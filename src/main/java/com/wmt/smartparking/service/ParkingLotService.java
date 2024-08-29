package com.wmt.smartparking.service;

import com.github.pagehelper.PageInfo;
import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.model.ParkingLot;

import java.util.List;

/**
 * @author wmtumanday
 */
public interface ParkingLotService {

    /**
     * @param smartLotDto
     * @return List<ParkingLot>
     * Viewing current occupancy and availability of a parking lot by passing stateType
     */
    PageInfo<ParkingLot> getParkingLotList(ParkingLotDto smartLotDto);

    /**
     * @param parkingLotDto
     * Registering a parking lot
     */
    int addParkingLot(ParkingLotDto parkingLotDto);
}
