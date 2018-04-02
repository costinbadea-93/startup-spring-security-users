package mainAPI.service;

import mainAPI.model.Event;
import mainAPI.model.EventDate;
import mainAPI.repository.EventDateRepository;
import mainAPI.repository.EventRepository;
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
    EventDateRepository eventDateRepository;

    public Event addEvent(Event event, int eventDateId) {
        EventDate eventDate = eventDateRepository.findById(eventDateId);
        List<Event> currentEventsList = eventDate.getEvents();
        currentEventsList.add(event);
        eventDate.setEvents(currentEventsList);

        event.setEventDate(eventDate);
        return eventRepository.save(event);

    }
}

