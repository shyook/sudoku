package com.example.jumping_i.sudoku_original.views.game2;

import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.ArrayList;

public interface IGameAdapterContract {
    interface View {
        void setOnClickListener(IOnItemClickListener.Game clickListener);

        void notifyAdapter();
    }

    interface Model {
        void addItems(ArrayList<SudokuData> datas);

        void changeItem(int position, SudokuData data);

        void clearItem();

        ArrayList<SudokuData> getItems();
    }
}
