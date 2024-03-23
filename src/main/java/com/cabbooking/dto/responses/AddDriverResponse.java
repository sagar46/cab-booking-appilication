package com.cabbooking.dto.responses;

import com.cabbooking.dto.CoordinatesDTO;
import com.cabbooking.enums.Gender;
import lombok.Data;

@Data
public class AddDriverResponse {
    private int id;
    private String name;
    private Gender gender;
    private int age;
    private String vehicleBrand;
    private String vehicleNumber;
    private CoordinatesDTO coordinatesDTO;
}
