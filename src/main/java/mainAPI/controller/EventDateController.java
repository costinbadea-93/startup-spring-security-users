package mainAPI.controller;

import io.swagger.annotations.*;
import mainAPI.model.RegistrationEvent;
import mainAPI.service.EventDateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventDate")
@Api("eventsDate")
public class EventDateController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventDateService eventDateService;

    @PostMapping(value = "/addEventDate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${EventDateController.addEventDate}", response = RegistrationEvent.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public RegistrationEvent addEventDate(@ApiParam("Add RegistrationEvent") @RequestBody RegistrationEvent registrationEvent, @RequestParam int eventLocationId) {

        return eventDateService.addEventDate(registrationEvent, eventLocationId);
    }
}
