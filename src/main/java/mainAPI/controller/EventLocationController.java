package mainAPI.controller;

import io.swagger.annotations.*;
import mainAPI.model.EventLocation;
import mainAPI.service.EventLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eventLocation")
@Api("eventLocation")
public class EventLocationController {

    private static final Logger LOGGER = (Logger) LoggerFactory.
            getLogger(EventController.class);

    @Autowired
    EventLocationService eventLocationService;

    @PostMapping(value = "/addEventLocation")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${EventLocationController.addEventLocation}", response = EventLocation.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> addEventDate(@ApiParam("Add RegistrationEvent") @RequestBody EventLocation eventLocation) {

        EventLocation addedLocation = eventLocationService.addEventLocation(eventLocation);
        URI location = URI.create("eventLocation/addEventLocation" + addedLocation.getId());
        LOGGER.warn("EventLocation added URI: " + location);
        return ResponseEntity.created(location).body(addedLocation.getId());
    }

    @DeleteMapping(value = "/deleteEventLocation")
    @CrossOrigin("http://127.0.0.1:4200/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventLocationController.deleteEventLocation}", response = EventLocation.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> deleteEventLocation(@ApiParam("EventLocationId") @RequestParam(value = "eventLocationId") int eventLocationId) {
        eventLocationService.deleteEventLocation(eventLocationId);
        LOGGER.warn("Deleted EventLocation with id: " + eventLocationId);
        return ResponseEntity.ok().body(eventLocationId);
    }

    @GetMapping(value = "/getLocations")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventLocationController.getLocations}", response = EventLocation.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<EventLocation> getEventLocations() {
        return eventLocationService.getLocations();
    }
}
