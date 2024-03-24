package com.cabbooking.store;

import com.cabbooking.dao.DriverDAO;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.DriverNotFoundException;
import com.cabbooking.exception.DuplicateUserOrDriverException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DriverStore {
    private int ID = 1;
    private ConcurrentHashMap<String, DriverDAO> driverStore = new ConcurrentHashMap<>();
    @Value("${driver.error.already-exist}")
    private String driverAlreadyExistMsg;
    @Value("${driver.error.not-found}")
    private String driverNotFoundMsg;

    public DriverDAO addDriverToStore(DriverDAO driverDAO) {
        DriverDAO driverExist = driverStore.get(driverDAO.getName());
        if (Objects.nonNull(driverExist)){
            throw new DuplicateUserOrDriverException(driverAlreadyExistMsg);
        }
        driverDAO.setId(ID++);
        driverStore.put(driverDAO.getName(),driverDAO);
        return driverDAO;
    }
    public DriverDAO findDriverByUsername(String driverName){
        DriverDAO driver = driverStore.get(driverName);
        if (Objects.nonNull(driver)){
            throw new DriverNotFoundException(driverNotFoundMsg);
        }
        return driver;
    }
     public void updateDriverStatus(String driverName){
        DriverDAO driver = driverStore.get(driverName);
        if (Objects.isNull(driver)){
            throw new DriverNotFoundException(driverNotFoundMsg);
        }
        if (driver.isOccupied()){
            driver.setOccupied(false);
            driverStore.put(driver.getName(),driver);
        }else {
            driver.setOccupied(true);
            driverStore.put(driver.getName(),driver);
        }
     }
    public List<DriverDAO> getAllDriver() {
        List<DriverDAO> driversList = new ArrayList<>();
        driverStore.forEach((key,value)->{
            driversList.add(value);
        });
        return driversList;
    }
}
