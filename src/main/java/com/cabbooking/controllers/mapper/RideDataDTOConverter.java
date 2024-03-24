package com.cabbooking.controllers.mapper;

import com.cabbooking.domain.RideData;
import com.cabbooking.dto.requests.ChooseRideRequest;

public class RideDataDTOConverter {

    public static RideData convertChooseRideRequestToRideData(ChooseRideRequest chooseRideRequest) {
        return RideData.builder()
                .driverName(chooseRideRequest.getDriverName())
                .username(chooseRideRequest.getUsername())
                .build();
    }
}
