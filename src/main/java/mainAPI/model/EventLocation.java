package mainAPI.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class EventLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double latitudeValue;
    private Double longitudeValue;

//    @OneToMany(mappedBy = "eventLocation")
//    @JsonIgnore
//    private List<EventDate> eventDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventLocation", orphanRemoval=true)
    @JsonIgnore
    private List<Event> events;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLatitudeValue() {
        return latitudeValue;
    }

    public void setLatitudeValue(Double latitudeValue) {
        this.latitudeValue = latitudeValue;
    }

    public Double getLongitudeValue() {
        return longitudeValue;
    }

    public void setLongitudeValue(Double longitudeValue) {
        this.longitudeValue = longitudeValue;
    }


//    public List<EventDate> getEventDate() {
//        return eventDate;
//    }
//
//    public void setEventDate(List<EventDate> eventDate) {
//        this.eventDate = eventDate;
//    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
