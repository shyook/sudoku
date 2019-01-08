package com.example.jumping_i.sudoku_original.views.game;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.base.BasePresenter;
import com.example.jumping_i.sudoku_original.base.Config;
import com.example.jumping_i.sudoku_original.data.ButtonData;
import com.example.jumping_i.sudoku_original.data.SudokuData;
import com.example.jumping_i.sudoku_original.retrofit.IResultListener;
import com.example.jumping_i.sudoku_original.retrofit.RequestApiType;
import com.example.jumping_i.sudoku_original.retrofit.RetrofitManager;
import com.example.jumping_i.sudoku_original.retrofit.data.ResponseDataObj;
import com.example.jumping_i.sudoku_original.retrofit.serverInterface.IParams;
import com.example.jumping_i.sudoku_original.utils.SharedPrefManager;
import com.example.jumping_i.sudoku_original.utils.SudokuGameController;
import com.example.jumping_i.sudoku_original.utils.SudokuGameUtils;
import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;
import com.example.jumping_i.sudoku_original.views.history.BaseRequestSet;
import com.example.jumping_i.sudoku_original.views.history.ISuccessResponse;
import com.example.jumping_i.sudoku_original.views.history.InvokeManager;
import com.example.jumping_i.sudoku_original.views.history.RequestDeleteSet;
import com.example.jumping_i.sudoku_original.views.history.RequestNumberSet;
import com.example.jumping_i.sudoku_original.views.history.ResultSet;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GamePresenter extends BasePresenter<IGameContractView> implements ISuccessResponse {
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


    @Override
    public void onSuccess(Object result) {
        ResultSet set = (ResultSet) result;
        // 수도쿠 영역
        mView.changeSudokuDataSet(set.getArraySudokuData());

        // 버튼 영역
        ButtonData undoRedoInfo = new ButtonData();
        undoRedoInfo.setUndoAvailable(isUndoAvailable());
        undoRedoInfo.setRedoAvailable(isRedoAvailable());
        mView.changeButtonDataSet();
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
        SudokuGameController.getInstance().createGameGrid(mActivity, mGameMode);
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
    public ArrayList<ButtonData> getButtonInfo() {
        Log.d(TAG, "getButtonInfo()");
        ArrayList<ButtonData> arrButtonInfo = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            // arrButtonInfo.add(new ButtonData().setButtonName(String.valueOf(i)));
            ButtonData buttonInfo = new ButtonData();
            buttonInfo.setButtonName(String.valueOf(i));
            arrButtonInfo.add(buttonInfo);
        }

        ButtonData delButton = new ButtonData();
        delButton.setButtonName(mActivity.getString(R.string.button_del));
        arrButtonInfo.add(delButton);

        ButtonData undoButton = new ButtonData();
        undoButton.setButtonName(mActivity.getString(R.string.button_undo));
        undoButton.setUndoAvailable(false);
        arrButtonInfo.add(undoButton);

        ButtonData redoButton = new ButtonData();
        redoButton.setButtonName(mActivity.getString(R.string.button_redo));
        redoButton.setRedoAvailable(false);
        arrButtonInfo.add(redoButton);

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
     * 버튼을 클릭한 경우 해당 셀을 변경한다 (Command Pattern 이용).
     * @param button
     * @param position
     */
    public void onClickButtonEvent(Button button, int position) {
        Log.d(TAG, "onClickButtonEvent()");

        if (mSelectedPosition != -1) {
            String buttonTag = (String) button.getTag();
            BaseRequestSet req = null;

            if (mActivity.getString(R.string.button_del).equals(buttonTag)) {
                // 삭제 버튼
                req = new RequestDeleteSet(getArraySudoku(), mSelectedPosition);
            } else if(mActivity.getString(R.string.button_undo).equals(buttonTag)) {
                // Undo
                InvokeManager.getInstance().startUndo();
            } else if (mActivity.getString(R.string.button_redo).equals(buttonTag)) {
                // Redo
                InvokeManager.getInstance().startRedo();
            }  else  {
                req = new RequestNumberSet(buttonTag, getArraySudoku(), mSelectedPosition);
            }

            if (req != null) {
                req.setSuccessResponse(this);
                InvokeManager.getInstance().startCommand(req);
            }
        }
    }

    /**
     * 버튼을 클릭한 경우 해당 셀을 변경한다.
     * @param button
     * @param position
     */
//    public void onClickButtonEvent(Button button, int position) {
//        Log.d(TAG, "onClickButtonEvent()");
//
//        if (mSelectedPosition != -1) {
//            String buttonTag = (String) button.getTag();
//            ArrayList<SudokuData> arrItems = getArraySudoku();
//
//            if (mActivity.getString(R.string.button_del).equals(buttonTag)) {
//                // 삭제 버튼
//                arrItems.remove(mSelectedPosition);
//                arrItems.add(mSelectedPosition, new SudokuData(0, true));
//            } else {
//                arrItems.remove(mSelectedPosition);
//                arrItems.add(mSelectedPosition, new SudokuData(Integer.parseInt(buttonTag), true));
//            }
//
//            mView.changeSudokuDataSet(arrItems);
//        }
//    }

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

    /**
     * 수도쿠를 초기화 한다.
     */
    public void clearSudoku() {
        SudokuGameController.getInstance().clearGridView();
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

    /**
     * 현재 게임 상태를 SharedPreference에 저장 한다.
     *
     * 현재 해당 영역을 사용하지는 않고 SharedPreference 테스트용으로 사용중.
     */
    public void setGameState() {
        SharedPrefManager.getInstance(mActivity).setSharedValueByString(SharedPrefManager.PREF_KEYS.SUDOKU_GAME_STATE_DATA, mActivity.getString(R.string.sudoku_state_start_game));

        if (Config.IS_DEBUG) {
            ArrayList<String> stringList = new ArrayList<>();
            stringList.add("A");
            stringList.add("B");
            stringList.add("C");

            List<Integer> intList = new ArrayList<>();
            intList.add(1);
            intList.add(2);
            intList.add(3);

            List<Boolean> boolList = new ArrayList<>();
            boolList.add(true);
            boolList.add(false);

            SharedPrefManager.getInstance(mActivity).setJsonArrayList(SharedPrefManager.PREF_KEYS.TEST_SHARED_KEY_STRING, stringList);
            Log.i(TAG, "String Json Array" + SharedPrefManager.getInstance(mActivity).getJsonArrayList(SharedPrefManager.PREF_KEYS.TEST_SHARED_KEY_STRING, new ArrayList<String>()));

            SharedPrefManager.getInstance(mActivity).setJsonArrayList(SharedPrefManager.PREF_KEYS.TEST_SHARED_KEY_INT, intList);
            Log.i(TAG, "Int Json Array" + SharedPrefManager.getInstance(mActivity).getJsonArrayList(SharedPrefManager.PREF_KEYS.TEST_SHARED_KEY_INT, new ArrayList<Integer>()));

            SharedPrefManager.getInstance(mActivity).setJsonArrayList(SharedPrefManager.PREF_KEYS.TEST_SHARED_KEY_BOOLEAN, boolList);
            Log.i(TAG, "Boolean Json Array" + SharedPrefManager.getInstance(mActivity).getJsonArrayList(SharedPrefManager.PREF_KEYS.TEST_SHARED_KEY_BOOLEAN, new ArrayList<Boolean>()));
        }
    }
}
