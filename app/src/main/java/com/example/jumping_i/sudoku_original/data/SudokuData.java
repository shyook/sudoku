package com.example.jumping_i.sudoku_original.data;

public class SudokuData {
    private int mSudokuInfo = -1;
    private boolean mIsModification = false;

    public SudokuData(int number, boolean isModification) {
        mSudokuInfo = number;
        mIsModification = isModification;
    }

    public int getSudokuInfo() {
        return mSudokuInfo;
    }

    public void setSudokuInfo(int sudokuInfo) {
        this.mSudokuInfo = sudokuInfo;
    }

    public boolean isModification() {
        return mIsModification;
    }

    public void setModification(boolean isModification) {
        this.mIsModification = isModification;
    }
}
