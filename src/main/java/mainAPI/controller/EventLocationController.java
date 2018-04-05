package mainAPI.controller;

import io.swagger.annotations.*;
import mainAPI.model.EventDate;
import mainAPI.model.EventLocation;
import mainAPI.service.EventLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by cbadea on 4/2/2018.
 */
@RestController
@RequestMapping("/eventLocation")
@Api("eventLocation")
public class EventLocationController {

    private static final Logger LOGGER = (Logger) LoggerFactory.
            getLogger(EventController.class);

    @Autowired
    EventLocationService eventLocationService;

    @PostMapping(value = "/addEventLocation")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventLocationController.addEventLocation}", response = EventDate.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> addEventDate(@ApiParam("Add EventDate") @RequestBody EventLocation eventLocation) {

        EventLocation addedEvent = eventLocationService.addEventLocation(eventLocation);
        URI location = URI.create("eventLocation/addEventLocation" + addedEvent.getId());
        LOGGER.debug("EventLocation added URI: " + location);
        return ResponseEntity.created(location).body(addedEvent.getId());
    }


}
