package com.wmt.smartparking.service.impl;

import com.github.pagehelper.PageInfo;
import com.wmt.smartparking.dto.VehicleDto;
import com.wmt.smartparking.model.Vehicle;
import com.wmt.smartparking.service.VehicleService;
import org.springframework.stereotype.Service;

/**
 * @author wmtumanday
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Override
    public PageInfo<Vehicle> getVehicleList(VehicleDto vehicleDto) {
        return null;
    }

    @Override
    public int addVehicle(VehicleDto vehicleDto) {
        return 1;
    }

    @Override
    public int checkInOutVehicle(VehicleDto vehicleDto) {
        return 0;
    }

}
