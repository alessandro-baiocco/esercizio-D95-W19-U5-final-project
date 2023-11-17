package application.U5D15.entities;

import application.U5D15.enums.Ruolo;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String userName;
    @Enumerated(EnumType.STRING)
    private Ruolo role;
    @ManyToMany
    @JoinTable(name = "event_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events;

}
