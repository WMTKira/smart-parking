package com.wmt.smartparking.model;

import lombok.Data;

/**
 * @author wmtumanday
 */
@Data
public class Vehicle extends BaseModel {

    /**
     * License Plate
     */
    private String plateId;
    /**
     * Owner Name
     */
    private String ownerName;
    /**
     * Type (Car, Motorcycle, Truck)
     */
    private Integer vehicleType;
    private String vehicleTypeStr;
    /**
     * Size of the vehicle
     */
    private double vehicleSize;
    /**
     * Lot ID
     */
    private String lotId;

}
