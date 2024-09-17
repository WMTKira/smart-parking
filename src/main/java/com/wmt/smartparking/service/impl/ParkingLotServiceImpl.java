package com.wmt.smartparking.service.impl;

import com.github.pagehelper.PageInfo;
import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.dto.VehicleDto;
import com.wmt.smartparking.enums.VehicleTypeEnum;
import com.wmt.smartparking.mapper.ParkingLotMapper;
import com.wmt.smartparking.mapper.VehicleMapper;
import com.wmt.smartparking.model.ParkingLot;
import com.wmt.smartparking.model.Vehicle;
import com.wmt.smartparking.service.ParkingLotService;
import com.wmt.smartparking.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wmtumanday
 */
@Slf4j
@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public PageInfo<ParkingLot> getParkingLotList(ParkingLotDto parkingLotDto) {
        Math.abs(1);
        List<ParkingLot> parkingLotList = parkingLotMapper.queryParkingLotList(parkingLotDto);
        parkingLotList.forEach(p -> {
            double load = 0;
            List<Vehicle> vehicleList = vehicleMapper.queryVehicleList(VehicleDto.builder().lotId(p.getLotId()).build());
            for (Vehicle vehicle : vehicleList) {
                load += Objects.requireNonNull(VehicleTypeEnum.getInfo(vehicle.getVehicleType())).getSize();
            }
            p.setStateTypeStr(Integer.valueOf(0).equals(p.getStateType()) ? "With available slot" : "Fully occupied");
            p.setAvailableSpace(p.getCapacity() - load);
            Map<String, Boolean> carMapAvail = new HashMap<>();
            for (VehicleTypeEnum vehicleTypeEnum : VehicleTypeEnum.values()) {
                carMapAvail.put(vehicleTypeEnum.getDescription(), p.getAvailableSpace() - vehicleTypeEnum.getSize() >= 0);
            }
            p.setMapVehicleTypeAvail(carMapAvail);
        });
        return PageInfo.of(parkingLotList);
    }

    @Override
    public int addParkingLot(ParkingLotDto parkingLotDto) {
        int lotIdUsageCount = parkingLotMapper.checkLotId(parkingLotDto.getLotId());
        AssertUtil.isFalse(lotIdUsageCount > 0, "Parking lot identifier already exists!");
        int locationUsageCount = parkingLotMapper.checkLocation(parkingLotDto.getLocation());
        AssertUtil.isFalse(locationUsageCount > 0, "Parking lot location already exists!");
        return parkingLotMapper.insertParkingLot(parkingLotDto);
    }
}
