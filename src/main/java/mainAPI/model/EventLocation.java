package mainAPI.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class EventLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double latitudeValue;
    private Double longitudeValue;

    @OneToOne
    private EventDate eventDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> eventsList;

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

    public EventDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(EventDate eventDate) {
        this.eventDate = eventDate;
    }

    public List<Event> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
    }
}
