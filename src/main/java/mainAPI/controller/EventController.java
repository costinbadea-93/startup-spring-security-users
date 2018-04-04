package mainAPI.controller;

import io.swagger.annotations.*;
import mainAPI.model.Event;
import mainAPI.model.EventDate;
import mainAPI.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;

/**
 * Created by cbadea on 4/2/2018.
 */
@RestController
@RequestMapping("/event")
@Api("events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(value = "/addEvent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventController.addEvent}", response = Event.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Event addEvent(@ApiParam("Add Event") @RequestBody Event event, @PathVariable(value = "userId", required = false) int userId ) {
        return eventService.addEvent(event, userId);
    }

}
