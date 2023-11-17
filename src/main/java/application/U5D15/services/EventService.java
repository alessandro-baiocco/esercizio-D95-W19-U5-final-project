package application.U5D15.services;

import application.U5D15.entities.Event;
import application.U5D15.entities.Parteciaption;
import application.U5D15.entities.User;
import application.U5D15.exceptions.BadRequestException;
import application.U5D15.exceptions.NotEventFoundException;
import application.U5D15.payloads.NewEventDTO;
import application.U5D15.payloads.UserIdDTO;
import application.U5D15.repositories.EventRepository;
import application.U5D15.repositories.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private UserRepository userRepository;

    public Event save(NewEventDTO body) throws IOException {
        Set<User> partecipanti = new HashSet<>();

        Event newEvent = new Event();

        newEvent.setTitle(body.title());
        newEvent.setDescription(body.description() == null ? "nessuna descrizione" : body.description());
        newEvent.setPlace(body.place());
        newEvent.setNumberMax(body.numberMax());
        newEvent.setData(body.data());
        newEvent.setPicture("https://ui-avatars.com/api/?name=" + body.title().replace(" " , ""));
        newEvent.setParticipants(partecipanti);


        return eventRepo.save(newEvent);
    }


    public Event findById(int id) throws NotEventFoundException{
        return eventRepo.findById(id).orElseThrow(() -> new NotEventFoundException(id));
    }




    public Page<Event> getAllEvent(int page , int size , String order){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        return eventRepo.findAll(pageable);
    }





    public void findByIdAndDelete(int id) throws NotEventFoundException{
        Event found = findById(id);
        eventRepo.delete(found);
    }


    public Event findByIdAndUpdate(int id , NewEventDTO body) throws IOException {
        Event found = findById(id);
        found.setTitle(body.title());
        found.setDescription(body.description());
        found.setPlace(body.place());
        found.setData(body.data());
        found.setNumberMax(body.numberMax());

        eventRepo.save(found);
        return found;
    }



    public Event partecipate(int id , User user) {
        Event found = findById(id);
        System.err.println(found);
        if (found.getParticipants().size() < found.getNumberMax()){
            if(!found.getParticipants().add(user)){
                throw new BadRequestException("fai gia parte di questo evento");
            }else {
                return eventRepo.save(found);
            }
        }else {
            throw new BadRequestException("l'evento è pieno non puoi unirti");
        }
    }



    public Event setEventPicture(int id , MultipartFile file) throws IOException , NotEventFoundException {
            Event found = findById(id);
            String newImage = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
            found.setPicture(newImage);
            return eventRepo.save(found);
    }


    public void removeFromList(int id , User user) {
        Event found = findById(id);
        Set<User> partecipants = found.getParticipants();
        partecipants.removeIf(current -> current.getId() == user.getId());
        found.setParticipants(partecipants);
        eventRepo.save(found);
        userService.removefromList(user);
    }














}
