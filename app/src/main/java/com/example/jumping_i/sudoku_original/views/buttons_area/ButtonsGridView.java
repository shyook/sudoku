package com.example.jumping_i.sudoku_original.views.buttons_area;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;

public class ButtonsGridView extends GridView {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = ButtonsGridView.class.getSimpleName();
    private static final int TOTAL_BUTTON_NUMBER = 10;

    /*******************************************************************************
     * Initialize.
     *******************************************************************************/
    /**
     * 생성자.
     *
     * @param context
     * @param attrs
     */
    public ButtonsGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        ButtonsGridViewAdapter adapter = new ButtonsGridViewAdapter(context);
        setAdapter(adapter);
    }

    /*******************************************************************************
     * Inner Class.
     *******************************************************************************/
    class ButtonsGridViewAdapter extends BaseAdapter {
        private Context mContext;

        public ButtonsGridViewAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return TOTAL_BUTTON_NUMBER;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Log.d(TAG, "ButtonsGridViewAdapter - getView()");
            View view = convertView;

            if (view == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                view = inflater.inflate(R.layout.view_sudoku_button, parent, false);

                NumberButton button = (NumberButton) view;
                button.setId(position);
                button.setTextSize(10);

                if (position != 9) {
                    button.setText(String.valueOf(position + 1));
                    button.setNumber(position + 1);
                } else {
                    button.setText(R.string.button_del);
                    button.setNumber(0);
                }
                return button;
            }
            return view;
        }
    }
}
