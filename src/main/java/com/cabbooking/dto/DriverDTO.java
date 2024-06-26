package com.cabbooking.dto;

import com.cabbooking.enums.Gender;
import lombok.Data;

@Data
public class DriverDTO {
    private int id;
    private String name;
    private Gender gender;
    private int age;
    private String vehicleBrand;
    private String vehicleNumber;
    private CoordinatesDTO coordinatesDTO;
    private boolean isOccupied;
}
