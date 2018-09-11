package mainAPI.service;

import mainAPI.model.Event;
import mainAPI.model.EventReservation;
import mainAPI.model.User;
import mainAPI.repository.EventReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

@Component
public class ScheduledTasks {

    @Autowired
    EventService eventService;
    @Autowired
    EventReservationRepository eventReservationRepository;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 1 1 * * ?")
    public void reportCurrentTime() {
        List<EventReservation> reservationsList =  eventReservationRepository.findAll();
        List<Event> events = new ArrayList<>();
        reservationsList.forEach(e -> events.add(e.getEvent()));
        try {
            validateData(events);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateData(List<Event> events)  {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDateCreated =  new Date();
        for(Event event : events){
            Date eventDate = null;
            try {
                 eventDate = format.parse(event.getEventDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

           List<EventReservation>  reservations = eventReservationRepository.findAll().stream()
                    .filter(e -> e.getEvent().getId() == event.getId()).collect(Collectors.toList());
            List<User> users = new ArrayList<>();
            reservations.forEach(e -> users.add(e.getUser()));

            for(User u : users){
                if(eventDate.getMonth() == newDateCreated.getMonth() && eventDate.getYear() == newDateCreated.getYear()
                        && eventDate.getDay() - newDateCreated.getDay() == 1) {
                    try {
                        eventService.sendEmail(u.getEmail(),"ss","ss");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }


    }
}
