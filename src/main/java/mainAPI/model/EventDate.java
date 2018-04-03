package mainAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class EventDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date eventDate;

    private String description;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "eventDate")
//    @JsonIgnore
//    private List<Event> events;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "eventDate")
//    @JsonIgnore
//    private List<User> eventParticipants;

    @ManyToOne
    @JoinColumn(name="event_location_id", nullable=false)
    private EventLocation eventLocation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public List<User> getEventParticipants() {
//        return eventParticipants;
//    }
//
//    public void setEventParticipants(List<User> eventParticipants) {
//        this.eventParticipants = eventParticipants;
//    }

//    public List<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<Event> events) {
//        this.events = events;
//    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventLocation getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(EventLocation eventLocation) {
        this.eventLocation = eventLocation;
    }

}
