package com.cabbooking.store;

import com.cabbooking.dao.RideDataDao;
import com.cabbooking.domain.RideData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RideStore {
    List<RideDataDao> rideDataDaoStore;

    public RideStore(List<RideDataDao> rideDataDaoStore) {
        this.rideDataDaoStore = rideDataDaoStore;
    }

    public RideDataDao saveRideData(RideDataDao rideDataDao) {
        if (!rideDataDaoStore.isEmpty()) {
            rideDataDao.setId(rideDataDaoStore.get(rideDataDaoStore.size() - 1).getId() + 1);
        } else {
            rideDataDao.setId(1);
        }
        rideDataDaoStore.add(rideDataDao);
        return rideDataDao;
    }

    public void completeRide(RideData rideData) {
        rideDataDaoStore.forEach(rideDataDao -> {
            if (rideDataDao.getUsername().equals(rideData.getUsername()) && rideDataDao.getDriver_name().equals(rideData.getDriverName())) {
                rideDataDaoStore.remove(rideDataDao);
            }
        });
    }
}
