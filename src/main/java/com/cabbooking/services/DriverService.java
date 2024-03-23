package com.cabbooking.services;

import com.cabbooking.domain.Driver;
import com.cabbooking.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverService {
    private final DriverRepository driverRepository;

    public Driver addDriver(Driver driver) {
        log.debug("DriverService.addDriver call started...");
        Driver savedDrive = driverRepository.addDriver(driver);
        log.debug("DriverService.addDriver call completed...");
        return savedDrive;
    }
}
