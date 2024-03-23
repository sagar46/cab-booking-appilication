package com.cabbooking.dto.requests;

import com.cabbooking.dto.CoordinatesDTO;
import lombok.Data;

@Data
public class FindRideRequest {
    private String username;
    private CoordinatesDTO source;
    private CoordinatesDTO destination;
}
