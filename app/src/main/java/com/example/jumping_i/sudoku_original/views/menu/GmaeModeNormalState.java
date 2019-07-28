package com.example.jumping_i.sudoku_original.views.menu;

import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;

/**
 * Normal State 호출시 다음 State를 반환 한다.
 */
public class GmaeModeNormalState implements IGameModeState {
    @Override
    public SudokuGenerator.eGameMode getNextUpState() {
        return SudokuGenerator.eGameMode.SUDOKU_LEVEL_1;
    }

    @Override
    public SudokuGenerator.eGameMode getNextDownState() {
        return SudokuGenerator.eGameMode.SUDOKU_LEVEL_2;
    }
}
