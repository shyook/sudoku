package com.example.jumping_i.sudoku_original;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.example.jumping_i.sudoku_original.views.game.GameActivity;
import com.example.jumping_i.sudoku_original.views.game.GamePresenter;
import com.example.jumping_i.sudoku_original.views.game.IGameContractView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GameTest extends ActivityInstrumentationTestCase2<GameActivity> {
    private IGameContractView mView = null;
    private GamePresenter mPresenter = null;
    private Activity mActivity;

    public GameTest() {
        super(GameActivity.class);
    }

    @Before
    public void setUp() {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();

//        mPresenter = new GamePresenter();
//        mPresenter.attachView(mView);
//        mPresenter.setGameMode(R.string.sudoku_game_mode_easy);
//        mPresenter.createSudoku();
    }

//    @Test
//    public void sudokuCreateTest() {
//        assertEquals(true, mPresenter.getArraySudoku().isEmpty());
//    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.jumping_i.sudoku_original", appContext.getPackageName());
    }
}
