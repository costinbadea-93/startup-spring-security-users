package mainAPI.service;

import mainAPI.model.Event;
import mainAPI.model.EventLocation;
import mainAPI.model.User;
import mainAPI.repository.EventLocationRepository;
import mainAPI.repository.EventRepository;
import mainAPI.repository.EventReservationRepository;
import mainAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cbadea on 4/2/2018.
 */

@Service
@Transactional
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

    public Page<Event> getEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public Event getSpecificEvent(int eventId) {
        return eventRepository.findOne(eventId);
    }

    public Event rateEvent(int eventId){
        Event event =  eventRepository.findOne(eventId);
        event.setNumberOfLikes(event.getNumberOfLikes() + 1);
        return event;
    }
}

