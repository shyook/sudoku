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

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    /**
     * Game Mode를 셋팅한다.
     * @param mode
     */
    public void setGameMode(int mode) {
        Log.d(TAG, "setGameMode()");
        mGameMode = SudokuGenerator.eGameMode.getIntToGameMode(mode);
        Log.i(TAG, "mGameMode : " + mActivity.getString(mGameMode.getMode()));
    }

    /**
     * 수도쿠를 생성하고 그린다.
     */
    public void createSudoku() {
        Log.d(TAG, "createSudoku()");
        SudokuGameController.getInstance().createGameGrid(mActivity);
    }

    /**
     * 수도쿠 리스트 데이터를 반환한다.
     * @return
     */
    public ArrayList<SudokuData> getArraySudoku() {
        Log.d(TAG, "getArraySudoku()");
        return SudokuGameController.getInstance().getArraySudoku();
    }

    /**
     * 버튼 리스트 데이터를 반환한다.
     * @return
     */
    public ArrayList<String> getButtonInfo() {
        Log.d(TAG, "getButtonInfo()");
        ArrayList<String> arrButtonInfo = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            arrButtonInfo.add(String.valueOf(i));
        }

        arrButtonInfo.add(mActivity.getString(R.string.button_del));

        return arrButtonInfo;
    }

    /**
     * 선택된 셀을 셋팅한다. (선택된셀 색상 변경 / 수정 불가능한 셀의 경우 아무런 변결 없음)
     * @param tv
     * @param item
     * @param position
     */
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

    /**
     * 버튼을 클릭한 경우 해당 셀을 변경한다.
     * @param button
     * @param position
     */
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

    /**
     * 확인 버튼을 누른경우 모든 셀이 채워졌는지 확인 한다.
     * @return true is not empty.
     */
    public boolean confirmGame() {
        ArrayList<SudokuData> arrItems = getArraySudoku();

        for (int i = 0; i < arrItems.size(); i++) {
            if (arrItems.get(i).getSudokuInfo() == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 확인 버튼을 누른경우 수도쿠가 정확하게 완성 되었는지 확인 후 디스플레이 한다.
     */
    public void checkCompleteGame() {
        if (SudokuGameController.getInstance().isCompleteSudoku(SudokuGameUtils.getInstance().getSudokuListToArray(getArraySudoku()))) {
            mView.doResultDisplay(true);
        }

        mView.doResultDisplay(false);
    }
}
