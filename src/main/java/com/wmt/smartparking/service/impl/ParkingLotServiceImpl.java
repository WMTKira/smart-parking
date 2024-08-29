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
        return  PageInfo.of(parkingLotList);
    }

    @Override
    public int addParkingLot(ParkingLotDto parkingLotDto) {
        //set the user info temporarily "UserAdmin" since no API authentication is out of scope.
        parkingLotDto.setCreatedBy("UserAdmin");
        parkingLotDto.setUpdatedBy("UserAdmin");
        return parkingLotMapper.insertParkingLot(parkingLotDto);
    }
}
