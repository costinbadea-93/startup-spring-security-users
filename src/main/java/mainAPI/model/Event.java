package mainAPI.model;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String eventName;
    private String eventDescription;
    private int freePlacesNumber;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    //    @ManyToOne
//    private EventDate eventDate;
    @ManyToMany(mappedBy = "events")
    @JsonIgnore
    private List<User> users;

    @ManyToOne(cascade = CascadeType.ALL)
    private EventLocation eventLocation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    @JsonIgnore
    private List<EventReservation> eventReservation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getFreePlacesNumber() {
        return freePlacesNumber;
    }

    public void setFreePlacesNumber(int freePlacesNumber) {
        this.freePlacesNumber = freePlacesNumber;
    }
//
//    public EventDate getEventDate() {
//        return eventDate;
//    }
//
//    public void setEventDate(EventDate eventDate) {
//        this.eventDate = eventDate;
//    }

    public EventLocation getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(EventLocation eventLocation) {
        this.eventLocation = eventLocation;
    }

    public List<EventReservation> getEventReservation() {
        return eventReservation;
    }

    public void setEventReservation(List<EventReservation> eventReservation) {
        this.eventReservation = eventReservation;
    }
}
