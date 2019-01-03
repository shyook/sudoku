package com.example.jumping_i.sudoku_original.views.sudoku_area;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class SudokuCell extends BaseSudokuCell {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = SudokuCell.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private Paint mPaint;

    /*******************************************************************************
     * Initialize.
     *******************************************************************************/
    public SudokuCell(Context context) {
        super(context);
        mPaint = new Paint();
    }

    /*******************************************************************************
     * Override.
     *******************************************************************************/
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawNumber(canvas);
        drawLines(canvas);
    }

    /*******************************************************************************
     * Private Method.
     *******************************************************************************/
    /**
     * sudoku cell의 숫자를 그린다.
     * @param canvas
     */
    private void drawNumber(Canvas canvas) {
        // Log.d(TAG, "drawNumber()");

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(60);
        mPaint.setStyle(Paint.Style.FILL);

        Rect bounds = new Rect();
        mPaint.getTextBounds(String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);
        if (getValue() != 0) {
            canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width()) / 2, (getHeight() + bounds.height()) / 2, mPaint);
        }
    }

    /**
     * sudoku cell의 라인을 그린다.
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        // Log.d(TAG, "drawLines()");

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
    }
}
