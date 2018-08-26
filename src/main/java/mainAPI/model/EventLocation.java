package mainAPI.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class EventLocation {

    @Id
    @Column(name = "LOCATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String locationAddress;
    private Double latitudeValue;
    private Double longitudeValue;

//    @OneToMany(mappedBy = "eventLocation")
//    @JsonIgnore
//    private List<RegistrationEvent> eventDate;

    @OneToMany( mappedBy = "eventLocation", cascade = CascadeType.ALL)
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


//    public List<RegistrationEvent> getRegistrationEvent() {
//        return eventDate;
//    }
//
//    public void setRegistrationEvent(List<RegistrationEvent> eventDate) {
//        this.eventDate = eventDate;
//    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
}
