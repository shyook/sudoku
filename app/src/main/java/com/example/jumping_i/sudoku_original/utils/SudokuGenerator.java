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
    public static final int SUDOKU_REGION_NUMBER = 3;
    public static final int SUDOKU_TOTAL_NUMBER = SUDOKU_COL * SUDOKU_ROW;
    // TODO 게임의 등급을 다 분화 한다.
    // 표시되는 이름, 빈칸 수
    // 1. 몸풀기 연습 부터 시작 - 20
    // 2. 아직은 연습중 - 25
    // 3. 슬슬 본 게임으로 가 볼까요 - 30
    // 4. 이제는 좀 게임 같네 - 35
    // 5. 난이도 올리기 준비 중 - 40
    // 6. 이정도는 해야 보통이죠 - 45
    // 7. 난이도를 좀 올려 볼까 - 50
    // 8. 빈칸이 더 많아요 - 55
    // 9. 훵 하네요 - 60
    // 10. 이건 어렵다 - 70
    public enum eGameMode {
        SUDOKU_LEVEL_1 (R.string.sudoku_game_mode_1, 20)
        , SUDOKU_LEVEL_2(R.string.sudoku_game_mode_2, 25)
        , SUDOKU_LEVEL_3(R.string.sudoku_game_mode_3, 30)
        , SUDOKU_LEVEL_4(R.string.sudoku_game_mode_4, 35)
        , SUDOKU_LEVEL_5(R.string.sudoku_game_mode_5, 40)
        , SUDOKU_LEVEL_6(R.string.sudoku_game_mode_6, 45)
        , SUDOKU_LEVEL_7(R.string.sudoku_game_mode_7, 50)
        , SUDOKU_LEVEL_8(R.string.sudoku_game_mode_8, 55)
        , SUDOKU_LEVEL_9(R.string.sudoku_game_mode_9, 60)
        , SUDOKU_LEVEL_10(R.string.sudoku_game_mode_10, 70);

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
                case R.string.sudoku_game_mode_1:
                    return SUDOKU_LEVEL_1;

                case R.string.sudoku_game_mode_2:
                    return SUDOKU_LEVEL_2;

                case R.string.sudoku_game_mode_3:
                    return SUDOKU_LEVEL_3;

                case R.string.sudoku_game_mode_4:
                    return SUDOKU_LEVEL_4;

                case R.string.sudoku_game_mode_5:
                    return SUDOKU_LEVEL_5;

                case R.string.sudoku_game_mode_6:
                    return SUDOKU_LEVEL_6;

                case R.string.sudoku_game_mode_7:
                    return SUDOKU_LEVEL_7;

                case R.string.sudoku_game_mode_8:
                    return SUDOKU_LEVEL_8;

                case R.string.sudoku_game_mode_9:
                    return SUDOKU_LEVEL_9;

                case R.string.sudoku_game_mode_10:
                    return SUDOKU_LEVEL_10;

                default:
                    return SUDOKU_LEVEL_5;
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
     * @param mGameMode
     * @return
     */
    public int[][] removeElements(int[][] sudoku, eGameMode mGameMode) {
        Log.d(TAG, "removeElements()");

        int i = 0;

        while (i < mGameMode.getBlankNumber()) {
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
