package com.wmt.smartparking.model;

import lombok.Data;

/**
 * @author wmtumanday
 */
@Data
public class ParkingLot extends BaseModel{

    /**
     * Lot ID (Unique identifier, 50 characters max)
     */
    private Long lotId;
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
    private Integer availableSpace;
    /**
     * Occupancy and availability of a parking lot
     * 0 = avail : 1 = fully occupied
     */
    private Integer stateType;
    private String stateTypeStr;


}
