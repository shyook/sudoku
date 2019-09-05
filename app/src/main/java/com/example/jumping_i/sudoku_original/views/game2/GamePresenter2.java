package com.example.jumping_i.sudoku_original.views.game2;

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
import com.example.jumping_i.sudoku_original.views.history.BaseRequestSet;
import com.example.jumping_i.sudoku_original.views.history.ISuccessResponse;
import com.example.jumping_i.sudoku_original.views.history.InvokeManager;
import com.example.jumping_i.sudoku_original.views.history.RequestDeleteSet;
import com.example.jumping_i.sudoku_original.views.history.RequestNumberSet;
import com.example.jumping_i.sudoku_original.views.history.ResultSet;

import java.util.ArrayList;

public class GamePresenter2 extends BasePresenter<IGameContractView2> implements IOnItemClickListener.Game, IOnItemClickListener.Button, ISuccessResponse {
    private static final String TAG = GamePresenter2.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private IGameContractView2 mView = null;
    private IGameAdapterContract.Model mAdapterModel;
    private IGameAdapterContract.View mAdapterView;
    private IButtonAdapterContract.Model mButtonAdapterModel;
    private IButtonAdapterContract.View mButtonAdapterView;
    private Activity mActivity = null;
    private SudokuGenerator.eGameMode mGameMode = SudokuGenerator.eGameMode.SUDOKU_LEVEL_5;
    private int mSelectedPosition = -1;
    private TextView mSelectedCell = null;
    private ArrayList<String> mButtonInfo = new ArrayList<>();

    /*******************************************************************************
     * Interface Override.
     *******************************************************************************/
    @Override
    public void attachView(IGameContractView2 view) {
        mView = view;
        mActivity = view.getActivity();
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public void onItemClick(TextView v, SudokuData item, int position) {
        Log.i(TAG, "position : " + position);
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
            Log.i(TAG, "tv.getTag() : " + v.getTag());
            mSelectedCell = v;
            v.setBackgroundColor(Color.parseColor("#FF00FF"));
            mSelectedPosition = position;
        } else {
            mSelectedPosition = -1;
        }
    }

    @Override
    public void onItemClick(Button button, int position) {
        Log.d(TAG, "onItemClick()");

        if (mSelectedPosition != -1) {
            String buttonTag = (String) button.getTag();
            BaseRequestSet req = null;

            if (mActivity.getString(R.string.button_del).equals(buttonTag)) {
                // 삭제 버튼
                req = new RequestDeleteSet(mAdapterModel.getItems(), mSelectedPosition);
            } else if(mActivity.getString(R.string.button_undo).equals(buttonTag)) {
                // Undo
                InvokeManager.getInstance().startUndo();
            } else if (mActivity.getString(R.string.button_redo).equals(buttonTag)) {
                // Redo
                InvokeManager.getInstance().startRedo();
            }  else  {
                req = new RequestNumberSet(buttonTag, mAdapterModel.getItems(), mSelectedPosition);
            }

            if (req != null) {
                req.setSuccessResponse(this);
                InvokeManager.getInstance().startCommand(req);
            }
        }
    }

    @Override
    public void onSuccess(Object result) {
        ResultSet set = (ResultSet) result;
        // 스도쿠 영역
        mAdapterView.notifyAdapter();

        // 버튼 영역
        mButtonAdapterModel.setUndoRedpAvailable(isUndoAvailable(), isRedoAvailable());
        mButtonAdapterView.notifyAdapter();
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    public void setGameAdapterModel(GameGridViewAdapter2 model) {
        mAdapterModel = model;
    }

    public void setGameAdapterView(GameGridViewAdapter2 view) {
        mAdapterView = view;
    }

    public void setButtonAdapterModel(ButtonGridViewAdapter2 model) {
        mButtonAdapterModel = model;
    }

    public void setButtonAdapterView(ButtonGridViewAdapter2 view) {
        mButtonAdapterView = view;
    }

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
     * 스도쿠 리스트 데이터를 반환한다.
     * @return
     */
    public ArrayList<SudokuData> getArraySudoku() {
        Log.d(TAG, "getArraySudoku()");
        return SudokuGameController.getInstance().getArraySudoku();
    }
//
//    /**
//     * 스도쿠를 생성하고 그린다.
//     */
//    public void createSudoku() {
//        Log.d(TAG, "createSudoku()");
//        SudokuGameController.getInstance().createGameGrid(mGameMode);
//    }
//
//    /**
//     * 스도쿠를 초기화 한다.
//     */
//    public void clearSudoku() {
//        SudokuGameController.getInstance().clearGridView();
//    }

    /**
     * 게임 시작을 위해 Adapter에 데이터를 셋팅 한다.
     */
    public void startGame() {
        mAdapterView.setOnClickListener(this);
        mButtonAdapterView.setOnClickListener(this);
        mAdapterModel.addItems(SudokuGameController.getInstance().getArraySudoku());
        mButtonAdapterModel.addItems(getButtonInfo());
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
     * 확인 버튼을 누른경우 스도쿠가 정확하게 완성 되었는지 확인 후 디스플레이 한다.
     */
    public void checkCompleteGame() {
        if (SudokuGameController.getInstance().isCompleteSudoku(SudokuGameUtils.getInstance().getSudokuListToArray(getArraySudoku()))) {
            mView.doResultDisplay(true);
        }

        mView.doResultDisplay(false);
    }

    /**
     * 버튼 리스트 데이터를 반환한다.
     * @return
     */
    public ArrayList<String> getButtonInfo() {
        Log.d(TAG, "getButtonInfo()");
        for (int i = 1; i <= 9; i++) {
            mButtonInfo.add(String.valueOf(i));
        }

        mButtonInfo.add(mActivity.getString(R.string.button_del));
        mButtonInfo.add(mActivity.getString(R.string.button_undo));
        mButtonInfo.add(mActivity.getString(R.string.button_redo));

        return mButtonInfo;
    }

    /**
     * Undo 기능이 가능한지 체크.
     * @return true is available
     */
    public boolean isUndoAvailable() {
        return InvokeManager.getInstance().isUndoAvailable();
    }

    /**
     * Redo 기능이 가능한지 체크.
     * @return true is available
     */
    public boolean isRedoAvailable() {
        return InvokeManager.getInstance().isRedoAvailable();
    }

}
