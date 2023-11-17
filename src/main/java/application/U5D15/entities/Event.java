package application.U5D15.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private Date data;
    private String place;
    private int numberMax;
    @ManyToMany
    @JoinTable(name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> participants;


}
