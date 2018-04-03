package mainAPI.model;

import javax.persistence.*;

/**
 * Created by cbadea on 4/3/2018.
 */
@Entity
public class EventReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numberOfReservedPlaces;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfReservedPlaces() {
        return numberOfReservedPlaces;
    }

    public void setNumberOfReservedPlaces(int numberOfReservedPlaces) {
        this.numberOfReservedPlaces = numberOfReservedPlaces;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
