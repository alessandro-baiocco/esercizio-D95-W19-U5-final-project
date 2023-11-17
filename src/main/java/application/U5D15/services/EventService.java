package application.U5D15.services;

import application.U5D15.entities.Event;
import application.U5D15.entities.User;
import application.U5D15.exceptions.NotEventFoundException;
import application.U5D15.payloads.NewEventDTO;
import application.U5D15.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepo;

    public Event save(NewEventDTO body) throws IOException {
        List<User> partecipanti = new ArrayList<>();

        Event newEvent = new Event();

        newEvent.setTitle(body.title());
        newEvent.setDescription(body.description() == null ? "nessuna descrizione" : body.description());
        newEvent.setPlace(body.place());
        newEvent.setNumberMax(body.numberMax());
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


}
