package com.wmt.smartparking.dto;

import com.wmt.smartparking.validation.AddGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wmtumanday
 */
@Data
@Builder
public class ParkingLotDto extends BaseDto {

    /**
     * Lot ID (Unique identifier, 50 characters max)
     */
    @Size(max = 50, message = "Lot ID too long (50 characters max only)", groups = AddGroup.class)
    @NotBlank(message = "Lot unique identifier cannot be empty!", groups = AddGroup.class)
    private String lotId;
    /**
     * Location
     */
    @Size(max = 100, message = "Location too long (100 characters max only)", groups = AddGroup.class)
    @NotBlank(message = "Location cannot be empty!", groups = AddGroup.class)
    private String location;
    /**
     * Capacity (Total number of parking spaces)
     */
    @NotNull(message = "Capacity cannot be empty!", groups = AddGroup.class)
    private Integer capacity;
    /**
     * Occupancy and availability of a parking lot
     * 0 = avail : 1 = occupied
     */
    private Integer stateType;


}
