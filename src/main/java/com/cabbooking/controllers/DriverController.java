package com.cabbooking.controllers;

import com.cabbooking.controllers.mapper.DriverDTOConverter;
import com.cabbooking.domain.Driver;
import com.cabbooking.dto.Response;
import com.cabbooking.dto.requests.AddDriverRequest;
import com.cabbooking.dto.responses.AddDriverResponse;
import com.cabbooking.services.impl.DriverServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@Slf4j
@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverController {
    private final DriverServiceImpl driverService;

    @Value("${driver.success.add-driver}")
    private String driverAddedMessage;

    @PostMapping("/")
    public Response<AddDriverResponse> addDriver(@RequestBody AddDriverRequest addDriverRequest) {
        log.debug("DriverController.addDriver call started...");
        Driver savedDriver = driverService.addDriver(DriverDTOConverter.convertAddDriverRequestToDriver(addDriverRequest));
        String driverAddedSuccessMsg = MessageFormat.format(driverAddedMessage,savedDriver.getName());
        log.debug("DriverController.addDriver call completed...");
        return Response.<AddDriverResponse>builder()
                .data(DriverDTOConverter.convertDriverToAddDriverResponse(savedDriver))
                .message(driverAddedSuccessMsg)
                .statusCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .build();
    }
}
