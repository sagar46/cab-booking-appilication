package com.cabbooking.store;

import com.cabbooking.dao.DriverDAO;
import com.cabbooking.domain.Driver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DriverStore {
    List<DriverDAO> driverStore;

    public DriverDAO addDriverToStore(DriverDAO driverDAO){
        if (!driverStore.isEmpty()){
            driverDAO.setId(driverStore.get(driverStore.size()-1).getId());
        }else {
            driverDAO.setId(1);
        }
        driverStore.add(driverDAO);
        return driverDAO;
    }

    public List<DriverDAO> getAllDriver(){
        return driverStore;
    }
}