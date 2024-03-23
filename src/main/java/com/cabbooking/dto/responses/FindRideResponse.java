package com.cabbooking.dto.responses;

import com.cabbooking.dto.DriverDTO;
import lombok.Data;

import java.util.List;

@Data
public class FindRideResponse {
    private List<DriverDTO> availableRides;
}
