package mainAPI.service;

import mainAPI.dto.ReviewDto;
import mainAPI.model.Event;
import mainAPI.model.EventLocation;
import mainAPI.model.User;
import mainAPI.repository.EventLocationRepository;
import mainAPI.repository.EventRepository;
import mainAPI.repository.EventReservationRepository;
import mainAPI.repository.UserRepository;
import mainAPI.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cbadea on 4/2/2018.
 */

@Service
@Transactional
public class EventService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventReservationRepository eventReservationRepository;

    @Autowired
    EventLocationRepository eventLocationRepository;

    @Autowired
    UserRepository userRepository;

    public Event applyOnEvent(Event event, int userId, int locationId) {
        User user = userRepository.findOne(userId);
        EventLocation eventLocation = eventLocationRepository.findById(locationId);
        event.setEventLocation(eventLocation);

        return eventRepository.save(event);
    }

    public Event addEvent(Event event, int locationId) {
        EventLocation el = eventLocationRepository.findById(locationId);
        event.setEventLocation(el);
        return eventRepository.save(event);
    }

    public void deleteEvent(int eventId) {
        Event event = eventRepository.findOne(eventId);
        eventRepository.delete(event);
    }

    public Page<Event> getEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public Event getSpecificEvent(int eventId) {
        return eventRepository.findOne(eventId);
    }

    public Event rateEvent(int eventId) {
        Event event = eventRepository.findOne(eventId);
        event.setNumberOfLikes(event.getNumberOfLikes() + 1);
        return event;
    }

    public List<ReviewDto> getReviwes(int eventId, User user) {

        String token = jwtTokenProvider.createToken(user.getUsername(), userRepository.findByUsername(user.getUsername()).getRoles());

        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://localhost:9001/review/getEventReview/" + eventId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", "Bearer " + token);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<List<ReviewDto>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<ReviewDto>>() {
                });

        List<ReviewDto> reviews = response.getBody();

        return reviews;
    }

    public ReviewDto addReview(ReviewDto reviewDto, User user) {
        String token = jwtTokenProvider.createToken(user.getUsername(), userRepository.findByUsername(user.getUsername()).getRoles());

        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://localhost:9001/review/addReview";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", "Bearer " + token);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        HttpEntity<?> entity = new HttpEntity<>(reviewDto,headers);

        HttpEntity<ReviewDto> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                ReviewDto.class);

        ReviewDto review = response.getBody();

        return review;
    }

   public String deleteReview (int reviewId, User user){
       String token = jwtTokenProvider.createToken(user.getUsername(), userRepository.findByUsername(user.getUsername()).getRoles());

       RestTemplate restTemplate = new RestTemplate();

       String uri = "http://localhost:9001/review/deleteReview/" + reviewId;

       HttpHeaders headers = new HttpHeaders();
       headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
       headers.set("Authorization", "Bearer " + token);

       UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

       HttpEntity<?> entity = new HttpEntity<>(headers);

       HttpEntity<String> response = restTemplate.exchange(
               builder.toUriString(),
               HttpMethod.DELETE,
               entity,
               String.class);

       String result = response.getBody();

       return result;
   }
}

