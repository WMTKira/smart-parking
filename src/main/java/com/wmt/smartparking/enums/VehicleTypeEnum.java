package com.wmt.smartparking.enums;

/**
 * @author wmtumanday
 */

public enum VehicleTypeEnum {

    CAR(0,"Car"),
    MOTORCYCLE (1,"Motorcycle") ,
    TRUCK(2, "Truck");

    private int code;
    private String description;

    VehicleTypeEnum(int i, String truck) {
        this.code = i;
        this.description = truck;
    }

    public static VehicleTypeEnum getInfo(int code) {
        for (VehicleTypeEnum type : VehicleTypeEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
