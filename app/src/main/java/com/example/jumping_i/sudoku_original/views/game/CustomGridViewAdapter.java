package com.example.jumping_i.sudoku_original.views.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.jumping_i.sudoku_original.R;

import java.util.ArrayList;

public class CustomGridViewAdapter extends ArrayAdapter<Integer> {
    public CustomGridViewAdapter(@NonNull Context context, ArrayList<Integer> items) {
        super(context, R.layout.list_item_sudoku, items);
    }
}
