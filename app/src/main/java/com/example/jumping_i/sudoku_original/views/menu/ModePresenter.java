package com.example.jumping_i.sudoku_original.views.menu;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.base.BasePresenter;
import com.example.jumping_i.sudoku_original.consts.IConsts;
import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;
import com.example.jumping_i.sudoku_original.views.game.GameActivity;

public class ModePresenter extends BasePresenter<IModeContractView> {
    private static final String TAG = ModePresenter.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private IModeContractView mView = null;
    private Activity mActivity = null;
    private SudokuGenerator.eGameMode mGameMode = SudokuGenerator.eGameMode.SUDOKU_LEVEL_EASY;

    /*******************************************************************************
     * Interface Override.
     *******************************************************************************/
    @Override
    public void attachView(IModeContractView view) {
        mView = view;
        mActivity = view.getActivity();
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    /*******************************************************************************
     * public method.
     *******************************************************************************/
    public void changeMode(boolean isUp) {
        Log.d(TAG, "changeMode()");
        if (isUp) {
            mGameMode = getCurrentModeState(mGameMode).getNextUpState();
        } else {
            mGameMode = getCurrentModeState(mGameMode).getNextDownState();
        }

        updateGameMode(mGameMode);
    }

    /*******************************************************************************
     * Private method.
     *******************************************************************************/
    private IGameModeState getCurrentModeState(SudokuGenerator.eGameMode gameMode) {
        switch (gameMode.getMode()) {
            case R.string.sudoku_game_mode_easy:
                return new GameModeEasyState();

            case R.string.sudoku_game_mode_normal:
                return new GmaeModeNormalState();

            case R.string.sudoku_game_mode_hard:
                return new GameModeHardState();

            default:
                return new GameModeEasyState();
        }
    }

    private void updateGameMode(SudokuGenerator.eGameMode gameMode) {
        Log.d(TAG, "updateGameMode()");
        mView.updateGameModeView(gameMode.getMode());
    }

    public void initGameMode() {
        Log.d(TAG, "initGameMode()");
        mView.updateGameModeView(mGameMode.getMode());
    }

    public void startGame() {
        Log.d(TAG, "initGameMode()");
        Intent i = new Intent(mActivity, GameActivity.class);
        i.putExtra(IConsts.IntentConsts.BUNDLE_GAME_MODE, mGameMode.getMode());
        mActivity.startActivity(i);
    }
}
