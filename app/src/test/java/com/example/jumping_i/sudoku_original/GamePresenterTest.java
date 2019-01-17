package com.example.jumping_i.sudoku_original;

import android.app.Activity;

import com.example.jumping_i.sudoku_original.data.ButtonData;
import com.example.jumping_i.sudoku_original.utils.SudokuGameController;
import com.example.jumping_i.sudoku_original.utils.SudokuGameUtils;
import com.example.jumping_i.sudoku_original.views.game.GameActivity;
import com.example.jumping_i.sudoku_original.views.game.GamePresenter;
import com.example.jumping_i.sudoku_original.views.game.IGameContractView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class GamePresenterTest {
    private IGameContractView mView = null;
    private GamePresenter mPresenter = null;
    private Activity mActivity;

    @Before
    public void setUp() {
        mView = mock(GameActivity.class);
        mActivity = mock(Activity.class);
        mPresenter = new GamePresenter();
        mPresenter.attachView(mView);
        mPresenter.setContext(mActivity);
//        String strTest = mActivity.getString(R.string.button_del);
//        assertNotNull(strTest);
        mPresenter.setGameMode(R.string.sudoku_game_mode_easy);
        mPresenter.clearSudoku();
        mPresenter.createSudoku();
    }

    @Test
    public void sudokuCreateTest() {
        assertEquals(false, mPresenter.getArraySudoku().isEmpty());
        assertEquals(81, mPresenter.getArraySudoku().size());
    }

    @Test
    public void buttonInfoTest() {
        ArrayList<ButtonData> datas = mPresenter.getButtonInfo();
        assertEquals(false, mPresenter.getButtonInfo().isEmpty());
        assertEquals(12, mPresenter.getButtonInfo().size());

        for (int i = 0; i <= 8; i++) {
            // arrButtonInfo.add(new ButtonData().setButtonName(String.valueOf(i)));
            ButtonData buttonInfo = datas.get(i);
            assertEquals(String.valueOf(i + 1), buttonInfo.getButtonName());
        }

    }

    @Test
    public void completeGameTest() {
        assertEquals(false, SudokuGameController.getInstance().isCompleteSudoku(SudokuGameUtils.getInstance().getSudokuListToArray(mPresenter.getArraySudoku())));
    }


}
