package murraco.controller;

import io.swagger.annotations.*;
import murraco.model.EventDate;
import murraco.model.User;
import murraco.service.EventDateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/event")
@Api("events")
public class EventController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventDateService eventDateService;


    @PostMapping(value = "/addEventDate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventController.addEventDate}", response = EventDate.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public EventDate addEventDate(@ApiParam("Add EventDate") @RequestBody EventDate eventDate) {
        return eventDateService.addEventDate(eventDate);
    }
}
