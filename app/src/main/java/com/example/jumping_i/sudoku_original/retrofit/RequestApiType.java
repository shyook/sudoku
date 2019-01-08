package com.example.jumping_i.sudoku_original.retrofit;

/**
 * API 타입을 enum 정의.
 */
public enum RequestApiType {
    REQUEST_NONE(0x0000, "")
    , REQUEST_WEATHER_HOURLY(0x0001, "weather/current/Hourly");

    private int mCode;
    private String mUrl;

    RequestApiType(int code, String url) {
        mCode = code;
    }

    public int getCode() {
        return mCode;
    }

    public String getUrl() {
        return mUrl;
    }
}
