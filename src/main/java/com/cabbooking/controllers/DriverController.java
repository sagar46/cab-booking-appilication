package com.cabbooking.controllers;

import com.cabbooking.controllers.mapper.DriverDTOConverter;
import com.cabbooking.domain.Driver;
import com.cabbooking.dto.Response;
import com.cabbooking.dto.requests.AddDriverRequest;
import com.cabbooking.dto.responses.AddDriverResponse;
import com.cabbooking.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverController {
    private final DriverService driverService;

    @PostMapping("/")
    public Response<AddDriverResponse> addDriver(@RequestBody AddDriverRequest addDriverRequest){
        log.debug("DriverController.addDriver call started...");
        Driver savedDriver = driverService.addDriver(DriverDTOConverter.convertAddDriverRequestToDriver(addDriverRequest));
        log.debug("DriverController.addDriver call completed...");
        return Response.<AddDriverResponse>builder()
                .data(DriverDTOConverter.convertDriverToAddDriverResponse(savedDriver))
                .message("Driver added successfully")
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build();
    }
}
