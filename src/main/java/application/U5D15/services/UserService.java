package application.U5D15.services;

import application.U5D15.entities.User;
import application.U5D15.enums.Ruolo;
import application.U5D15.exceptions.BadRequestException;
import application.U5D15.exceptions.NotUserFoundException;
import application.U5D15.payloads.NewUserDTO;
import application.U5D15.repositories.UserRepository;
import com.cloudinary.Cloudinary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;




    public User findByEmail(String email){
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new NotUserFoundException("Utente con email " + email + " non trovato!"));
    }
    public User findByUserName(String userName){
        return userRepo.findByUserName(userName)
                .orElseThrow(() -> new NotUserFoundException("Utente con userName " + userName + " non trovato!"));
    }








    public User findById(int id) throws NotUserFoundException{
        return userRepo.findById(id).orElseThrow(() -> new NotUserFoundException(id));
    }




    public Page<User> getAllUser(int page , int size , String order){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        return userRepo.findAll(pageable);
    }





    public void findByIdAndDelete(int id) throws NotUserFoundException{
        User found = findById(id);
        userRepo.delete(found);
    }



}