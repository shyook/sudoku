package com.example.jumping_i.sudoku_original.views.game;

import android.content.Context;
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

import java.util.ArrayList;

public class ButtonGridViewAdapter extends ArrayAdapter<String> {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private Context mContext;
    private ArrayList<String> mArrayItems;

    /*******************************************************************************
     * 생성자.
     *******************************************************************************/
    public ButtonGridViewAdapter(@NonNull Context context, ArrayList<String> items) {
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
    public String getItem(int position) {
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

        String buttonInfo = getItem(position);

        Button button = view.findViewById(R.id.button_cell_bt);
        button.setText(buttonInfo);
        button.setTag(buttonInfo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, position, 0);
            }
        });


        return view;
    }
}
