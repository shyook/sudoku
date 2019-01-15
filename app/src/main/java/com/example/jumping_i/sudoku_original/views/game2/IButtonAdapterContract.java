package com.example.jumping_i.sudoku_original.views.game2;

import java.util.ArrayList;

public interface IButtonAdapterContract {
    interface View {
        void setOnClickListener(IOnItemClickListener.Button clickListener);

        void notifyAdapter();
    }

    interface Model {
        void addItems(ArrayList<String> buttonInfo);

        void setUndoRedpAvailable(boolean undo, boolean redo);
    }
}
