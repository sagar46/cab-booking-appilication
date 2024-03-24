package com.cabbooking.utils;

import com.cabbooking.domain.Coordinates;
import com.cabbooking.domain.Driver;
import com.cabbooking.enums.Gender;
import com.cabbooking.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateDriverTestCases {
    private final DriverRepository driverRepository;

    public void addDriverTestCase() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(createDriver("Driver1", 22, Gender.MALE, "Swift", "KA-O1-12345", Coordinates.builder().xCoordinate(10).yCoordinate(1).build()));
        drivers.add(createDriver("Driver2", 29, Gender.MALE, "Swift", "KA-O1-12345", Coordinates.builder().xCoordinate(11).yCoordinate(10).build()));
        drivers.add(createDriver("Driver3", 24, Gender.MALE, "Swift", "KA-O1-12345", Coordinates.builder().xCoordinate(5).yCoordinate(3).build()));
        drivers.forEach(driverRepository::addDriver);
    }

    public Driver createDriver(String name, int age, Gender gender, String vehicleBrand, String vehicleNumber, Coordinates coordinates) {
        return Driver.builder()
                .name(name)
                .age(age)
                .gender(gender)
                .vehicleBrand(vehicleBrand)
                .vehicleNumber(vehicleNumber)
                .coordinates(coordinates)
                .build();
    }
}
