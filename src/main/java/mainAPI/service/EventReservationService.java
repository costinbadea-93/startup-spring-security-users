package mainAPI.service;

import io.swagger.annotations.Authorization;

import mainAPI.model.Event;
import mainAPI.model.EventReservation;
import mainAPI.repository.EventReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cbadea on 4/3/2018.
 */
@Service
public class EventReservationService {

    @Autowired
    EventReservationRepository eventReservationRepository;

    public EventReservation addEventReservation(EventReservation eventReservation) {
        return eventReservationRepository.save(eventReservation);
    }
}
