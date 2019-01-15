package com.example.jumping_i.sudoku_original.views.game2;

import android.widget.TextView;

import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.ArrayList;

public interface IOnItemClickListener {
    interface Game {
        void onItemClick(TextView v, SudokuData item, int position);
    }

    interface Button {
        void onItemClick(android.widget.Button button, int position);
    }
}
