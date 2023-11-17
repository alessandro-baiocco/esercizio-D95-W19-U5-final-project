package application.U5D15.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private LocalDateTime data;
    private String place;
    private int numberMax;
    @ManyToMany
    @JoinTable(name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> participants;
    private String picture;


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                ", place='" + place + '\'' +
                ", numberMax=" + numberMax +
                ", participants=" + participants.size() +
                ", picture='" + picture + '\'' +
                '}';
    }
}
