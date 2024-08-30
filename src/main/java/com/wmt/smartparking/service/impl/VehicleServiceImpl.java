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
import com.wmt.smartparking.service.VehicleService;
import com.wmt.smartparking.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author wmtumanday
 */
@Service
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
            v.setCarTypeStr(Objects.requireNonNull(VehicleTypeEnum.getInfo(v.getCarType())).getDescription());
            v.setVehicleSize(Objects.requireNonNull(VehicleTypeEnum.getInfo(v.getCarType())).getSize());
        });
        return PageInfo.of(vehicleList);
    }

    @Override
    public int addVehicle(VehicleDto vehicleDto) {
        Vehicle checkDuplicate = vehicleMapper.countVehicleByPlate(vehicleDto.getPlateId());
        AssertUtil.isNull(checkDuplicate, "Vehicle already Exists!");
        return vehicleMapper.insertVehicle(vehicleDto);
    }

    @Override
    public int checkInOutVehicle(VehicleDto vehicleDto) {
        Long parkingLotId = null;
        Vehicle checkExist = vehicleMapper.countVehicleByPlate(vehicleDto.getPlateId());
        AssertUtil.notNull(checkExist, "Vehicle information does not exists, please perform registration to proceed!");
        if (Integer.valueOf(0).equals(vehicleDto.getOperateType())) {
            parkingLotId = vehicleMapper.getLotIdByVehicle(vehicleDto);
            AssertUtil.isNull(parkingLotId, "Check-in failed, same vehicle information already in the parking lot!");
            AssertUtil.isTrue(
                    getParkingLotInfo(vehicleDto.getLotId()).getAvailableSpace() >= Objects.requireNonNull(VehicleTypeEnum.getInfo(checkExist.getCarType())).getSize(),
                    "Parking lot can not accommodate this type of vehicle!"
            );
            parkingLotId = vehicleDto.getLotId();
        } else {
            parkingLotId = vehicleMapper.getLotIdByVehicle(vehicleDto);
            AssertUtil.notNull(parkingLotId, "Check-out failed, no check-in information found!");
            vehicleDto.setLotId(parkingLotId);
            parkingLotId = 0L;
        }
        int updateSuccess = vehicleMapper.updateVehicle(vehicleDto.getPlateId(), parkingLotId);
        if (updateSuccess > 0) {
            ParkingLot parkingLot = getParkingLotInfo(vehicleDto.getLotId());
            int parkingLotState = parkingLot.getAvailableSpace() == 0 ? 1 : 0;
            parkingLotMapper.updateParkingLot(parkingLotState);
        }
        return updateSuccess;
    }

    private ParkingLot getParkingLotInfo(Long lotId) {
        List<ParkingLot> parkingLotList = parkingLotService.getParkingLotList(ParkingLotDto.builder().lotId(lotId).build()).getList();
        AssertUtil.isFalse(CollectionUtils.isEmpty(parkingLotList), "Parking Lot not found");
        return parkingLotList.get(0);
    }

}
