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
import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;

import java.util.ArrayList;

public class GameGridViewAdapter extends ArrayAdapter<SudokuData> {
    private Context mContext;
    private ArrayList<SudokuData> mArrayItems;

    public GameGridViewAdapter(@NonNull Context context, ArrayList<SudokuData> items) {
        super(context, R.layout.list_item_sudoku, items);
        mContext = context;
        mArrayItems = items;
    }

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

        SudokuData sudokuInfo = getItem(position);
        TextView textView = view.findViewById(R.id.sudoku_cell_tv);

        if (sudokuInfo != null) {
            if (sudokuInfo.getSudokuInfo() != 0) {
                textView.setText(String.valueOf(sudokuInfo.getSudokuInfo()));
            } else {
                textView.setText("");
            }
        }

        if (sudokuInfo.getSudokuInfo() != 0 && sudokuInfo.isModification()) {
            textView.setBackgroundColor(Color.parseColor("#FF9900"));
        } else if (SudokuGameUtils.getInstance().isColorRegion(position)) {
            textView.setBackgroundColor(Color.parseColor("#FFFF00"));
        } else {
            textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        return view;
    }

}
