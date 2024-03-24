package com.cabbooking.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RideDataDao {
    private int id;
    private String username;
    private String driver_name;

}
