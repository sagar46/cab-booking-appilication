package com.cabbooking.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoordinatesDao {
    private int xCoordinate;
    private int yCoordinate;
}
