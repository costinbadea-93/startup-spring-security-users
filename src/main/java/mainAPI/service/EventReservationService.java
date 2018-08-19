package mainAPI.service;

import io.swagger.annotations.Authorization;

import mainAPI.exception.CustomException;
import mainAPI.model.Event;
import mainAPI.model.EventReservation;
import mainAPI.model.User;
import mainAPI.repository.EventRepository;
import mainAPI.repository.EventReservationRepository;
import mainAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

        List<EventReservation> validationList = eventReservationRepository.findAll().stream()
                .filter(rez -> rez.getEvent().getId() == event.getId())
                .collect(Collectors.toList());

        if(validationList.size() > 0) {
            throw new CustomException("You have alrady applyed on this event !", HttpStatus.CONFLICT);
        }


        return eventReservationRepository.save(eventReservation);
    }

    public void deleteEventReservation(int eventReservationId) {
        EventReservation eventReservation =  eventReservationRepository.findOne(eventReservationId);
        eventReservationRepository.delete(eventReservation);
    }


}
