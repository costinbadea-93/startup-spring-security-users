package mainAPI.controller;

import io.swagger.annotations.*;
import mainAPI.model.EventDate;
import mainAPI.model.EventLocation;
import mainAPI.service.EventLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cbadea on 4/2/2018.
 */
@RestController
@RequestMapping("/eventLocation")
@Api("eventLocation")
public class EventLocationController {

    @Autowired
    EventLocationService eventLocationService;

    @PostMapping(value = "/addEventLocation")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventLocationController.addEventLocation}", response = EventDate.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public EventLocation addEventDate(@ApiParam("Add EventDate") @RequestBody EventLocation eventLocation) {
        return eventLocationService.addEventLocation(eventLocation);
    }


}
