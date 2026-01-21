package com.api.constant;

public enum Service_Location {

    SERVICE_LOCATION_A(0),SERVICE_LOCATION_B(2);
    int code;


    private Service_Location(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
