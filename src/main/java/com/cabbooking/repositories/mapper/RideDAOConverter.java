package com.cabbooking.repositories.mapper;

import com.cabbooking.dao.RideDataDao;
import com.cabbooking.domain.RideData;

public class RideDAOConverter {
    public static RideDataDao convertRideDataToRideDataDAO(RideData rideData) {
        return RideDataDao.builder()
                .username(rideData.getUsername())
                .driver_name(rideData.getDriverName())
                .build();
    }

    public static RideData convertRideDAOToRideData(RideDataDao rideDataDao) {
        return RideData.builder()
                .id(rideDataDao.getId())
                .username(rideDataDao.getUsername())
                .driverName(rideDataDao.getDriver_name())
                .build();
    }
}
