package com.cabbooking.services;

import com.cabbooking.domain.Coordinates;
import com.cabbooking.domain.Driver;
import com.cabbooking.domain.User;
import com.cabbooking.exception.UserNotFoundException;
import com.cabbooking.repositories.RideRepository;
import com.cabbooking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RideService {
    private final RideRepository rideRepository;
    private final UserRepository userRepository;

    public List<Driver> findRide(String username, Coordinates source, Coordinates destination) {
        log.debug("RideService.findRide call started...");
        User user = userRepository.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User does not exist");
        }
        List<Driver> availableDrivers = new ArrayList<>();
        rideRepository.getAllDrivers().forEach(driver -> {
            if (distanceCalculator(source, driver.getCoordinates()) < 5) {
                availableDrivers.add(driver);
            }
        });
        log.debug("RideService.findRide call completed...");
        return availableDrivers;
    }

    private double distanceCalculator(Coordinates coordinates1, Coordinates coordinates2) {
        return Math.sqrt(
                Math.pow(coordinates2.getXCoordinate() - coordinates1.getXCoordinate(), 2) +
                        Math.pow(coordinates2.getYCoordinate() - coordinates1.getYCoordinate(), 2));
    }

}
