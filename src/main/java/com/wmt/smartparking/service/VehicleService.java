package com.wmt.smartparking.service;

import com.github.pagehelper.PageInfo;
import com.wmt.smartparking.dto.VehicleDto;
import com.wmt.smartparking.model.Vehicle;

/**
 * @author wmtumanday
 */
public interface VehicleService {

    /**
     * @param vehicleDto
     * @return List<Vehicle>
     * Viewing all vehicles currently parked in a lot
     */
    PageInfo<Vehicle> getVehicleList(VehicleDto vehicleDto);

    /**
     * @param vehicleDto
     * Registering a vehicle
     */
    int addVehicle(VehicleDto vehicleDto);

    /**
     * @param vehicleDto
     * Checking in/out a vehicle to a parking lot
     */
    int checkInOutVehicle(VehicleDto vehicleDto);

}
