package com.example.jumping_i.sudoku_original.views.menu;

import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;

/**
 * Hard State 호출시 다음 State를 반환한다.
 */
public class GameModeHardState implements IGameModeState {
    @Override
    public SudokuGenerator.eGameMode getNextUpState() {
        return SudokuGenerator.eGameMode.SUDOKU_LEVEL_1;
    }

    @Override
    public SudokuGenerator.eGameMode getNextDownState() {
        return SudokuGenerator.eGameMode.SUDOKU_LEVEL_2;
    }
}
