package com.example.jumping_i.sudoku_original.views.history;

public abstract class AbstractResponseSet {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    protected int mResponseCode;
    protected  String mResponseMessage;

    /*******************************************************************************
     * 생성자.
     *******************************************************************************/
    public AbstractResponseSet(int responseCode, String responseMessage) {
        mResponseCode = responseCode;
        mResponseMessage = responseMessage;
    }

    /*******************************************************************************
     * Getter.
     *******************************************************************************/
    protected int getResponseCode() {
        return mResponseCode;
    }

    protected String getResponseMessage() {
        return mResponseMessage;
    }
}
