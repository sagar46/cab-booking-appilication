package com.cabbooking.controllers.mapper;

import com.cabbooking.domain.Coordinates;
import com.cabbooking.domain.Driver;
import com.cabbooking.dto.CoordinatesDTO;
import com.cabbooking.dto.DriverDTO;
import com.cabbooking.dto.requests.AddDriverRequest;
import com.cabbooking.dto.responses.AddDriverResponse;

public class DriverDTOConverter {

    public static Driver convertAddDriverRequestToDriver(AddDriverRequest addDriverRequest) {
        return Driver.builder()
                .name(addDriverRequest.getName())
                .gender(addDriverRequest.getGender())
                .age(addDriverRequest.getAge())
                .vehicleBrand(addDriverRequest.getVehicleBrand())
                .vehicleNumber(addDriverRequest.getVehicleNumber())
                .coordinates(convertCoordinateDTOToCoordinate(addDriverRequest.getCoordinatesDTO()))
                .isOccupied(false)
                .build();
    }

    public static Driver convertDriverDTOToDriver(DriverDTO driverDTO) {
        return Driver.builder()
                .name(driverDTO.getName())
                .gender(driverDTO.getGender())
                .age(driverDTO.getAge())
                .vehicleBrand(driverDTO.getVehicleBrand())
                .vehicleNumber(driverDTO.getVehicleNumber())
                .coordinates(convertCoordinateDTOToCoordinate(driverDTO.getCoordinatesDTO()))
                .isOccupied(driverDTO.isOccupied())
                .build();
    }

    public static DriverDTO convertDriverToDriverDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setName(driver.getName());
        driverDTO.setAge(driver.getAge());
        driverDTO.setGender(driver.getGender());
        driverDTO.setVehicleBrand(driver.getVehicleBrand());
        driverDTO.setVehicleNumber(driver.getVehicleNumber());
        driverDTO.setCoordinatesDTO(convertCoordinatesToCoordinatesDTO(driver.getCoordinates()));
        driverDTO.setOccupied(driver.isOccupied());
        return driverDTO;
    }

    public static AddDriverResponse convertDriverToAddDriverResponse(Driver driver) {
        AddDriverResponse addDriverResponse = new AddDriverResponse();
        addDriverResponse.setId(driver.getId());
        addDriverResponse.setName(driver.getName());
        addDriverResponse.setGender(driver.getGender());
        addDriverResponse.setAge(driver.getAge());
        addDriverResponse.setVehicleBrand(driver.getVehicleBrand());
        addDriverResponse.setVehicleNumber(driver.getVehicleNumber());
        addDriverResponse.setCoordinatesDTO(convertCoordinatesToCoordinatesDTO(driver.getCoordinates()));
        return addDriverResponse;
    }

    public static CoordinatesDTO convertCoordinatesToCoordinatesDTO(Coordinates coordinates) {
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setXCoordinates(coordinates.getXCoordinate());
        coordinatesDTO.setYCoordinates(coordinates.getYCoordinate());
        return coordinatesDTO;
    }

    public static Coordinates convertCoordinateDTOToCoordinate(CoordinatesDTO coordinatesDTO) {
        return Coordinates.builder()
                .xCoordinate(coordinatesDTO.getXCoordinates())
                .yCoordinate(coordinatesDTO.getYCoordinates())
                .build();
    }
}
