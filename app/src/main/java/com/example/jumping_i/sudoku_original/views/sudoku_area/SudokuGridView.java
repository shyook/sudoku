package com.example.jumping_i.sudoku_original.views.sudoku_area;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.jumping_i.sudoku_original.utils.SudokuGameController;
import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;
import com.example.jumping_i.sudoku_original.views.buttons_area.ButtonsGridView;

public class SudokuGridView extends GridView {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = SudokuGridView.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private Context mContext;

    /*******************************************************************************
     * Initialize.
     *******************************************************************************/
    public SudokuGridView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        SudokuGridViewAdapter adapter = new SudokuGridViewAdapter(context);
        setAdapter(adapter);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int x = position % SudokuGenerator.SUDOKU_ROW;
                int y = position / SudokuGenerator.SUDOKU_COL;

                SudokuGameController.getInstance().setSelectedPosition(x, y);
                Toast.makeText(mContext, "Select Item x : " + x + " y : " + y, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*******************************************************************************
     * Override.
     *******************************************************************************/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    /*******************************************************************************
     * Inner Class.
     *******************************************************************************/
    class SudokuGridViewAdapter extends BaseAdapter {

        private Context mContext;

        public SudokuGridViewAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return SudokuGenerator.SUDOKU_TOTAL_NUMBER;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d(TAG, "SudokuGridViewAdapter - getView()");
            return SudokuGameController.getInstance().getGameGrid().getItem(position);
        }
    }
}
