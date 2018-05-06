package mainAPI.service;

import mainAPI.model.Event;
import mainAPI.model.EventLocation;
import mainAPI.model.User;
import mainAPI.repository.EventLocationRepository;
import mainAPI.repository.EventRepository;
import mainAPI.repository.EventReservationRepository;
import mainAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cbadea on 4/2/2018.
 */

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventReservationRepository eventReservationRepository;

    @Autowired
    EventLocationRepository eventLocationRepository;

    @Autowired
    UserRepository userRepository;

    public Event applyOnEvent(Event event, int userId, int locationId) {
        User user = userRepository.findOne(userId);
        EventLocation eventLocation = eventLocationRepository.findById(locationId);
        event.setEventLocation(eventLocation);

        return eventRepository.save(event);
    }

    public Event addEvent(Event event, int locationId) {
        EventLocation el =  eventLocationRepository.findById(locationId);
        event.setEventLocation(el);
        return eventRepository.save(event);
    }

    public void deleteEvent(int eventId) {
        Event event =  eventRepository.findOne(eventId);
        eventRepository.delete(event);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event getSpecificEvent(int eventId) {
        return eventRepository.findOne(eventId);
    }
}

