package com.cabbooking.controllers;

import com.cabbooking.controllers.mapper.DriverDTOConverter;
import com.cabbooking.domain.Driver;
import com.cabbooking.dto.DriverDTO;
import com.cabbooking.dto.Response;
import com.cabbooking.dto.requests.AddDriverRequest;
import com.cabbooking.dto.responses.AddDriverResponse;
import com.cabbooking.services.impl.DriverServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverController {
    private final DriverServiceImpl driverServiceImpl;

    @Value("${driver.success.add-driver}")
    private String driverAddedMessage;
    @Value("${driver.success.all-driver}")
    private String allDriverFetchedMsg;

    @PostMapping("/")
    public Response<AddDriverResponse> addDriver(@RequestBody AddDriverRequest addDriverRequest) {
        log.debug("DriverController.addDriver call started...");
        Driver savedDriver = driverServiceImpl.addDriver(DriverDTOConverter.convertAddDriverRequestToDriver(addDriverRequest));
        String driverAddedSuccessMsg = MessageFormat.format(driverAddedMessage, savedDriver.getName());
        log.debug("DriverController.addDriver call completed...");
        return Response.<AddDriverResponse>builder()
                .data(DriverDTOConverter.convertDriverToAddDriverResponse(savedDriver))
                .message(driverAddedSuccessMsg)
                .statusCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/all-driver")
    public Response<List<DriverDTO>> getAllDrivers() {
        log.debug("DriverController.getAllDrivers call started...");
        List<Driver> drivers = driverServiceImpl.getAllDriver();
        log.debug("DriverController.getAllDrivers call completed...");
        return Response.<List<DriverDTO>>builder()
                .data(drivers.stream().map(DriverDTOConverter::convertDriverToDriverDTO).toList())
                .message(allDriverFetchedMsg)
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
