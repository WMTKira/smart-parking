package com.wmt.smartparking.dto;

import com.wmt.smartparking.validation.AddGroup;
import com.wmt.smartparking.validation.QueryGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wmtumanday
 */
@Data
public class ParkingLotDto extends BaseDto{

    /**
     * Lot ID (Unique identifier, 50 characters max)
     */
    private Long lotId;
    /**
     * Location
     */
    @NotBlank(message = "Location cannot be empty!", groups = AddGroup.class)
    private String location;
    /**
     * Capacity (Total number of parking spaces)
     */
    @NotBlank(message = "Capacity cannot be empty!", groups = AddGroup.class)
    private String capacity;
    /**
     * Occupancy and availability of a parking lot
     * 0 = avail : 1 = occupied
     */
    private Integer stateType;



}
