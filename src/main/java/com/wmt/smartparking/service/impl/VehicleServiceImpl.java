package com.wmt.smartparking.service.impl;

import com.github.pagehelper.PageInfo;
import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.dto.VehicleDto;
import com.wmt.smartparking.enums.VehicleTypeEnum;
import com.wmt.smartparking.mapper.ParkingLotMapper;
import com.wmt.smartparking.mapper.VehicleMapper;
import com.wmt.smartparking.model.ParkingLot;
import com.wmt.smartparking.model.Vehicle;
import com.wmt.smartparking.service.VehicleService;
import com.wmt.smartparking.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wmtumanday
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public PageInfo<Vehicle> getVehicleList(VehicleDto vehicleDto) {
        List<Vehicle> vehicleList = vehicleMapper.queryVehicleList(vehicleDto);
        vehicleList.forEach(v -> {
            v.setCarTypeStr(Objects.requireNonNull(VehicleTypeEnum.getInfo(v.getCarType())).getDescription());
        });
        return PageInfo.of(vehicleList);
    }

    @Override
    public int addVehicle(VehicleDto vehicleDto) {
        int countDuplicates = vehicleMapper.countVehicleByPlate(vehicleDto.getPlateId());
        AssertUtil.isFalse(countDuplicates > 0, "Vehicle already Exists!");
        return vehicleMapper.insertVehicle(vehicleDto);
    }

    @Override
    @Transactional
    public int checkInOutVehicle(VehicleDto vehicleDto) {
        Long parkingLotId = null;
        int countExist = vehicleMapper.countVehicleByPlate(vehicleDto.getPlateId());
        AssertUtil.isTrue(countExist > 0, "Vehicle does not Exists!");
        if (Integer.valueOf(0).equals(vehicleDto.getOperateType())) {
            parkingLotId = vehicleMapper.getLotIdByVehicle(vehicleDto);
            AssertUtil.isNull(parkingLotId, "Cant checkin, vehicle already in the parking lot!");
        } else {
            parkingLotId = vehicleMapper.getLotIdByVehicle(vehicleDto);
            AssertUtil.notNull(parkingLotId, "Cant checkout, vehicle not in the parking lot!");
        }
        int updateSuccess = vehicleMapper.updateVehicle(vehicleDto);
        return updateSuccess;
    }

}
