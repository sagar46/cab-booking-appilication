package com.cabbooking.services.impl;

import com.cabbooking.domain.Coordinates;
import com.cabbooking.domain.Driver;
import com.cabbooking.domain.RideData;
import com.cabbooking.domain.User;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.UserNotFoundException;
import com.cabbooking.repositories.DriverRepository;
import com.cabbooking.repositories.RideRepository;
import com.cabbooking.repositories.UserRepository;
import com.cabbooking.services.interfaces.RideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    @Value("${user.error.not-found}")
    private String userNotFoundExistMsg;
    @Value("${ride.error.occupied}")
    private String rideOccupiedMsg;

    @Override
    public List<Driver> findRide(String username, Coordinates source, Coordinates destination) {
        log.debug("RideServiceImpl.findRide call started...");
        User user = userRepository.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(userNotFoundExistMsg);
        }
        List<Driver> availableDrivers = new ArrayList<>();
        rideRepository.getAllDrivers().forEach(driver -> {
            if (distanceCalculator(source, driver.getCoordinates()) < 5 && !driver.isOccupied()) {
                availableDrivers.add(driver);
            }
        });
        log.debug("RideServiceImpl.findRide call completed...");
        return availableDrivers;
    }

    @Override
    public Driver chooseRide(RideData rideData) {
        log.debug("RideServiceImpl.chooseRide call started...");
        User user = userRepository.getUserByUsername(rideData.getUsername());
        Driver driver = driverRepository.getDriverByDriverName(rideData.getDriverName());
        if (driver.isOccupied()) {
            throw new CabBookingException(rideOccupiedMsg);
        }
        driverRepository.updateDriver(driver);
        rideRepository.chooseRide(rideData);
        log.debug("RideServiceImpl.chooseRise call completed...");
        return driver;
    }

    @Override
    public Driver completeRide(RideData rideData) {
        log.debug("RideServiceImpl.completeRide call started...");
        User user = userRepository.getUserByUsername(rideData.getUsername());
        Driver driver = driverRepository.getDriverByDriverName(rideData.getDriverName());
        driverRepository.updateDriver(driver);
        rideRepository.completeRide(rideData);
        log.debug("RideServiceImpl.completeRide call completed...");
        return Driver.builder().build();
    }


    private double distanceCalculator(Coordinates coordinates1, Coordinates coordinates2) {
        return Math.sqrt(
                Math.pow(coordinates2.getXCoordinate() - coordinates1.getXCoordinate(), 2) +
                        Math.pow(coordinates2.getYCoordinate() - coordinates1.getYCoordinate(), 2));
    }

}
