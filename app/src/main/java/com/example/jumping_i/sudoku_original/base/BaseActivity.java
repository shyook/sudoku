package com.example.jumping_i.sudoku_original.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.utils.DialogUtils;
import com.example.jumping_i.sudoku_original.utils.Utils;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    private static final String TAG = BaseActivity.class.getSimpleName();

    private Dialog mProgressDialog = null;

    protected abstract void init();

    @Override
    public Activity getActivity() {
        return this;
    }

    /*******************************************************************************
     * Progress Dialog.
     *******************************************************************************/
    /**
     * 전체 화면 다이얼 로그.
     */
    protected void showProgress(boolean isCancelable, DialogInterface.OnCancelListener listener) {
        Log.d(TAG, ">> showProgress()");

        if (!Utils.checkActivityState(this)) {
            return;
        }

        DialogUtils.showProgress(this, isCancelable, listener);
    }

    /**
     * 전체 프로그래스바 다이얼로그를 닫는다.
     */
    protected void closeProgress() {
        Log.d(TAG, ">> closeProgress()");

        if (!Utils.checkActivityState(this)) {
            // 액티비티가 종료 되었거나, 윈도우가 없거나, 다이얼로그 인스턴스의 윈도우가 없으면 리턴
            return;
        }

        DialogUtils.dismissDialog();
    }
}
