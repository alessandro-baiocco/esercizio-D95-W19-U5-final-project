package application.U5D15.controllers;



import application.U5D15.entities.User;

import application.U5D15.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService usersService;


    @GetMapping("")
    public Page<User> getAllUser(@RequestParam(defaultValue = "0")int page ,
                                 @RequestParam(defaultValue = "10")int size,
                                 @RequestParam(defaultValue = "id")String order){
        return usersService.getAllUser(page , size , order);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return usersService.findById(id);
    }





    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id){
        usersService.findByIdAndDelete(id);
    }













}
