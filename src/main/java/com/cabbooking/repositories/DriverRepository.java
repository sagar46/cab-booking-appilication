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
    @Value("${driver.error.already-exist}")
    private String driverAlreadyExistMsg;
    @Value("${driver.error.not-found}")
    private String driverNotFoundMsg;

    public Driver addDriver(Driver driver) {
        log.debug("DriverRepository.addDriver call started...");
        DriverDAO savedDriver = saveDriver(DriverDAOConverter.convertDriverToDriverDAO(driver));
        log.debug("DriverRepository.addDriver call completed...");
        return DriverDAOConverter.convertDriverDAOToDriver(savedDriver);
    }

    public DriverDAO saveDriver(DriverDAO driverDAO) {
        List<DriverDAO> driverDAOS = getDrivers();
        driverDAOS.forEach(driverExist -> {
            if (Objects.equals(driverExist.getName(), driverDAO.getName())) {
                throw new CabBookingException(driverAlreadyExistMsg);
            }
        });
        return driverStore.addDriverToStore(driverDAO);
    }

    public void updateDriver(Driver driver) {
        log.debug("DriverRepository.updateDriver call started...");
        List<DriverDAO> allDrivers = getDrivers();
        allDrivers.forEach(driverDAO -> {
            if (Objects.equals(driverDAO.getName(), driver.getName())) {
                if (driverDAO.isOccupied()) {
                    driverDAO.setOccupied(false);
                } else {
                    driverDAO.setOccupied(true);
                }
            }
        });
        log.debug("DriverRepository.updateDriver call completed...");
    }

    public Driver getDriverByDriverName(String driverName) {
        List<DriverDAO> driverDAOS = getDrivers();
        AtomicReference<DriverDAO> driverDAOA = new AtomicReference<>(DriverDAO.builder().build());
        driverDAOS.forEach(driverDAO -> {
            if (driverDAO.getName().equals(driverName)) {
                driverDAOA.set(driverDAO);
            }
        });
        if (Objects.isNull(driverDAOA.get())) {
            throw new DriverNotFoundException(driverNotFoundMsg);
        }
        return DriverDAOConverter.convertDriverDAOToDriver(driverDAOA.get());
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
