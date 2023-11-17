package application.U5D15.services;

import application.U5D15.entities.User;
import application.U5D15.exceptions.UnauthorizedException;
import application.U5D15.payloads.UserLoginDTO;
import application.U5D15.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body){
        User user = usersService.findByUserName(body.userName());


        if(body.password().equals(user.getPassword())){

            return jwtTools.createToken(user);
        }else {

            throw new UnauthorizedException("credenziali non valide!");
        }


    }





}
