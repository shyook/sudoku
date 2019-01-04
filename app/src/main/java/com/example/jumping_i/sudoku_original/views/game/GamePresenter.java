package com.example.jumping_i.sudoku_original.views.game;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.base.BasePresenter;
import com.example.jumping_i.sudoku_original.data.SudokuData;
import com.example.jumping_i.sudoku_original.utils.SudokuGameChecker;
import com.example.jumping_i.sudoku_original.utils.SudokuGameController;
import com.example.jumping_i.sudoku_original.utils.SudokuGameUtils;
import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;

import java.util.ArrayList;

public class GamePresenter extends BasePresenter<IGameContractView> {
    private static final String TAG = GamePresenter.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private IGameContractView mView = null;
    private Activity mActivity = null;
    private SudokuGenerator.eGameMode mGameMode = SudokuGenerator.eGameMode.SUDOKU_LEVEL_EASY;
    private int mSelectedPosition = -1;
    private TextView mSelectedCell = null;

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

    public void createSudoku() {
        Log.d(TAG, "createSudoku()");
        SudokuGameController.getInstance().createGameGrid(mActivity);
    }

    public ArrayList<SudokuData> getArraySudoku() {
        Log.d(TAG, "getArraySudoku()");
        return SudokuGameController.getInstance().getArraySudoku();
    }

    public ArrayList<String> getButtonInfo() {
        Log.d(TAG, "getButtonInfo()");
        ArrayList<String> arrButtonInfo = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            arrButtonInfo.add(String.valueOf(i));
        }

        arrButtonInfo.add(mActivity.getString(R.string.button_del));

        return arrButtonInfo;
    }

    public void setSelectSudokuCell(TextView tv, SudokuData item, int position) {
        Log.d(TAG, "setSelectSudokuCell()");
        if (mSelectedCell != null) {
            Log.i(TAG, "mSelectedCell : " + mSelectedCell.getText());
            if (! TextUtils.isEmpty(mSelectedCell.getText())) {
                mSelectedCell.setBackgroundColor(Color.parseColor("#FF9900"));
            } else if (SudokuGameUtils.getInstance().isColorRegion(mSelectedPosition)) {
                mSelectedCell.setBackgroundColor(Color.parseColor("#FFFF00"));
            } else {
                mSelectedCell.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }

        // 수정 가능한 텍스트 뷰인지 확인 한다.
        if (item.isModification()) {
            Log.i(TAG, "tv.getTag() : " + tv.getTag());
            mSelectedCell = tv;
            tv.setBackgroundColor(Color.parseColor("#FF00FF"));
            mSelectedPosition = position;
        } else {
            mSelectedPosition = -1;
        }
    }

    public void onClickButtonEvent(Button button, int position) {
        Log.d(TAG, "onClickButtonEvent()");

        if (mSelectedPosition != -1) {
            ArrayList<SudokuData> arrItems = getArraySudoku();

            if (mActivity.getString(R.string.button_del).equals(button.getTag())) {
                // 삭제 버튼
                arrItems.remove(mSelectedPosition);
                arrItems.add(mSelectedPosition, new SudokuData(0, true));
            } else {
                arrItems.remove(mSelectedPosition);
                arrItems.add(mSelectedPosition, new SudokuData(Integer.parseInt((String) button.getTag()), true));
            }

            mView.changeSudokuDataSet(arrItems);
        }
    }

    public boolean confirmGame() {
        ArrayList<SudokuData> arrItems = getArraySudoku();

        for (int i = 0; i < arrItems.size(); i++) {
            if (arrItems.get(i).getSudokuInfo() == 0) {
                return false;
            }
        }

        return true;
    }

    public void checkCompleteGame() {
        if (SudokuGameChecker.getInstance().isCompleteSudoku(SudokuGameUtils.getInstance().getSudokuListToArray(getArraySudoku()))) {
            mView.doResultDisplay(true);
        }

        mView.doResultDisplay(false);
    }
}
