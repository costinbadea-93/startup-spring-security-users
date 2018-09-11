package mainAPI.controller;

import io.swagger.annotations.*;
import mainAPI.dto.AddReviewRequestDto;
import mainAPI.dto.ReviewDto;
import mainAPI.model.Event;
import mainAPI.model.User;
import mainAPI.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Created by cbadea on 4/2/2018.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/event")
@Api("events")
public class EventController {

    private static final Logger LOGGER = (Logger) LoggerFactory.
            getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping( value = "/**", method = RequestMethod.OPTIONS ) public ResponseEntity handle() { return new ResponseEntity(HttpStatus.OK); }

    @PostMapping(value = "/applyOnEvent")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${EventController.applyOnEvent}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> applyOnEvent(@ApiParam("Apply on Event") @RequestBody Event event,
                                          @ApiParam("UserId") @RequestParam(value = "userId") int userId,
                                          @ApiParam("LocationId") @RequestParam(value = "locationId") int locationId ) {

        Event savedEvent = eventService.applyOnEvent(event, userId, locationId);

        URI location = URI.create("event/applyOnEvent" + savedEvent.getId());
        LOGGER.debug("Applied on event URI: " + location);
        return ResponseEntity.created(location).body(savedEvent.getId());

    }

    @PostMapping(value = "/addEvent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventController.addEvent}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> addEvent(@ApiParam("Add Event") @RequestBody Event event,
                                      @ApiParam("LocationId") @RequestParam(value = "locationId") int locationId) {

        Event addedEvent = eventService.addEvent(event, locationId);
        URI location = URI.create("event/applyOnEvent" + addedEvent.getId());
        LOGGER.debug("Event added URI: " + location);

        return ResponseEntity.created(location).body(addedEvent.getId());
    }

    @CrossOrigin("http://127.0.0.1:4200/")
    @GetMapping(value = "/getEvents")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${EventController.addEvent}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Page<Event> getEvents(Pageable pageable){
        Page<Event> events = eventService.getEvents(pageable);
        return events;
    }

    @CrossOrigin("http://127.0.0.1:4200/")
    @GetMapping(value = "/getTopRatedEvents")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${EventController.getTopRatedEvents}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Page<Event> getTopRatedEvents(@PageableDefault(size = 4, sort = "numberOfLikes", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Event> events = eventService.getEvents(pageable);
        return events;
    }

    @CrossOrigin("http://127.0.0.1:4200/")
    @GetMapping(value = "/getEvent/{eventId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${EventController.getEvent}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Event getEvent(@ApiParam("EventId") @PathVariable(value = "eventId") int eventId){
        Event event = eventService.getSpecificEvent(eventId);
        return event;
    }

    @CrossOrigin("http://127.0.0.1:4200/")
    @PostMapping(value = "/rateEvent/{eventId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${EventController.rateEvent}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity rateEvent(@ApiParam("EventId") @PathVariable(value = "eventId") int eventId){
        Event event = eventService.rateEvent(eventId);
        return ResponseEntity.ok(event);
    }


    @CrossOrigin("http://127.0.0.1:4200/")
    @PostMapping(value = "/getReviews/{eventId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${EventController.getReviwes}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<ReviewDto> getEventReviews(@PathVariable int eventId, @RequestBody User user){

        List<ReviewDto> reviews = eventService.getReviwes(eventId, user);
        return reviews;
    }

    @CrossOrigin("http://127.0.0.1:4200/")
    @PostMapping(value = "/addReview")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${EventController.addReview}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ReviewDto addReview(@RequestBody AddReviewRequestDto request){

       return  eventService.addReview(request.getReviewDto(), request.getUser());
    }

    @CrossOrigin("http://127.0.0.1:4200/")
    @PostMapping(value = "/deleteReview/{reviewId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventController.deleteReview}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String deleteReview(@PathVariable int reviewId, @RequestBody User user){
         return  eventService.deleteReview(reviewId, user);
    }

    @CrossOrigin("http://127.0.0.1:4200/")
    @DeleteMapping(value = "/deleteEvent/{eventId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventController.deleteEvent}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void deleteEvent(@PathVariable int eventId){
          eventService.deleteEvent(eventId);
    }

    @PostMapping(value = "/updateEvent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventController.updateEvent}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> updateEvent(@ApiParam("Update Event") @RequestBody Event event,
                                         @ApiParam("LocationId") @RequestParam(value = "locationId") int locationId) {

        Event updateEvent = eventService.updateEvent(event, locationId);
        URI location = URI.create("event/applyOnEvent" + updateEvent.getId());
        LOGGER.debug("Event added URI: " + location);

        return ResponseEntity.created(location).body(updateEvent.getId());
    }

    @PostMapping(value = "/contactAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${EventController.contactAdmin}", response = String.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> contactAdmin(@RequestParam String name,
                                                   @RequestParam String email,
                                                   @RequestParam String text
                               ) {
        try{
            eventService.sendEmail(email,name,text);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }
}
