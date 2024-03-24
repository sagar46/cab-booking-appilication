package com.cabbooking.dto.requests;

import lombok.Data;

@Data
public class CompleteRideRequest {
    private String username;
    private String driverName;
}
