package com.cabbooking.repositories;

import com.cabbooking.dao.DriverDAO;
import com.cabbooking.domain.Driver;
import com.cabbooking.repositories.mapper.DriverDAOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DriverRepository {

    public Driver addDriver(Driver driver){
      log.debug("DriverRepository.addDriver call started...");
      DriverDAO savedDriver = saveDriver(DriverDAOConverter.convertDriverToDriverDAO(driver));
      log.debug("DriverRepository.addDriver call completed...");
      return DriverDAOConverter.convertDriverDAOToDriver(savedDriver);
    }

    public DriverDAO saveDriver(DriverDAO driverDAO){
        return driverDAO;
    }
}
