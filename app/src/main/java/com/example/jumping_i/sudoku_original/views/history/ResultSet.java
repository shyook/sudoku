package com.example.jumping_i.sudoku_original.views.history;

import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.ArrayList;

public class ResultSet extends AbstractResponseSet {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    ArrayList<SudokuData> mArraySudokuData = null;  // 수도쿠 배열

    /*******************************************************************************
     * 생성자.
     *******************************************************************************/
    public ResultSet(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    /*******************************************************************************
     * Getter / Setter.
     *******************************************************************************/
    public ArrayList<SudokuData> getArraySudokuData() {
        return mArraySudokuData;
    }

    public void setArraySudokuData(ArrayList<SudokuData> arraySudokuData) {
        this.mArraySudokuData = arraySudokuData;
    }
}
