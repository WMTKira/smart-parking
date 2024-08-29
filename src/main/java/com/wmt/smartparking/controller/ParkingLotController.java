package com.wmt.smartparking.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.model.ParkingLot;
import com.wmt.smartparking.service.ParkingLotService;
import com.wmt.smartparking.validation.AddGroup;
import com.wmt.smartparking.validation.QueryGroup;
import com.wmt.smartparking.vo.ResponseVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
 * @author wmtumanday
 */
@RestController
@RequestMapping(value = "/smartPark")
public class ParkingLotController {

    @Resource
    private ParkingLotService parkingLotService;

    /**
     * @param parkingLotDto
     * @return List<ParkingLot>
     * Viewing current occupancy and availability of a parking lot by passing stateType
     */
    @GetMapping(value = "/v1/getParkingLotList")
    public ResponseEntity<Object> getParkingLotList(@RequestBody @Validated(QueryGroup.class) ParkingLotDto parkingLotDto) {
        PageMethod.startPage(parkingLotDto.getPageNum(), parkingLotDto.getPageSize());
        PageInfo<ParkingLot> parkingLotList = parkingLotService.getParkingLotList(parkingLotDto);
        return ResponseVo.success(parkingLotList);
    }

    /**
     * @param parkingLotDto
     * Registering a parking lot
     */
    @PostMapping(value = "/v1/addParkingLot")
    public ResponseEntity<Object> addParkingLot(@RequestBody @Validated(AddGroup.class) ParkingLotDto parkingLotDto) {
        int succeed = parkingLotService.addParkingLot(parkingLotDto);
        return succeed > 0 ? ResponseVo.success() : ResponseVo.fail();
    }

}
