package application.U5D15.services;

import application.U5D15.entities.User;
import application.U5D15.enums.Ruolo;
import application.U5D15.exceptions.BadRequestException;
import application.U5D15.exceptions.UnauthorizedException;
import application.U5D15.payloads.NewUserDTO;
import application.U5D15.payloads.UserLoginDTO;
import application.U5D15.repositories.UserRepository;
import application.U5D15.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUser(UserLoginDTO body){
        User user = usersService.findByUserName(body.userName());


        if(bcrypt.matches(body.password(), user.getPassword())){

            return jwtTools.createToken(user);
        }else {

            throw new UnauthorizedException("credenziali non valide!");
        }


    }


    public User save(NewUserDTO body) throws IOException {
        userRepo.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });

        User newUser = new User();

        newUser.setLastName(body.lastName());
        newUser.setName(body.name());
        newUser.setUserName(body.userName());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setRole(Ruolo.UTENTE);


        return userRepo.save(newUser);
    }





}
