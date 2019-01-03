package com.example.jumping_i.sudoku_original.views.buttons_area;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.jumping_i.sudoku_original.utils.SudokuGameController;

public class NumberButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = NumberButton.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private int mNumber;

    /*******************************************************************************
     * Initialize.
     *******************************************************************************/
    /**
     * 생성자.
     *
     * @param context
     * @param attrs
     */
    public NumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    /*******************************************************************************
     * Override.
     *******************************************************************************/
    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick()");
        SudokuGameController.getInstance().setNumber(mNumber);
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    /**
     * 버튼 영역의 숫자를 저장 한다.
     * @param number
     */
    public void setNumber(int number) {
        // Log.d(TAG, "setNumber()");
        mNumber = number;
    }
}
