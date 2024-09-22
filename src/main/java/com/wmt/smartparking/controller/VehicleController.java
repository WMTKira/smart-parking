package com.wmt.smartparking.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.wmt.smartparking.dto.VehicleDto;
import com.wmt.smartparking.model.Vehicle;
import com.wmt.smartparking.service.VehicleService;
import com.wmt.smartparking.validation.AddGroup;
import com.wmt.smartparking.validation.CheckInGroup;
import com.wmt.smartparking.validation.CheckOutGroup;
import com.wmt.smartparking.validation.QueryGroup;
import com.wmt.smartparking.vo.ResponseVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wmtumanday
 */
@RestController
@RequestMapping(value = "/smart/parking")
public class VehicleController {

    @Resource
    private VehicleService vehicleService;

    /**
     * @param vehicleDto
     * @return List<Vehicle>
     * Viewing all vehicles currently parked in a lot
     */
    @GetMapping(value = "/v1/getVehicleList")
    public ResponseEntity<Object> getVehicleList(@RequestBody @Validated(QueryGroup.class) VehicleDto vehicleDto) {
        PageHelper.startPage(vehicleDto.getPageNum(), vehicleDto.getPageSize());
        PageInfo<Vehicle> vehiclePageInfo = vehicleService.getVehicleList(vehicleDto);
        return ResponseVo.success(vehiclePageInfo);
    }

    /**
     * @return map of vehicle types
     * For vehicle type selection used on Registering a vehicle
     */
    @GetMapping(value = "/v1/getVehicleTypeMap")
    public ResponseEntity<Object> getVehicleTypeMap() {
        Map<String, Integer> vehicleTypeMap = vehicleService.getVehicleTypeMap();
        return ResponseVo.success(vehicleTypeMap);
    }

    /**
     * @param vehicleDto Registering a vehicle
     */
    @PostMapping(value = "/v1/addVehicle")
    public ResponseEntity<Object> addVehicle(@RequestBody @Validated(AddGroup.class) VehicleDto vehicleDto) {
        int succeed = vehicleService.addVehicle(vehicleDto);
        return succeed > 0 ? ResponseVo.success() : ResponseVo.fail();
    }

    /**
     * @param vehicleDto Checking in a vehicle to a parking lot
     */
    @PostMapping(value = "/v1/checkInVehicle")
    public ResponseEntity<Object> checkInVehicle(@RequestBody @Validated(CheckInGroup.class) VehicleDto vehicleDto) {
        vehicleDto.setOperateType(0);
        int succeed = vehicleService.checkInOutVehicle(vehicleDto);
        return succeed > 0 ? ResponseVo.success() : ResponseVo.fail();
    }


    /**
     * @param vehicleDto Checking out a vehicle from a parking lot
     */
    @PostMapping(value = "/v1/checkOutVehicle")
    public ResponseEntity<Object> checkOutVehicle(@RequestBody @Validated(CheckOutGroup.class) VehicleDto vehicleDto) {
        vehicleDto.setOperateType(1);
        int succeed = vehicleService.checkInOutVehicle(vehicleDto);
        return succeed > 0 ? ResponseVo.success() : ResponseVo.fail();
    }


}
