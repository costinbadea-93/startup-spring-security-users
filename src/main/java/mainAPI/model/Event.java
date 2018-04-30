package mainAPI.model;


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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID")
    private EventLocation eventLocation;

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

    public EventLocation getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(EventLocation eventLocation) {
        this.eventLocation = eventLocation;
    }

}
