package mainAPI.service;

import mainAPI.model.RegistrationEvent;
import mainAPI.model.EventLocation;
import mainAPI.repository.RegistrationEventRepository;
import mainAPI.repository.EventLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventDateService {

    @Autowired
    private RegistrationEventRepository registrationEventRepository;

    @Autowired
    private EventLocationRepository eventLocationRepository;

    public RegistrationEvent addEventDate(RegistrationEvent registrationEvent, int eventLocationId) {

        EventLocation eventLocation = eventLocationRepository.findById(eventLocationId);
        //registrationEvent.setEventLocation(eventLocation);

        return registrationEventRepository.save(registrationEvent);
    }
}
