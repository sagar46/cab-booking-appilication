package com.cabbooking.repositories;

import com.cabbooking.dao.DriverDAO;
import com.cabbooking.dao.RideDataDao;
import com.cabbooking.domain.Driver;
import com.cabbooking.domain.RideData;
import com.cabbooking.exception.DriverNotFoundException;
import com.cabbooking.repositories.mapper.DriverDAOConverter;
import com.cabbooking.repositories.mapper.RideDAOConverter;
import com.cabbooking.store.RideStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RideRepository {
    private final DriverRepository driverRepository;
    private final RideStore rideStore;

    @Value("${driver.error.no-driver}")
    private String noDriverErrorMsg;


    public List<Driver> getAllDrivers() {
        log.debug("RideRepository.getAllDrivers call started...");
        List<DriverDAO> driverDAOS = driverRepository.getDrivers();
        if (Objects.isNull(driverDAOS) || driverDAOS.isEmpty()) {
            throw new DriverNotFoundException(noDriverErrorMsg);
        }
        List<Driver> drivers = driverRepository.getDrivers().stream().map(DriverDAOConverter::convertDriverDAOToDriver).toList();
        log.debug("RideRepository.getAllDrivers call completed...");
        return drivers;
    }

    public void chooseRide(RideData rideData) {
        log.debug("RideRepository.chooseRide call started...");
        RideDataDao savedRide = saveRide(RideDAOConverter.convertRideDataToRideDataDAO(rideData));
        log.debug("RideRepository.chooseRide call completed...");
        RideDAOConverter.convertRideDAOToRideData(savedRide);
    }

    public void completeRide(RideData rideData) {
        log.debug("RideRepository.completeRide call started...");
        rideStore.completeRide(rideData);
        log.debug("RideRepository.completeRide call completed...");
    }

    public RideDataDao saveRide(RideDataDao rideDataDao) {
        return rideStore.saveRideData(rideDataDao);

    }
}
