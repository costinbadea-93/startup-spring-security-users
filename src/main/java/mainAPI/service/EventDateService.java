package mainAPI.service;

import mainAPI.model.EventDate;
import mainAPI.model.EventLocation;
import mainAPI.repository.EventDateRepository;
import mainAPI.repository.EventLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventDateService {

    @Autowired
    private EventDateRepository eventDateRepository;

    @Autowired
    private EventLocationRepository eventLocationRepository;

    public EventDate addEventDate(EventDate eventDate, int eventLocationId) {

        EventLocation eventLocation = eventLocationRepository.findById(eventLocationId);
        eventDate.setEventLocation(eventLocation);

        return eventDateRepository.save(eventDate);
    }
}
