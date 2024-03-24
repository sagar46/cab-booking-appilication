package com.cabbooking.services.interfaces;

import com.cabbooking.domain.Coordinates;
import com.cabbooking.domain.Driver;
import com.cabbooking.domain.RideData;

import java.util.List;

public interface RideService {
    public List<Driver> findRide(String username, Coordinates source, Coordinates destination);

    public Driver chooseRide(RideData rideData);

    public Driver completeRide(RideData rideData);
}
