package com.wmt.smartparking.dto;

import com.wmt.smartparking.validation.AddGroup;
import com.wmt.smartparking.validation.QueryGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author wmtumanday
 */
@Data
public class VehicleDto extends BaseDto{

    /**
     * License Plate (Unique identifier, allowed only letters, numbers, and dashes)
     */
    @NotBlank(message = "License Plate cannot be empty!", groups = AddGroup.class)
    @Pattern(regexp = "^(\\w|(?<=\\w)-(?=\\w))+$", message = "Invalid License Plate!", groups = AddGroup.class)
    private String plateId;
    /**
     * Owner Name (Letters and spaces only)
     */
    @NotBlank(message = "Location cannot be empty!", groups = AddGroup.class)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Invalid Owner Name!", groups = AddGroup.class)
    private String ownerName;
    /**
     * Type (Car, Motorcycle, Truck)
     */
    @NotNull(message = "Type cannot be empty!", groups = AddGroup.class)
    private Integer carType;
    /**
     * Checking in a vehicle to a parking lot
     * Checking out a vehicle from a parking lot
     * 0 = check in : 1 = check out
     */
    private Integer operateType;
    /**
     * Lot ID (Unique identifier, 50 characters max)
     */
    private Long lotId;

}
