package com.wmt.smartparking.mapper;

import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.model.ParkingLot;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wmtumanday
 */
@Mapper
public interface ParkingLotMapper {

    List<ParkingLot> queryParkingLotList(ParkingLotDto parkingLotDto);

    int insertParkingLot(ParkingLotDto parkingLotDto);

    int checkLocation(String location);

}
