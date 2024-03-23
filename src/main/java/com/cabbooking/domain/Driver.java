package com.cabbooking.domain;

import com.cabbooking.dto.CoordinatesDTO;
import com.cabbooking.enums.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Driver {
    private int id;
    private String name;
    private Gender gender;
    private int age;
    private String vehicleBrand;
    private String vehicleNumber;
    private Coordinates coordinates;
}
