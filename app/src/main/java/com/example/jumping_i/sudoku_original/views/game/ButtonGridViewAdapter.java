package com.example.jumping_i.sudoku_original.views.game;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.data.ButtonData;

import java.util.ArrayList;

public class ButtonGridViewAdapter extends ArrayAdapter<ButtonData> {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private Context mContext;
    private ArrayList<ButtonData> mArrayItems;

    /*******************************************************************************
     * 생성자.
     *******************************************************************************/
    public ButtonGridViewAdapter(@NonNull Context context, ArrayList<ButtonData> items) {
        super(context, R.layout.list_item_button, items);

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
    public ButtonData getItem(int position) {
        return mArrayItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = LinearLayout.inflate(mContext, R.layout.list_item_button, null);
        } else {
            view = convertView;
        }

        ButtonData buttonInfo = getItem(position);

        Button button = view.findViewById(R.id.button_cell_bt);

        String strButtonName = buttonInfo.getButtonName();
        button.setText(strButtonName);
        button.setTag(strButtonName);

        if (mContext.getString(R.string.button_undo).equals(strButtonName) && buttonInfo.isUndoAvailable()) {
            button.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (mContext.getString(R.string.button_undo).equals(strButtonName) && ! buttonInfo.isUndoAvailable()){
            button.setTextColor(Color.parseColor("#000000"));
        }

        if (mContext.getString(R.string.button_redo).equals(strButtonName) && buttonInfo.isRedoAvailable()) {
            button.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (mContext.getString(R.string.button_redo).equals(strButtonName) && ! buttonInfo.isRedoAvailable()) {
            button.setTextColor(Color.parseColor("#000000"));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, position, 0);
            }
        });

        return view;
    }
}
