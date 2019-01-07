package com.example.jumping_i.sudoku_original.views.history;

import android.util.Log;

import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.ArrayList;

public class RequestNumberSet extends BaseRequestSet<ResultSet> {
    private static final String TAG = RequestNumberSet.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private RequestCommandID.eRequestID mRequestID;
    private ArrayList<SudokuData> mArraySudokuData;
    private int mPosition;

    /*******************************************************************************
     * 생성자.
     *******************************************************************************/
    public RequestNumberSet(String buttonTag, ArrayList<SudokuData> arraySudoku, int position) {
        Log.d(TAG, "RequestNumberSet()");
        mArraySudokuData = arraySudoku;
        mPosition = position;

        switch (Integer.parseInt(buttonTag)) {
            case 1:
                mRequestID = RequestCommandID.eRequestID.REQUEST_NUMBER_ONE_BUTTON;
                break;
            case 2:
                mRequestID = RequestCommandID.eRequestID.REQUEST_NUMBER_TWO_BUTTON;
                break;
            case 3:
                mRequestID = RequestCommandID.eRequestID.REQUEST_NUMBER_THREE_BUTTON;
                break;
            case 4:
                mRequestID = RequestCommandID.eRequestID.REQUEST_NUMBER_FORE_BUTTON;
                break;
            case 5:
                mRequestID = RequestCommandID.eRequestID.REQUEST_NUMBER_FIVE_BUTTON;
                break;
            case 6:
                mRequestID = RequestCommandID.eRequestID.REQUEST_NUMBER_SIX_BUTTON;
                break;
            case 7:
                mRequestID = RequestCommandID.eRequestID.REQUEST_NUMBER_SEVEN_BUTTON;
                break;
            case 8:
                mRequestID = RequestCommandID.eRequestID.REQUEST_NUMBER_EIGHT_BUTTON;
                break;
            case 9:
                mRequestID = RequestCommandID.eRequestID.REQUEST_NUMBER_NINE_BUTTON;
                break;

            default:
                mRequestID = RequestCommandID.eRequestID.REQUEST_NUMBER_ONE_BUTTON;
                break;
        }
    }

    /*******************************************************************************
     * Override Method.
     *******************************************************************************/
    @Override
    public RequestCommandID.eRequestID getRequestID() {
        Log.d(TAG, "getRequestID() : " + mRequestID);
        return mRequestID;
    }

    /*******************************************************************************
     * Getter.
     *******************************************************************************/
    public ArrayList<SudokuData> getArraySudokuData() {
        return mArraySudokuData;
    }

    public int getPosition() {
        return mPosition;
    }
}
