package application.U5D15.controllers;

import application.U5D15.entities.Event;
import application.U5D15.entities.User;
import application.U5D15.exceptions.BadRequestException;
import application.U5D15.exceptions.NotEventFoundException;
import application.U5D15.payloads.NewEventDTO;
import application.U5D15.payloads.PutUserDTO;
import application.U5D15.payloads.UserIdDTO;
import application.U5D15.repositories.EventRepository;
import application.U5D15.services.EventService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepo;


    @GetMapping("")
    public Page<Event> getAllEvents(@RequestParam(defaultValue = "0")int page ,
                                    @RequestParam(defaultValue = "10")int size,
                                    @RequestParam(defaultValue = "id")String order){
        return eventService.getAllEvent(page , size , order);
    }

    @GetMapping("/{id}")
    public Event findById(@PathVariable int id){
        return eventService.findById(id);
    }


    @PutMapping("/{id}/partecipa")
    public Event partecipate(@PathVariable int id , @RequestBody @Validated UserIdDTO userId , BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
           return eventService.partecipate(id , userId );
        }catch (IOException e){
                System.err.println(e.getMessage());
                return null;
            }
    }}





    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id){
        eventService.findByIdAndDelete(id);
    }


    @PostMapping("")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvent(@RequestBody @Validated NewEventDTO body , BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return eventService.save(body);
            }catch (IOException e){
                System.err.println(e.getMessage());
                return null;
            }

        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Event putEvent(@PathVariable int id, @Validated @RequestBody NewEventDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
        return eventService.findByIdAndUpdate(id, body);
            }catch (IOException e){
                System.err.println(e.getMessage());
                return null;
            }

        }
    }


    @PatchMapping("/{id}/upload")
    public Event uploadPicture(@PathVariable int id , @RequestParam("picture")  MultipartFile body) throws IOException {
      return eventService.setEventPicture(id , body);
    }




}
