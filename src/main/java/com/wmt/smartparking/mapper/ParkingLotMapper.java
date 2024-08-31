package com.wmt.smartparking.mapper;

import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.model.ParkingLot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wmtumanday
 */
@Mapper
public interface ParkingLotMapper {

    List<ParkingLot> queryParkingLotList(ParkingLotDto parkingLotDto);

    int insertParkingLot(ParkingLotDto parkingLotDto);

    int checkLocation(String location);

    int checkLotId(String lotId);

    void updateParkingLot(int stateType);
}
