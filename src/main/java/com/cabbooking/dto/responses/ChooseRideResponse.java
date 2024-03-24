package com.cabbooking.dto.responses;

import com.cabbooking.domain.Coordinates;
import lombok.Data;

@Data
public class ChooseRideResponse {
    private String driverName;
    private Coordinates coordinates;
}
