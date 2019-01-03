package com.example.jumping_i.sudoku_original.views.sudoku_area;

import android.content.Context;
import android.util.Log;
import android.view.View;

public class BaseSudokuCell extends View {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = BaseSudokuCell.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private int mValue;
    private boolean mModifiable = true;

    /*******************************************************************************
     * Initialize.
     *******************************************************************************/
    /**
     * 생성자.
     *
     * @param context
     */
    public BaseSudokuCell(Context context) {
        super(context);
    }

    /*******************************************************************************
     * Override.
     *******************************************************************************/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    public void setNotModifiable() {
        Log.d(TAG, "setNotModifiable()");
        mModifiable = false;
    }

    public void setInitValue(int value) {
        Log.d(TAG, "setInitValue()");
        mValue = value;
        invalidate();
    }

    public void setValue(int value) {
        Log.d(TAG, "setValue()");
        if (mModifiable) {
            mValue = value;
        }
        invalidate();
    }

    public int getValue() {
        // Log.d(TAG, "getValue()");
        return mValue;
    }
}
