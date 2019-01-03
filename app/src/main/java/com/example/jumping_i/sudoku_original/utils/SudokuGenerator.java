package com.example.jumping_i.sudoku_original.utils;

import android.util.Log;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.base.Config;

import java.util.ArrayList;
import java.util.Random;

/**
 * Sudoku 게임을 위한 숫자를 생성 한다.
 */
public class SudokuGenerator {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = SudokuGenerator.class.getSimpleName();

    public static final int SUDOKU_ROW = 9;
    public static final int SUDOKU_COL = 9;
    public static final int SUDOKU_TOTAL_NUMBER = SUDOKU_COL * SUDOKU_ROW;
    public enum eGameMode {
        SUDOKU_LEVEL_EASY (R.string.sudoku_game_mode_easy, 30)
        , SUDOKU_LEVEL_NOMAL(R.string.sudoku_game_mode_normal, 45)
        , SUDOKU_LEVEL_HARD(R.string.sudoku_game_mode_hard, 60);

        private int mMode;
        private int mBlankNumber;

        eGameMode(int mode, int blankNumber) {
            mMode = mode;
            mBlankNumber = blankNumber;
        }

        public int getMode() {
            return mMode;
        }

        public int getBlankNumber() {
            return mBlankNumber;
        }

        public static eGameMode getIntToGameMode(int mode) {
            switch (mode) {
                case R.string.sudoku_game_mode_easy:
                    return SUDOKU_LEVEL_EASY;

                case R.string.sudoku_game_mode_normal:
                    return SUDOKU_LEVEL_NOMAL;

                case R.string.sudoku_game_mode_hard:
                    return SUDOKU_LEVEL_HARD;

                default:
                    return SUDOKU_LEVEL_EASY;
            }
        }
    }

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static SudokuGenerator mInstance = null;
    private ArrayList<ArrayList<Integer>> mAvailable = new ArrayList<>();
    private Random mRandom = new Random();

    /*******************************************************************************
     * Initialize.
     *******************************************************************************/
    /**
     * 생성자
     */
    private SudokuGenerator() {

    }

