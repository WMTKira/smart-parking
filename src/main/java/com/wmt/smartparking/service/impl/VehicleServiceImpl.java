package com.wmt.smartparking.service.impl;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xml.internal.utils.StringToStringTableVector;
import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.dto.VehicleDto;
import com.wmt.smartparking.enums.VehicleTypeEnum;
import com.wmt.smartparking.mapper.ParkingLotMapper;
import com.wmt.smartparking.mapper.VehicleMapper;
import com.wmt.smartparking.model.ParkingLot;
import com.wmt.smartparking.model.Vehicle;
import com.wmt.smartparking.service.ParkingLotService;
import com.wmt.smartparking.service.VehicleService;
import com.wmt.smartparking.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wmtumanday
 */
@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public PageInfo<Vehicle> getVehicleList(VehicleDto vehicleDto) {
        List<Vehicle> vehicleList = vehicleMapper.queryVehicleList(vehicleDto);
        vehicleList.forEach(v -> {
            v.setVehicleTypeStr(Objects.requireNonNull(VehicleTypeEnum.getInfo(v.getVehicleType())).getDescription());
            v.setVehicleSize(Objects.requireNonNull(VehicleTypeEnum.getInfo(v.getVehicleType())).getSize());
        });
        return PageInfo.of(vehicleList);
    }

    @Override
    public int addVehicle(VehicleDto vehicleDto) {
        AssertUtil.notNull(VehicleTypeEnum.getInfo(vehicleDto.getVehicleType()), "Invalid vehicle type!");
        Vehicle checkDuplicate = vehicleMapper.getVehicleByPlate(vehicleDto.getPlateId());
        AssertUtil.isNull(checkDuplicate, "Vehicle already exists!");
        return vehicleMapper.insertVehicle(vehicleDto);
    }

    @Override
    public int checkInOutVehicle(VehicleDto vehicleDto) {
        String parkingLotId;
        Vehicle checkExist = vehicleMapper.getVehicleByPlate(vehicleDto.getPlateId());
        AssertUtil.notNull(checkExist, "Vehicle information does not exists, please perform registration to proceed!");
        if (Integer.valueOf(0).equals(vehicleDto.getOperateType())) {
            parkingLotId = vehicleMapper.getLotIdByVehicle(vehicleDto);
            AssertUtil.isBlank(parkingLotId, String.format("Check-in failed, same vehicle information already in the parking lot %s!", parkingLotId));
            AssertUtil.isTrue(
                    getParkingLotInfo(vehicleDto.getLotId()).getAvailableSpace()
                            >= Objects.requireNonNull(VehicleTypeEnum.getInfo(checkExist.getVehicleType())).getSize(),
                    "Parking lot is fully occupied or can not accommodate the size of vehicle!"
            );
            parkingLotId = vehicleDto.getLotId();
        } else {
            parkingLotId = vehicleMapper.getLotIdByVehicle(vehicleDto);
            AssertUtil.notBlank(parkingLotId, "Check-out failed, no check-in information found!");
            vehicleDto.setLotId(parkingLotId);
            parkingLotId = "";
        }
        int updateSuccess = vehicleMapper.updateVehicle(vehicleDto.getPlateId(), parkingLotId);
        if (updateSuccess > 0) {
            ParkingLot parkingLot = getParkingLotInfo(vehicleDto.getLotId());
            int parkingLotState = parkingLot.getAvailableSpace() == 0 ? 1 : 0;
            parkingLotMapper.updateParkingLot(parkingLotState);
        }
        return updateSuccess;
    }

    @Override
    public Map<String, Integer> getVehicleTypeMap() {
        return Arrays.stream(VehicleTypeEnum.values())
                .collect(Collectors.toMap(
                        VehicleTypeEnum::getDescription,
                        VehicleTypeEnum::getCode)
                );
    }

    private ParkingLot getParkingLotInfo(String lotId) {
        List<ParkingLot> parkingLotList = parkingLotService.getParkingLotList(ParkingLotDto.builder().lotId(lotId).build()).getList();
        AssertUtil.isFalse(CollectionUtils.isEmpty(parkingLotList), "Parking lot not found!");
        return parkingLotList.get(0);
    }

}
