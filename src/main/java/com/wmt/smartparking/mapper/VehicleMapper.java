package com.wmt.smartparking.mapper;

import com.wmt.smartparking.dto.VehicleDto;
import com.wmt.smartparking.model.Vehicle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wmtumanday
 */
@Mapper
public interface VehicleMapper {

    List<Vehicle> queryVehicleList(VehicleDto vehicleDto);

    int insertVehicle(VehicleDto vehicleDto);

    Vehicle getVehicleByPlate(String plateId);

    String getLotIdByVehicle(VehicleDto vehicleDto);

    int updateVehicle(String plateId, String lotId);

}
