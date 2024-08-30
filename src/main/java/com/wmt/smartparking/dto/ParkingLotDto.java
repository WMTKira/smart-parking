package com.wmt.smartparking.dto;

import com.wmt.smartparking.validation.AddGroup;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wmtumanday
 */
@Data
@Builder
public class ParkingLotDto extends BaseDto {

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
