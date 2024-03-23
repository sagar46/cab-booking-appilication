package com.cabbooking.repositories;

import com.cabbooking.dao.DriverDAO;
import com.cabbooking.domain.Driver;
import com.cabbooking.exception.DriverNotFoundException;
import com.cabbooking.repositories.mapper.DriverDAOConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RideRepository {
    private final DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        log.debug("RideRepository.getAllDrivers call started...");
        List<DriverDAO> driverDAOS = driverRepository.getAllDriver();
        if (Objects.isNull(driverDAOS) || driverDAOS.isEmpty()) {
            throw new DriverNotFoundException("No Drivers registered yet.");
        }
        List<Driver> drivers = driverRepository.getAllDriver().stream().map(DriverDAOConverter::convertDriverDAOToDriver).toList();
        log.debug("RideRepository.getAllDrivers call completed...");
        return drivers;
    }
}
