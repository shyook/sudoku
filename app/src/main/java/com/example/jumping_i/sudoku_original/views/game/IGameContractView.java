package com.example.jumping_i.sudoku_original.views.game;

import com.example.jumping_i.sudoku_original.base.IBaseView;
import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.ArrayList;

public interface IGameContractView extends IBaseView {
    // 데이터가 변경되어 다시 그린다.
    void changeSudokuDataSet(ArrayList<SudokuData> arrItems);

    // 확인 버튼 클릭시 성공 여부를 디스플레이 한다.
    void doResultDisplay(boolean result);

    // Undo / Redo 가능 여부를 변경 한다.
    void changeButtonDataSet();
}
