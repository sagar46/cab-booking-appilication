package com.cabbooking.repositories;

import com.cabbooking.dao.DriverDAO;
import com.cabbooking.domain.Driver;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.repositories.mapper.DriverDAOConverter;
import com.cabbooking.store.DriverStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

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
        List<DriverDAO> driverDAOS = getAllDriver();
        driverDAOS.forEach(driverExist -> {
            if (Objects.equals(driverExist.getName(), driverDAO.getName())) {
                throw new CabBookingException("Driver is already exist.");
            }
        });
        return driverStore.addDriverToStore(driverDAO);
    }

    public List<DriverDAO> getAllDriver() {
        return driverStore.getAllDriver();
    }
}
