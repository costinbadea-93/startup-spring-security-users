package mainAPI.controller;

import io.swagger.annotations.*;
import mainAPI.model.Event;
import mainAPI.model.EventReservation;
import mainAPI.service.EventReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by cbadea on 4/3/2018.
 */
@RestController
@RequestMapping("/eventReservation")
@Api("eventReservation")
public class EventReservationController {

    private static final Logger LOGGER = (Logger) LoggerFactory.
            getLogger(EventReservationController.class);

    @Autowired
    EventReservationService eventReservationService;

    @PostMapping(value = "/addEventReservation")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventReservationController.addEventReservation}", response = EventReservation.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> addEventReservation(@ApiParam("Add EventReservation") @RequestBody EventReservation eventReservation,
                                              @ApiParam("UserId") @RequestParam(value = "userId") int userId,
                                              @ApiParam("EventId") @RequestParam(value = "eventId") int eventId) {

        EventReservation eventReservationSaved = eventReservationService.addEventReservation(eventReservation, userId, eventId);

        URI location = URI.create("/eventReservation/addEventReservation/" + eventReservationSaved.getId());
        LOGGER.debug("EventReservation creation URI: " + location);
        return ResponseEntity.created(location).body(eventReservationSaved.getId());
    }

    @DeleteMapping(value = "/deleteEventReservation")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventController.deleteReservation}", response = EventReservation.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> deleteEventReservation(@ApiParam("EventReservationId") @RequestParam(value = "eventReservationId") int eventReservationId) {
        eventReservationService.deleteEventReservation(eventReservationId);
        LOGGER.warn("Deleted Event with id: " + eventReservationId);
        return ResponseEntity.ok().body(eventReservationId);
    }
}
