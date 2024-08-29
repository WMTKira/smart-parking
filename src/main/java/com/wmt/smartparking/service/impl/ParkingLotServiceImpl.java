package com.wmt.smartparking.service.impl;

import com.github.pagehelper.PageInfo;
import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.mapper.ParkingLotMapper;
import com.wmt.smartparking.model.ParkingLot;
import com.wmt.smartparking.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wmtumanday
 */
@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Override
    public PageInfo<ParkingLot> getParkingLotList(ParkingLotDto parkingLotDto) {
        List<ParkingLot> parkingLotList = parkingLotMapper.queryParkingLotList(parkingLotDto);
        parkingLotList.forEach(p -> {
            p.setStateTypeStr(Integer.valueOf(0).equals(p.getStateType()) ? "With available slot" : "Occupied");
            p.setAvailableSpace(p.getCapacity() - parkingLotMapper.countVehiclePerLot(p.getLotId()));
        });
        return  PageInfo.of(parkingLotList);
    }

    @Override
    public int addParkingLot(ParkingLotDto parkingLotDto) {
        return parkingLotMapper.insertParkingLot(parkingLotDto);
    }
}
