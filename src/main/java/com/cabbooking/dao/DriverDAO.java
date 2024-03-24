package com.cabbooking.dao;

import com.cabbooking.enums.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverDAO {
    private int id;
    private String name;
    private Gender gender;
    private int age;
    private String vehicleBrand;
    private String vehicleNumber;
    private CoordinatesDao coordinatesDao;
    private boolean isOccupied;
}
