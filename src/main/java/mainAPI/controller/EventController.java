package mainAPI.controller;

import io.swagger.annotations.*;
import mainAPI.model.Event;
import mainAPI.model.EventDate;
import mainAPI.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.net.URI;

/**
 * Created by cbadea on 4/2/2018.
 */
@RestController
@RequestMapping("/event")
@Api("events")
public class EventController {

    private static final Logger LOGGER = (Logger) LoggerFactory.
            getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @PostMapping(value = "/applyOnEvent")
    @PreAuthorize("hasRole('ROLE_USER')")
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
    public ResponseEntity<?> addEvent(@ApiParam("Add Event") @RequestBody Event event) {

        Event addedEvent = eventService.addEvent(event);
        URI location = URI.create("event/applyOnEvent" + addedEvent.getId());
        LOGGER.debug("Event added URI: " + location);

        return ResponseEntity.created(location).body(addedEvent.getId());
    }

}
