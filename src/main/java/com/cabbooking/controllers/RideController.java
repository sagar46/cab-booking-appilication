package com.cabbooking.controllers;

import com.cabbooking.controllers.mapper.DriverDTOConverter;
import com.cabbooking.controllers.mapper.RideDataDTOConverter;
import com.cabbooking.domain.Coordinates;
import com.cabbooking.domain.Driver;
import com.cabbooking.dto.Response;
import com.cabbooking.dto.requests.ChooseRideRequest;
import com.cabbooking.dto.requests.CompleteRideRequest;
import com.cabbooking.dto.requests.FindRideRequest;
import com.cabbooking.dto.responses.ChooseRideResponse;
import com.cabbooking.dto.responses.CompleteRideResponse;
import com.cabbooking.dto.responses.FindRideResponse;
import com.cabbooking.services.impl.RideServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/ride")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RideController {
    private final RideServiceImpl rideServiceImpl;

    @PostMapping("all")
    public Response<FindRideResponse> findRide(@RequestBody FindRideRequest findRideRequest) {
        log.debug("RideController.findRide call started...");
        Coordinates source = DriverDTOConverter.convertCoordinateDTOToCoordinate(findRideRequest.getSource());
        Coordinates destination = DriverDTOConverter.convertCoordinateDTOToCoordinate(findRideRequest.getDestination());
        List<Driver> drivers = rideServiceImpl.findRide(findRideRequest.getUsername(), source, destination);
        String message = "";
        if (drivers.isEmpty()) {
            message = "No ride available for now";
        } else {
            message = "Total ride avialable:" + drivers.size();
        }
        FindRideResponse findRideResponse = new FindRideResponse();
        findRideResponse.setAvailableRides(drivers.stream().map(DriverDTOConverter::convertDriverToDriverDTO).collect(Collectors.toList()));
        log.debug("RideController.findRide call completed...");
        return Response.<FindRideResponse>builder()
                .data(findRideResponse)
                .message(message)
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/choose")
    public Response<ChooseRideResponse> chooseRide(@RequestBody ChooseRideRequest chooseRideRequest) {
        log.debug("RideController.chooseRise call started...");
        rideServiceImpl.chooseRide(RideDataDTOConverter.convertChooseRideRequestToRideData(chooseRideRequest));
        log.debug("RideController.chooseRide call completed...");
        return Response.<ChooseRideResponse>builder()
                .message("Ride booked successfully.")
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/completed")
    public Response<CompleteRideResponse> completeRide(@RequestBody CompleteRideRequest completeRideRequest) {
        log.debug("RideController.completeRide call started...");
        log.debug("RideController.completeRide call completed...");
        return Response.<CompleteRideResponse>builder()
                .message("Ride Completed")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
