package com.cabbooking.repositories.mapper;

import com.cabbooking.dao.CoordinatesDao;
import com.cabbooking.dao.DriverDAO;
import com.cabbooking.domain.Coordinates;
import com.cabbooking.domain.Driver;

public class DriverDAOConverter {
    public static DriverDAO convertDriverToDriverDAO(Driver driver){
        return DriverDAO.builder()
                .name(driver.getName())
                .age(driver.getAge())
                .gender(driver.getGender())
                .vehicleBrand(driver.getVehicleBrand())
                .vehicleNumber(driver.getVehicleNumber())
                .coordinatesDao(convertCoordinatesToCoordinatesDAO(driver.getCoordinates()))
                .build();
    }
    public static Driver convertDriverDAOToDriver(DriverDAO driverDAO){
        return Driver.builder()
                .id(driverDAO.getId())
                .name(driverDAO.getName())
                .age(driverDAO.getAge())
                .gender(driverDAO.getGender())
                .vehicleNumber(driverDAO.getVehicleNumber())
                .vehicleBrand(driverDAO.getVehicleBrand())
                .coordinates(convertCoordinatesDAOToCoordinates(driverDAO.getCoordinatesDao()))
                .build();
    }
    private static Coordinates convertCoordinatesDAOToCoordinates(CoordinatesDao coordinatesDao){
        return Coordinates.builder()
                .xCoordinate(coordinatesDao.getXCoordinate())
                .yCoordinate(coordinatesDao.getYCoordinate())
                .build();
    }
    private static CoordinatesDao convertCoordinatesToCoordinatesDAO(Coordinates coordinates){
        return CoordinatesDao.builder()
                .xCoordinate(coordinates.getXCoordinate())
                .yCoordinate(coordinates.getYCoordinate())
                .build();
    }
}
