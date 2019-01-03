package com.example.jumping_i.sudoku_original.views.game;

import android.app.Activity;
import android.util.Log;

import com.example.jumping_i.sudoku_original.base.BasePresenter;
import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;

public class GamePresenter extends BasePresenter<IGameContractView> {
    private static final String TAG = GamePresenter.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private IGameContractView mView = null;
    private Activity mActivity = null;
    private SudokuGenerator.eGameMode mGameMode = SudokuGenerator.eGameMode.SUDOKU_LEVEL_EASY;

    /*******************************************************************************
     * Interface Override.
     *******************************************************************************/
    @Override
    public void attachView(IGameContractView view) {
        mView = view;
        mActivity = view.getActivity();
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    public void setGameMode(int mode) {
        Log.d(TAG, "setGameMode()");
        mGameMode = SudokuGenerator.eGameMode.getIntToGameMode(mode);
        Log.i(TAG, "mGameMode : " + mActivity.getString(mGameMode.getMode()));
    }
}
