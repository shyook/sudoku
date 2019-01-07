package com.example.jumping_i.sudoku_original.views.history;

import android.util.Log;

import com.example.jumping_i.sudoku_original.data.SudokuData;
import com.example.jumping_i.sudoku_original.views.history.BaseRequestSet;

import java.util.ArrayList;

public class RequestDeleteSet extends BaseRequestSet<ResultSet> {
    private static final String TAG = RequestNumberSet.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private ArrayList<SudokuData> mArraySudokuData;
    private int mPosition;
    private int mRequestDeleteNumber;

    /*******************************************************************************
     * 생성자.
     *******************************************************************************/
    public RequestDeleteSet(ArrayList<SudokuData> arraySudoku, int position) {
        Log.d(TAG, "RequestNumberSet()");
        mArraySudokuData = arraySudoku;
        mPosition = position;

        setRequestDeletedData(arraySudoku, position);
    }

    /*******************************************************************************
     * Override Method.
     *******************************************************************************/
    @Override
    public RequestCommandID.eRequestID getRequestID() {
        return RequestCommandID.eRequestID.REQUEST_DELETE_BUTTON;
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

    public int getRequestDeleteNumber() {
        return mRequestDeleteNumber;
    }

    /*******************************************************************************
     * Private Method.
     *******************************************************************************/
    private void setRequestDeletedData(ArrayList<SudokuData> arraySudoku, int position) {
        mRequestDeleteNumber = arraySudoku.get(position).getSudokuInfo();
    }
}
