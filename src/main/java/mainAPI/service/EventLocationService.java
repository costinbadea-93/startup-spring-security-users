package mainAPI.service;

import mainAPI.model.EventLocation;
import mainAPI.repository.EventLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cbadea on 4/2/2018.
 */
@Service
public class EventLocationService {

    @Autowired
    EventLocationRepository eventLocationRepository;

    public EventLocation addEventLocation(EventLocation eventLocation) {
        return eventLocationRepository.save(eventLocation);
    }

    public void deleteEventLocation(int eventLocationId) {
        EventLocation eventLocation = eventLocationRepository.findById(eventLocationId);
        eventLocationRepository.delete(eventLocation);

    }
}
