package com.example.jumping_i.sudoku_original.retrofit.serverInterface;

public abstract class BaseServerInterface {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    protected String BASE_URL = "http://api2.sktelecom.com/";

    /*******************************************************************************
     * Getter.
     *******************************************************************************/
    public String getBaseUrl() {
        return BASE_URL;
    }
}
