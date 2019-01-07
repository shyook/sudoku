package com.example.jumping_i.sudoku_original.views.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

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
    /** BackKey press Count. */
    private int mBackButtonClickCount = 0;

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
    /**
     * 게임 모드 선택 변경에 대한 동작을 수행한다.
     * @param isUp
     */
    public void changeMode(boolean isUp) {
        Log.d(TAG, "changeMode()");
        if (isUp) {
            mGameMode = getCurrentModeState(mGameMode).getNextUpState();
        } else {
            mGameMode = getCurrentModeState(mGameMode).getNextDownState();
        }

        updateGameMode(mGameMode);
    }

    /**
     * 처음 진입시 게임 모드를 셋팅한다.
     */
    public void initGameMode() {
        Log.d(TAG, "initGameMode()");
        mView.updateGameModeView(mGameMode.getMode());
    }

    /**
     * 선택한 모드로 게임을 시작한다.
     */
    public void startGame() {
        Log.d(TAG, "initGameMode()");
        Intent i = new Intent(mActivity, GameActivity.class);
        i.putExtra(IConsts.IntentConsts.BUNDLE_GAME_MODE, mGameMode.getMode());
        mActivity.startActivity(i);
    }

    /**
     * 뒤로 버튼을 두번 클릭했을때 앱 종료 하도록 체크.
     * @return
     */
    public boolean checkExitApp() {
        Log.d(TAG, "checkExitApp()");
        mBackButtonClickCount++;
        if (mBackButtonClickCount == 1) {
            Toast.makeText(mActivity, R.string.toast_finish_app, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    mBackButtonClickCount = 0;
                }
            }, 3000);
            return false;
        }
        return true;
    }

    /*******************************************************************************
     * Private method.
     *******************************************************************************/
    /**
     * 현재 모드에 대한 State Class  반환.
     * @param gameMode
     * @return
     */
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

    /**
     * 게임 모드를 변경을 View에 알린다.
     *
     * @param gameMode
     */
    private void updateGameMode(SudokuGenerator.eGameMode gameMode) {
        Log.d(TAG, "updateGameMode()");
        mView.updateGameModeView(gameMode.getMode());
    }

}
