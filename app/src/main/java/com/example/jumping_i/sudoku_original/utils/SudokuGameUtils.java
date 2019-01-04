package com.example.jumping_i.sudoku_original.utils;

import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.List;

public class SudokuGameUtils {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static SudokuGameUtils mInstance = null;

    /*******************************************************************************
     * Initialize.
     *******************************************************************************/
    private SudokuGameUtils() {

    }

    /**
     * Singleton Instance.
     *
     * @return
     */
    public static SudokuGameUtils getInstance() {
        if (mInstance == null) {
            mInstance = new SudokuGameUtils();
        }

        return mInstance;
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    /**
     * 칼러 섹션인지 확인 한다.
     *
     * @param position
     * @return true is color.
     */
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

    /**
     * List 형식의 데인터를 2차원 배열로 반환 한다.
     *
     * @param arrData
     * @return
     */
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
