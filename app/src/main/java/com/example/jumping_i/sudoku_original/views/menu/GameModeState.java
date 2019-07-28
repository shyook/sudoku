package com.example.jumping_i.sudoku_original.views.menu;

import android.util.Log;

import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;

import static com.example.jumping_i.sudoku_original.utils.SudokuGenerator.eGameMode.SUDOKU_LEVEL_1;
import static com.example.jumping_i.sudoku_original.utils.SudokuGenerator.eGameMode.SUDOKU_LEVEL_10;

public class GameModeState {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = GameModeState.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static GameModeState mInstance;
    private SudokuGenerator.eGameMode mMode;

    /*******************************************************************************
     * Initialize.
     *******************************************************************************/
    /**
     * 생성자.
     */
    private GameModeState() {

    }

    /**
     * Singleton Instance.
     * @return
     */
    public static GameModeState getInstance() {
        if (mInstance == null) {
            mInstance = new GameModeState();
        }

        return mInstance;
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/

    public void initGameMode(SudokuGenerator.eGameMode mode) {
        mMode = mode;
    }

    public SudokuGenerator.eGameMode getNextUpState() {
        Log.d(TAG, ">> getNextUpState()");
        if (mMode == SUDOKU_LEVEL_1) {
            mMode = SUDOKU_LEVEL_10;
        } else {
            mMode = SudokuGenerator.eGameMode.values()[mMode.ordinal() - 1];
        }
        Log.i(TAG, ">> mMode : " + mMode.toString());

        return mMode;
    }

    public SudokuGenerator.eGameMode getNextDownState() {
        Log.d(TAG, ">> getNextDownState()");
        if (mMode == SUDOKU_LEVEL_10) {
            mMode = SUDOKU_LEVEL_1;
        } else {
            mMode = SudokuGenerator.eGameMode.values()[mMode.ordinal() + 1];
        }
        Log.i(TAG, ">> mMode : " + mMode.toString());

        return mMode;
    }

    /*******************************************************************************
     * private Method.
     *******************************************************************************/

}
