package murraco.service;

import murraco.model.Event;
import murraco.model.EventDate;
import murraco.repository.EventDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventDateService {

    @Autowired
    private EventDateRepository eventDateRepository;

    public EventDate addEventDate(EventDate eventDate) {
        return eventDateRepository.save(eventDate);
    }
}
