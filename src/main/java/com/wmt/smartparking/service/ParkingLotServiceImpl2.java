package com.wmt.smartparking.service;

import com.github.pagehelper.PageInfo;
import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.model.ParkingLot;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl2 implements ParkingLotService {
    /**
     * @param smartLotDto
     * @return List<ParkingLot>
     * Viewing current occupancy and availability of a parking lot by passing stateType
     */
    @Override
    public PageInfo<ParkingLot> getParkingLotList(ParkingLotDto smartLotDto) {
        return null;
    }

    /**
     * @param parkingLotDto Registering a parking lot
     */
    @Override
    public int addParkingLot(ParkingLotDto parkingLotDto) {
        return 0;
    }
}
