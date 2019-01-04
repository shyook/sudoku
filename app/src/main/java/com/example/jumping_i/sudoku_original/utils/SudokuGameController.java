package com.example.jumping_i.sudoku_original.utils;

import android.content.Context;
import android.util.Log;

import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.ArrayList;

import static com.example.jumping_i.sudoku_original.utils.SudokuGenerator.SUDOKU_COL;
import static com.example.jumping_i.sudoku_original.utils.SudokuGenerator.SUDOKU_ROW;

public class SudokuGameController {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = SudokuGameController.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static SudokuGameController mInstance;
    private ArrayList<SudokuData> mArraySudoku = new ArrayList<>();

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

        for (int i = 0; i < SUDOKU_ROW; i++) {
            for (int j = 0; j < SUDOKU_COL; j++) {
                int nNumber = sudoku[i][j];
                if (nNumber != 0) {
                    mArraySudoku.add(new SudokuData(sudoku[i][j], false));
                } else {
                    mArraySudoku.add(new SudokuData(sudoku[i][j], true));
                }
            }
        }
    }

    public ArrayList<SudokuData> getArraySudoku() {
        return mArraySudoku;
    }

    /**
     * sudoku가 완성 되었는지 체크 한다.
     *
     * @param sudoku
     * @return
     */
    public boolean isCompleteSudoku(int[][] sudoku) {
        Log.d(TAG, "isCompleteSudoku()");

        return checkRow(sudoku) || checkColumn(sudoku) || checkRegions(sudoku);
    }

    /*******************************************************************************
     * Private Method.
     *******************************************************************************/
    /**
     * Row 방향에 같은 숫자가 있는지 체크 한다.
     *
     * @param sudoku
     * @return true is success
     */
    private boolean checkRow(int[][] sudoku) {
        Log.d(TAG, "checkRow()");

        for (int y = 0; y < SudokuGenerator.SUDOKU_COL; y++) {
            for (int xPos = 0; xPos < SudokuGenerator.SUDOKU_ROW; xPos++) {
                // 초기값이 있으면 실패
                if (sudoku[xPos][y] == 0) {
                    return false;
                }

                // 해당 cell 이후 같은 숫자가 있는지 체크
                for (int x = xPos + 1; x < SudokuGenerator.SUDOKU_ROW; x++) {
                    if (sudoku[xPos][y] == sudoku[x][y] || sudoku[x][y] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Column 방향에 같은 숫자가 있는지 체크 한다.
     *
     * @param sudoku
     * @return true is success.
     */
    private boolean checkColumn(int[][] sudoku) {
        Log.d(TAG, "checkColumn()");

        for (int x = 0; x < SudokuGenerator.SUDOKU_ROW; x++) {
            for (int yPos = 0; yPos < SudokuGenerator.SUDOKU_COL; yPos++) {
                // 초기값이 있으면 실패
                if (sudoku[x][yPos] == 0) {
                    return false;
                }

                // 해당 cell 이후 같은 숫자가 있는지 체크
                for (int y = yPos + 1; y < SudokuGenerator.SUDOKU_COL; y++) {
                    if (sudoku[x][yPos] == sudoku[x][y] || sudoku[x][y] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 해당 지역에 같은 숫자가 있는지 체크한다.
     *
     * @param sudoku
     * @return true is success.
     */
    private boolean checkRegions(int[][] sudoku) {
        Log.d(TAG, "checkRegions()");

        for (int xRegion = 0; xRegion < 3; xRegion++) {
            for (int yRegion = 0; yRegion < 3; yRegion++) {
                if (! checkRegions(sudoku, xRegion, yRegion)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 해당 지역에 같은 숫자가 있는지 체크 한다.
     *
     * @param sudoku
     * @param xRegion
     * @param yRegion
     * @return true is success
     */
    private boolean checkRegions(int[][] sudoku, int xRegion, int yRegion) {
        Log.d(TAG, "checkRegions()");

        for (int xPos = xRegion * 3; xPos < xRegion * 3 + 3; xPos++) {
            for (int yPos = yRegion * 3; yPos < yRegion * 3 + 3; yPos++) {
                for (int x = xPos; x < xRegion * 3 + 3; x++) {
                    for (int y = yPos; y < yRegion * 3 + 3; y++) {
                        if (((x != xPos || y != yPos) && sudoku[xPos][yPos] == sudoku[x][y]) || sudoku[x][y] == 0) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
