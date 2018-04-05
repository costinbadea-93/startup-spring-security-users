package mainAPI.service;

import io.swagger.annotations.Authorization;

import mainAPI.model.Event;
import mainAPI.model.EventReservation;
import mainAPI.model.User;
import mainAPI.repository.EventRepository;
import mainAPI.repository.EventReservationRepository;
import mainAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cbadea on 4/3/2018.
 */
@Service
public class EventReservationService {

    @Autowired
    EventReservationRepository eventReservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    public EventReservation addEventReservation(EventReservation eventReservation, int userId, int eventId) {

        User user = userRepository.findOne(userId);
        Event event = eventRepository.findOne(eventId);
        eventReservation.setUser(user);
        eventReservation.setEvent(event);

        return eventReservationRepository.save(eventReservation);
    }
}
