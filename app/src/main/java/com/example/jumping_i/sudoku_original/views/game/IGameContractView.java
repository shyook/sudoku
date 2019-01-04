package com.example.jumping_i.sudoku_original.views.game;

import com.example.jumping_i.sudoku_original.base.IBaseView;
import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.ArrayList;

public interface IGameContractView extends IBaseView {
    void changeSudokuDataSet(ArrayList<SudokuData> arrItems);

    void doResultDisplay(boolean result);
}
