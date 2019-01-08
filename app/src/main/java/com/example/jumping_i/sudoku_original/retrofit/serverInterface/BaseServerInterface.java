package com.example.jumping_i.sudoku_original.retrofit.serverInterface;

public abstract class BaseServerInterface {
    protected String BASE_URL = "http://api2.sktelecom.com/";

    public String getBaseUrl() {
        return BASE_URL;
    }
}
