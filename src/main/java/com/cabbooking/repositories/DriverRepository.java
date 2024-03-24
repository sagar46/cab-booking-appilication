package com.cabbooking.repositories;

import com.cabbooking.dao.DriverDAO;
import com.cabbooking.domain.Driver;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.DriverNotFoundException;
import com.cabbooking.repositories.mapper.DriverDAOConverter;
import com.cabbooking.store.DriverStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverRepository {
    private final DriverStore driverStore;


    public Driver addDriver(Driver driver) {
        log.debug("DriverRepository.addDriver call started...");
        DriverDAO savedDriver = saveDriver(DriverDAOConverter.convertDriverToDriverDAO(driver));
        log.debug("DriverRepository.addDriver call completed...");
        return DriverDAOConverter.convertDriverDAOToDriver(savedDriver);
    }

    public DriverDAO saveDriver(DriverDAO driverDAO) {
        return driverStore.addDriverToStore(driverDAO);
    }

    public void updateDriver(Driver driver) {
        log.debug("DriverRepository.updateDriver call started...");
        driverStore.updateDriverStatus(driver.getName());
        log.debug("DriverRepository.updateDriver call completed...");
    }

    public Driver getDriverByDriverName(String driverName) {
        log.debug("DriverRepository.getDriverByDriverName call started...");
      DriverDAO driver = driverStore.findDriverByUsername(driverName);
        log.debug("DriverRepository.getDriverByDriverName call completed...");
      return DriverDAOConverter.convertDriverDAOToDriver(driver);
    }

    public List<Driver> getAllDrivers() {
        log.debug("DriverRepository.getAllDrivers call started...");
        List<Driver> drivers = getDrivers().stream().map(DriverDAOConverter::convertDriverDAOToDriver).toList();
        log.debug("DriverRepository.getAllDrivers call completed...");
        return drivers;
    }

    public List<DriverDAO> getDrivers() {
        return driverStore.getAllDriver();
    }
}
