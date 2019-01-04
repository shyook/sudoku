package com.example.jumping_i.sudoku_original.utils;

import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.List;

public class SudokuGameUtils {
    private static SudokuGameUtils mInstance = null;

    private SudokuGameUtils() {

    }

    public static SudokuGameUtils getInstance() {
        if (mInstance == null) {
            mInstance = new SudokuGameUtils();
        }

        return mInstance;
    }

    public boolean isColorRegion(int position) {
        if (position == -1) {
            return false;
        }

        int x = position % SudokuGenerator.SUDOKU_ROW;
        int y = position / SudokuGenerator.SUDOKU_COL;

        int xRegion = x / SudokuGenerator.SUDOKU_REGION_NUMBER;
        int yRegion = y / SudokuGenerator.SUDOKU_REGION_NUMBER;

        if ((xRegion == 1 && (yRegion == 0 || yRegion == 2)) || ((xRegion == 0 || xRegion == 2) && yRegion == 1)) {
            return true;
        }

        return false;
    }

    public int[][] getSudokuListToArray(List<SudokuData> arrData) {
        int[][] sudoku = new int[SudokuGenerator.SUDOKU_ROW][SudokuGenerator.SUDOKU_COL];

        for (int i = 0; i < arrData.size(); i++) {
            int x = i % SudokuGenerator.SUDOKU_ROW;
            int y = i / SudokuGenerator.SUDOKU_COL;

            sudoku[x][y] = arrData.get(i).getSudokuInfo();
        }

        return sudoku;
    }
}
