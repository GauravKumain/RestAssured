package com.api.constant;

public enum Platform {

    FST(1), Front_Desk(2);
    int code;

    private Platform(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
