package com.wmt.smartparking.model;

import lombok.Data;

import java.util.Map;

/**
 * @author wmtumanday
 */
@Data
public class ParkingLot extends BaseModel {

    /**
     * Lot ID
     */
    private String lotId;
    /**
     * Location
     */
    private String location;
    /**
     * Capacity (Total number of parking spaces)
     */
    private Integer capacity;
    /**
     * Available Spaces (Number of currently available spaces)
     */
    private double availableSpace;
    /**
     * Occupancy and availability of a parking lot
     * 0 = avail : 1 = fully occupied
     */
    private Integer stateType;
    private String stateTypeStr;

    /**
     * Map of vehicle type that can check in
     */
    private Map<String, Boolean> mapVehicleTypeAvail;


}
