package com.example.jumping_i.sudoku_original.views.sudoku_area;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.utils.SudokuGameChecker;
import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;

public class SudokuGameGrid {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = SudokuGameGrid.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private SudokuCell[][] sudokuCells = new SudokuCell[SudokuGenerator.SUDOKU_ROW][SudokuGenerator.SUDOKU_COL];
    private Context mContext;

    /*******************************************************************************
     * Initialize.
     *******************************************************************************/
    /**
     * 생성자.
     *
     * @param context
     */
    public SudokuGameGrid(Context context) {
        mContext = context;
        for (int x = 0; x < SudokuGenerator.SUDOKU_ROW; x++) {
            for (int y = 0; y < SudokuGenerator.SUDOKU_COL; y++) {
                sudokuCells[x][y] = new SudokuCell(context);
            }
        }
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    public void setGameGrid(int[][] gameGrid) {
        Log.d(TAG, "setGameGrid()");
        for (int x = 0; x < SudokuGenerator.SUDOKU_ROW; x++) {
            for (int y = 0; y < SudokuGenerator.SUDOKU_COL; y++) {
                sudokuCells[x][y].setInitValue(gameGrid[x][y]);
                if (gameGrid[x][y] != 0) {
                    sudokuCells[x][y].setNotModifiable();
                }
            }
        }
    }

    public SudokuCell[][] getGameGrid() {
        Log.d(TAG, "getGameGrid()");
        return sudokuCells;
    }

    public SudokuCell getItem(int x, int y) {
        Log.d(TAG, "getItem()");
        return sudokuCells[x][y];
    }

    public SudokuCell getItem(int position) {
        Log.d(TAG, "getItem()");
        int x = position % SudokuGenerator.SUDOKU_ROW;
        int y = position / SudokuGenerator.SUDOKU_COL;

        return sudokuCells[x][y];
    }

    public void setItem(int x, int y, int number) {
        Log.d(TAG, "setItem()");
        sudokuCells[x][y].setValue(number);
    }

    public void checkGame() {
        Log.d(TAG, "checkGame()");
        int[][] sudokuGrid = new int[SudokuGenerator.SUDOKU_ROW][SudokuGenerator.SUDOKU_COL];

        for (int x = 0; x < SudokuGenerator.SUDOKU_ROW; x++) {
            for (int y = 0; y < SudokuGenerator.SUDOKU_COL; y++) {
                sudokuGrid[x][y] = getItem(x, y).getValue();
            }
        }
        if (SudokuGameChecker.getInstance().isCompleteSudoku(sudokuGrid)) {
            Toast.makeText(mContext, R.string.sudoku_game_complete, Toast.LENGTH_SHORT).show();
        }
    }
}
