package com.cabbooking.services.impl;

import com.cabbooking.domain.Driver;
import com.cabbooking.repositories.DriverRepository;
import com.cabbooking.services.interfaces.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    @Override
    public Driver addDriver(Driver driver) {
        log.debug("DriverServiceImpl.addDriver call started...");
        Driver savedDrive = driverRepository.addDriver(driver);
        log.debug("DriverServiceImpl.addDriver call completed...");
        return savedDrive;
    }

    @Override
    public List<Driver> getAllDriver() {
        log.debug("DriverServiceImpl.getAllDriver call started...");
        List<Driver> drivers = driverRepository.getAllDrivers();
        log.debug("DriverServiceImpl.getAllDriver call completed...");
        return drivers;
    }
}
