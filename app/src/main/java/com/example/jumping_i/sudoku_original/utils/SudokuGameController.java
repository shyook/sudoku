package com.example.jumping_i.sudoku_original.utils;

import android.content.Context;
import android.util.Log;

import com.example.jumping_i.sudoku_original.views.sudoku_area.SudokuGameGrid;

public class SudokuGameController {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = SudokuGameController.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static SudokuGameController mInstance;
    private SudokuGameGrid gameGrid = null;
    private int mSelectedPositionX = -1, mSelectedPositionY = -1;

    /*******************************************************************************
     * Initialize.
     *******************************************************************************/
    /**
     * 생성자.
     */
    private SudokuGameController() {

    }

    /**
     * Singleton Instance.
     * @return
     */
    public static SudokuGameController getInstance() {
        if (mInstance == null) {
            mInstance = new SudokuGameController();
        }

        return mInstance;
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    /**
     * sudoku game을 위한 판을 생성한다.
     * @param context
     */
    public void createGameGrid(Context context) {
        Log.d(TAG, "createGameGrid()");
        int[][] sudoku = SudokuGenerator.getInstance().generateGrid();
        sudoku = SudokuGenerator.getInstance().removeElements(sudoku);
        gameGrid = new SudokuGameGrid(context);
        gameGrid.setGameGrid(sudoku);
    }

    /**
     * sudoku game 판을 반환한다.
     * @return
     */
    public SudokuGameGrid getGameGrid() {
        Log.d(TAG, "getGameGrid()");
        return gameGrid;
    }

    /**
     * 선택된 cell을 저장한다.
     * @param x
     * @param y
     */
    public void setSelectedPosition(int x, int y) {
        Log.d(TAG, "setSelectedPosition()");
        mSelectedPositionX = x;
        mSelectedPositionY = y;
    }

    /**
     *
     * @param number
     */
    public void setNumber(int number) {
        Log.d(TAG, "setNumber()");

        if (mSelectedPositionX != -1 && mSelectedPositionY != -1) {
            gameGrid.setItem(mSelectedPositionX, mSelectedPositionY, number);
        }

        // 게임이 완성 됐는지 체크 한다.
        gameGrid.checkGame();
    }
}
