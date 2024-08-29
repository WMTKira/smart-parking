package com.wmt.smartparking.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.wmt.smartparking.dto.VehicleDto;
import com.wmt.smartparking.model.Vehicle;
import com.wmt.smartparking.service.VehicleService;
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
@RequestMapping(value = "/smart/vehicle")
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
        PageMethod.startPage(vehicleDto.getPageNum(), vehicleDto.getPageSize());
        PageInfo<Vehicle> vehiclePageInfo = vehicleService.getVehicleList(vehicleDto);
        return ResponseVo.success(vehiclePageInfo);
    }


    /**
     * @param vehicleDto
     * Registering a vehicle
     */
    @PostMapping(value = "/v1/addVehicle")
    public ResponseEntity<Object> addVehicle(@RequestBody @Validated(AddGroup.class) VehicleDto vehicleDto) {
        int succeed = vehicleService.addVehicle(vehicleDto);
        return succeed > 0 ? ResponseVo.success() : ResponseVo.fail();
    }

    /**
     * @param vehicleDto
     * Checking in a vehicle to a parking lot
     */
    @PostMapping(value = "/v1/checkInVehicle")
    public ResponseEntity<Object> checkInVehicle(@RequestBody @Validated(AddGroup.class) VehicleDto vehicleDto) {
        vehicleDto.setOperateType(0);
        int succeed = vehicleService.checkInOutVehicle(vehicleDto);
        return succeed > 0 ? ResponseVo.success() : ResponseVo.fail();
    }


    /**
     * @param vehicleDto
     * Checking out a vehicle from a parking lot
     */
    @PostMapping(value = "/v1/checkOutVehicle")
    public ResponseEntity<Object> checkOutVehicle(@RequestBody @Validated(AddGroup.class) VehicleDto vehicleDto) {
        vehicleDto.setOperateType(1);
        int succeed = vehicleService.checkInOutVehicle(vehicleDto);
        return succeed > 0 ? ResponseVo.success() : ResponseVo.fail();
    }


}