    /**
     * Singleton Instance.
     *
     * @return SudokuGenerator
     */
    public static SudokuGenerator getInstance() {
        if (mInstance == null) {
            mInstance = new SudokuGenerator();
        }
        return mInstance;
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    /**
     * sudoku 숫자를 생성해서 배열을 완성한다.
     *
     * @return
     */
    public int[][] generateGrid() {
        Log.d(TAG, "generateGrid()");

        int[][] sudoku = new int[SUDOKU_ROW][SUDOKU_COL];
        int currentPos = 0;

        // grid 삭제.
        clearGrid(sudoku);

        while (currentPos < SUDOKU_TOTAL_NUMBER) {
            if (mAvailable.get(currentPos).size() != 0) {
                int randomNumber = mRandom.nextInt(mAvailable.get(currentPos).size());
                int number = mAvailable.get(currentPos).get(randomNumber);

                Log.i(TAG, "currentPos : " + currentPos + "  randomNumber : " + randomNumber + "  number : " + number + "  size : " + mAvailable.get(currentPos).size());
                if (!checkConflict(sudoku, currentPos, number)) {
                    int xPos = currentPos % SUDOKU_ROW;
                    int yPos = currentPos / SUDOKU_COL;

                    sudoku[xPos][yPos] = number;
                    mAvailable.get(currentPos).remove(randomNumber);
                    currentPos++;
                } else {
                    mAvailable.get(currentPos).remove(randomNumber);
                }
            } else {
                Log.i(TAG, "currentPos : " + currentPos);
                for (int i = 1; i <= 9; i++) {
                    mAvailable.get(currentPos).add(i);
                }
                currentPos--;
            }
        }
        return sudoku;
    }

    /**
     * 레벨에 따라 빈칸을 만들기 위해 해당 항목의 숫자를 0으로 변경 한다.
     *
     * @param sudoku
     * @return
     */
    public int[][] removeElements(int[][] sudoku) {
        Log.d(TAG, "removeElements()");

        int i = 0;

        while (i < eGameMode.SUDOKU_LEVEL_EASY.getBlankNumber()) {
            int x = mRandom.nextInt(SUDOKU_ROW);
            int y = mRandom.nextInt(SUDOKU_COL);

            if (sudoku[x][y] != 0) {
                sudoku[x][y] = 0;
                i++;
            }
        }

        return sudoku;
    }

    /*******************************************************************************
     * Private Method.
     *******************************************************************************/
    /**
     * sudoku 배열을 초기화 한다.
     *
     * @param sudoku
     */
    private void clearGrid(int[][] sudoku) {
        Log.d(TAG, "clearGrid()");

        mAvailable.clear();

        // sudoku 초기화
        for (int y = 0; y < SUDOKU_COL; y++) {
            for (int x = 0; x < SUDOKU_ROW; x++) {
                sudoku[x][y] = -1;
            }
        }

        // mAvailable 초기화
        for (int x = 0; x < SUDOKU_TOTAL_NUMBER; x++) {
            mAvailable.add(new ArrayList<Integer>());
            for (int i = 1; i <= 9; i++) {
                mAvailable.get(x).add(i);
            }
        }

        if (Config.IS_DEBUG) {
            for (int i = 0; i < mAvailable.size(); i++) {
                Log.i(TAG, "mAvaliable : " + mAvailable.get(i) + " i : " + i);
            }
        }
    }

    /**
     * 숫자판에 저장된 숫자중 sudoku 룰에 맞지 않는 같은 숫자가 있는지 체크 한다.
     *
     * @param sudoku
     * @param currentPos
     * @param number
     * @return true is conflict.
     */
    private boolean checkConflict(int[][] sudoku, int currentPos, int number) {
        Log.d(TAG, "checkConflict()");

        int xPos = currentPos % SUDOKU_ROW;
        int yPos = currentPos / SUDOKU_COL;

        Log.i(TAG, "xPos : " + xPos + "  yPos : " + yPos + "  number : " + number);
        if (checkRowConflict(sudoku, xPos, yPos, number)
                || checkColumnConflict(sudoku, xPos, yPos, number)
                || checkRegionConflict(sudoku, xPos, yPos, number)) {
            return true;
        }
        return false;
    }

    /**
     * Row 방향에 이전에 저장된 같은 숫자가 있는지 체크 한다.
     *
     * @param sudoku
     * @param xPos
     * @param yPos
     * @param number
     * @return true is conflict.
     */
    private boolean checkRowConflict(int[][] sudoku, int xPos, int yPos, int number) {
        Log.d(TAG, "checkRowConflict()");

        for (int x = xPos - 1; x >= 0; x--) {
            if (number == sudoku[x][yPos]) {
                Log.e(TAG, "Row had same number");
                return true;
            }
        }
        return false;
    }

    /**
     * Column 방향에 이전에 저장된 같은 숫자가 있는지 체크 한다.
     *
     * @param sudoku
     * @param xPos
     * @param yPos
     * @param number
     * @return true is conflict.
     */
    private boolean checkColumnConflict(int[][] sudoku, int xPos, int yPos, int number) {
        Log.d(TAG, "checkColumnConflict()");

        for (int y = yPos - 1; y >= 0; y--) {
            if (number == sudoku[xPos][y]) {
                Log.e(TAG, "Column had same number");
                return true;
            }
        }
        return false;
    }

    /**
     * 3 * 3 구역내에 이전에 저장된 같은 숫자가 있는지 체크 한다.
     *
     * @param sudoku
     * @param xPos
     * @param yPos
     * @param number
     * @return true is conflict.
     */
    private boolean checkRegionConflict(int[][] sudoku, int xPos, int yPos, int number) {
        Log.d(TAG, "checkRegionConflict()");

        int xRegion = xPos / 3;
        int yRegion = yPos / 3;

        for (int x = xRegion * 3; x < xRegion * 3 + 3; x++) {
            for (int y = yRegion * 3; y < yRegion * 3 + 3; y++) {
                Log.i(TAG, "x : " + x + " y : " + y + " xPos : " + xPos + " yPos : " + yPos + " number : " + number + " sudoku[x][y] : " + sudoku[x][y]);
                if ((x != xPos || y != yPos) && number == sudoku[x][y]) {
                    Log.e(TAG, "Region had same number");
                    return true;
                }
            }
        }

        return false;
    }
}
