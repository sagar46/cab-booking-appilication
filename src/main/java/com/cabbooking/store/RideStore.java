package com.cabbooking.store;

import com.cabbooking.dao.RideDataDao;
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
            rideDataDao.setId(rideDataDaoStore.get(rideDataDaoStore.size() - 1).getId());
        } else {
            rideDataDao.setId(1);
        }
        rideDataDaoStore.add(rideDataDao);
        return rideDataDao;
    }
}
