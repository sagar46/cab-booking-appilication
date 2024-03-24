package com.cabbooking.store;

import com.cabbooking.dao.RideDataDao;
import com.cabbooking.domain.RideData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RideStore {
    List<RideDataDao> rideDataDaoStore;
    private AtomicInteger ID = new AtomicInteger(1);
    public RideStore(List<RideDataDao> rideDataDaoStore) {
        this.rideDataDaoStore = rideDataDaoStore;
    }

    public RideDataDao saveRideData(RideDataDao rideDataDao) {
        rideDataDao.setId(ID.incrementAndGet());
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
