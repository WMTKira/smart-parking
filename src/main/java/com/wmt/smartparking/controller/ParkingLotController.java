package com.wmt.smartparking.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.feign.FinanceFeignClient;
import com.wmt.smartparking.model.ParkingLot;
import com.wmt.smartparking.service.ParkingLotService;
import com.wmt.smartparking.validation.AddGroup;
import com.wmt.smartparking.validation.QueryGroup;
import com.wmt.smartparking.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;


/**
 * @author wmtumanday
 */
@RestController
@Slf4j
@RequestMapping(value = "/smart/parking")
public class ParkingLotController {

    @Resource
    private ParkingLotService parkingLotService;
    @Resource
    private FinanceFeignClient financeFeignClient;
    @Value("${server.port}")
    private String config;

    /**
     * @param parkingLotDto
     * @return List<ParkingLot>
     * Viewing current occupancy and availability of a parking lot by passing stateType
     */
    @PostMapping(value = "/v1/getParkingLotList")
    public ResponseEntity<Object> getParkingLotList(@RequestBody @Validated(QueryGroup.class) ParkingLotDto parkingLotDto) {
        String test = financeFeignClient.test();
        log.info("test config and feign {},{}", config, test);
        List<ParkingLot> dwdwdw= new ArrayList<>();
        log.info("{},{}",parkingLotDto.getPageNum(), parkingLotDto.getPageSize());

        PageInfo<ParkingLot> parkingLotList = parkingLotService.getParkingLotList(parkingLotDto);
        return ResponseVo.success(parkingLotList);
    }

    /**
     * @param parkingLotDto Registering a parking lot
     */
    @PostMapping(value = "/v1/addParkingLot")
    public ResponseEntity<Object> addParkingLot(@RequestBody @Validated(AddGroup.class) ParkingLotDto parkingLotDto) {
        int succeed = parkingLotService.addParkingLot(parkingLotDto);
        return succeed > 0 ? ResponseVo.success() : ResponseVo.fail();
    }

}
