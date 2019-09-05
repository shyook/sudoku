package com.example.jumping_i.sudoku_original.base;

import com.example.jumping_i.sudoku_original.utils.SudokuGameController;
import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;

public abstract class BasePresenter<T extends IBaseView> {
    /*******************************************************************************
     * abstract.
     *******************************************************************************/
    public abstract void attachView(T view);
    public abstract void detachView();

    /*******************************************************************************
     * Method.
     *******************************************************************************/
    /**
     * 스도쿠를 초기화 한다.
     */
    public void clearSudoku() {
        SudokuGameController.getInstance().clearGridView();
    }

    /**
     * 스도쿠를 생성하고 그린다.
     */
    public void createSudoku(SudokuGenerator.eGameMode mGameMode) {
        SudokuGameController.getInstance().createGameGrid(mGameMode);
    }
}
