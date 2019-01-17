package com.example.jumping_i.sudoku_original;


import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;
import com.example.jumping_i.sudoku_original.views.menu.IModeContractView;
import com.example.jumping_i.sudoku_original.views.menu.ModePresenter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ModePresenterTest {
    private IModeContractView mView = null;
    private ModePresenter mPresenter = null;

    @Before
    public void setUp() {
        mView = mock(MainActivity.class);
        mPresenter = new ModePresenter();
        mPresenter.attachView(mView);
        mPresenter.initGameMode();
    }

    @Test
    public void changModeUpTest() {
        // ButtonData data = mock(ButtonData.class);
        assertEquals(SudokuGenerator.eGameMode.SUDOKU_LEVEL_EASY, mPresenter.getGameMode());
        mPresenter.changeMode(true);
        assertEquals(SudokuGenerator.eGameMode.SUDOKU_LEVEL_NOMAL, mPresenter.getGameMode());
        mPresenter.changeMode(true);
        assertEquals(SudokuGenerator.eGameMode.SUDOKU_LEVEL_HARD, mPresenter.getGameMode());
        mPresenter.changeMode(true);
        assertEquals(SudokuGenerator.eGameMode.SUDOKU_LEVEL_EASY, mPresenter.getGameMode());
    }

    @Test
    public void changeModeDownTest() {
        assertEquals(SudokuGenerator.eGameMode.SUDOKU_LEVEL_EASY, mPresenter.getGameMode());
        mPresenter.changeMode(false);
        assertEquals(SudokuGenerator.eGameMode.SUDOKU_LEVEL_HARD, mPresenter.getGameMode());
        mPresenter.changeMode(false);
        assertEquals(SudokuGenerator.eGameMode.SUDOKU_LEVEL_NOMAL, mPresenter.getGameMode());
        mPresenter.changeMode(false);
        assertEquals(SudokuGenerator.eGameMode.SUDOKU_LEVEL_EASY, mPresenter.getGameMode());
    }
}
