package com.wmt.smartparking.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wmtumanday
 */
@Data
public class ParkingLot {

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
     * Occupied Spaces (Number of currently occupied spaces)
     */
    private Integer occupiedSpace;
    /**
     * Occupancy and availability of a parking lot
     * 0 = avail : 1 = occupied
     */
    private Integer stateType;
    /**
     * Current log user - add
     */
    private String createdBy;
    /**
     * Current log user - edit/delete
     */
    private String updatedBy;
    /**
     * Current log user - add
     */
    private LocalDateTime createdAt;
    /**
     * Current log user - edit/delete
     */
    private LocalDateTime updatedAt;

}
