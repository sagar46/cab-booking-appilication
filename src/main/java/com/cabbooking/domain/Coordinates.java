package com.cabbooking.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinates {
    private int xCoordinate;
    private int yCoordinate;
}
