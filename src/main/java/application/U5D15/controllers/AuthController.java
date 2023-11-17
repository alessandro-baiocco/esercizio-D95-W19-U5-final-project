package application.U5D15.controllers;

import application.U5D15.entities.User;
import application.U5D15.exceptions.BadRequestException;
import application.U5D15.payloads.NewUserDTO;
import application.U5D15.payloads.UserLoginDTO;
import application.U5D15.payloads.UserLoginSuccessDTO;
import application.U5D15.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body){

        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }




    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated NewUserDTO body , BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.save(body);
            }catch (IOException e){
                System.err.println(e.getMessage());
                return null;
            }

        }
    }

}
