package com.example.jumping_i.sudoku_original.views.menu;

import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;

public interface IGameModeState {
    // Up Button Click시 다음 state
    public SudokuGenerator.eGameMode getNextUpState();
    // Down Button Click시 다음 state
    public SudokuGenerator.eGameMode getNextDownState();
}
