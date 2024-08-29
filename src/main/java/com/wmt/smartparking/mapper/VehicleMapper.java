package com.wmt.smartparking.mapper;

import com.wmt.smartparking.dto.VehicleDto;
import com.wmt.smartparking.model.Vehicle;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author wmtumanday
 */
@Mapper
public interface VehicleMapper {

    List<Vehicle> queryVehicleList(VehicleDto vehicleDto);

    int insertVehicle(VehicleDto vehicleDto);

    int countVehiclePerLot(Long lotId);

    int countVehicleByPlate(String plateId);

    Long getLotIdByVehicle(VehicleDto vehicleDto);

    int updateVehicle(VehicleDto vehicleDto);

}
