package com.cabbooking.services.interfaces;

import com.cabbooking.domain.Driver;

import java.util.List;

public interface DriverService {
    public Driver addDriver(Driver driver);

    public List<Driver> getAllDriver();
}
