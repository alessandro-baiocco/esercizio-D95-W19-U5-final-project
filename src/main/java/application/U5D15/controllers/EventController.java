package application.U5D15.controllers;

import application.U5D15.entities.Event;
import application.U5D15.exceptions.BadRequestException;
import application.U5D15.payloads.NewEventDTO;
import application.U5D15.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;


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




}
