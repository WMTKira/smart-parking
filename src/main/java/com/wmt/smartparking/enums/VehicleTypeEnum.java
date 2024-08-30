package com.wmt.smartparking.enums;

/**
 * @author wmtumanday
 */

public enum VehicleTypeEnum {

    MOTORCYCLE(0, "Motorcycle", 0.5),
    CAR(1, "Car", 1),
    TRUCK(2, "Truck", 5);

    private int code;
    private String description;
    private double size;

    VehicleTypeEnum(int i, String description, double size) {
        this.code = i;
        this.description = description;
        this.size = size;
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

    public double getSize() {
        return size;
    }

}
