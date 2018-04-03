package mainAPI.controller;

import io.swagger.annotations.*;
import mainAPI.model.EventDate;
import mainAPI.model.EventLocation;
import mainAPI.model.EventReservation;
import mainAPI.service.EventReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cbadea on 4/3/2018.
 */
@RestController
@RequestMapping("/eventReservation")
@Api("eventReservation")
public class EventReservationController {

    @Autowired
    EventReservationService eventReservationService;

    @PostMapping(value = "/addEventReservation")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventReservationController.addEventReservation}", response = EventReservation.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public EventReservation addEventReservation(@ApiParam("Add EventReservation") @RequestBody EventReservation eventReservation) {
        return eventReservationService.addEventReservation(eventReservation);
    }
}
