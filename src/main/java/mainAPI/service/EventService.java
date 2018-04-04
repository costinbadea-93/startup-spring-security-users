package mainAPI.service;

import mainAPI.model.Event;
import mainAPI.model.EventDate;
import mainAPI.model.EventLocation;
import mainAPI.model.User;
import mainAPI.repository.EventDateRepository;
import mainAPI.repository.EventLocationRepository;
import mainAPI.repository.EventRepository;
import mainAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    EventLocationRepository eventLocationRepository;

    @Autowired
    UserRepository userRepository;

    public Event addEvent(Event event, int userId) {
//        EventDate eventDate = eventDateRepository.findById(eventDateId);
//        List<Event> currentEventsList = eventDate.getEvents();
//        currentEventsList.add(event);
//        eventDate.setEvents(currentEventsList);

//        EventLocation eventLocation = eventLocationRepository.findById(locationId);
//        List<Event> currentEventsLocationList = eventLocation.getEvents();
//        currentEventsLocationList.add(event);
//        eventLocation.setEvents(currentEventsLocationList);

//        event.setEventDate(eventDate);
     //   event.setEventLocation(eventLocation);
        User user = userRepository.findOne(userId);
        List<Event> userEvents = user.getEvents();
        userEvents.add(event);
        user.setEvents(userEvents);

        List<User> users = event.getUsers();
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
        event.setUsers(users);

        return eventRepository.save(event);

    }
}

