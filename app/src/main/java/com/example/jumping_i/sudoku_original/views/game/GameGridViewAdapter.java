package com.example.jumping_i.sudoku_original.views.game;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.data.SudokuData;
import com.example.jumping_i.sudoku_original.utils.SudokuGameUtils;

import java.util.ArrayList;

public class GameGridViewAdapter extends ArrayAdapter<SudokuData> {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private Context mContext;
    private ArrayList<SudokuData> mArrayItems;

    /*******************************************************************************
     * 생성자.
     *******************************************************************************/
    public GameGridViewAdapter(@NonNull Context context, ArrayList<SudokuData> items) {
        super(context, R.layout.list_item_sudoku, items);
        mContext = context;
        mArrayItems = items;
    }

    /*******************************************************************************
     * Override Method.
     *******************************************************************************/
    @Override
    public int getCount() {
        return mArrayItems.size();
    }

    @Nullable
    @Override
    public SudokuData getItem(int position) {
        return mArrayItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = LinearLayout.inflate(mContext, R.layout.list_item_sudoku, null);
        } else {
            view = convertView;
        }

        // 데이터를 획득한다.
        SudokuData sudokuInfo = getItem(position);
        TextView textView = view.findViewById(R.id.sudoku_cell_tv);

        if (sudokuInfo != null) {
            if (sudokuInfo.getSudokuInfo() != 0) {
                textView.setText(String.valueOf(sudokuInfo.getSudokuInfo()));
            } else {
                textView.setText("");
            }

            // 색상을 표시 한다.
            if (sudokuInfo.getSudokuInfo() != 0 && sudokuInfo.isModification()) {
                textView.setBackgroundColor(Color.parseColor("#FF9900"));
            } else if (SudokuGameUtils.getInstance().isColorRegion(position)) {
                textView.setBackgroundColor(Color.parseColor("#FFFF00"));
            } else {
                textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }

        return view;
    }
}
