package com.example.jumping_i.sudoku_original.views.game2;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.jumping_i.sudoku_original.R;

import java.util.ArrayList;

public class ButtonGridViewAdapter2 extends ArrayAdapter<String> implements IButtonAdapterContract.Model, IButtonAdapterContract.View {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private Context mContext;
    private ArrayList<String> mArrayItems;
    private IOnItemClickListener.Button mListener;
    private boolean mIsUndoAble = false, mIsRedoAble = false;

    /*******************************************************************************
     * 생성자.
     *******************************************************************************/
    public ButtonGridViewAdapter2(@NonNull Context context) {
        super(context, R.layout.list_item_button);

        mContext = context;
    }

    /*******************************************************************************
     * Override Method.
     *******************************************************************************/
    @Override
    public int getCount() {
        return mArrayItems != null ? mArrayItems.size() : 0;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return mArrayItems != null ? mArrayItems.get(position) : null;
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

        if (mContext.getString(R.string.button_undo).equals(buttonInfo) && mIsUndoAble) {
            button.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (mContext.getString(R.string.button_undo).equals(buttonInfo) && ! mIsUndoAble) {
            button.setTextColor(Color.parseColor("#000000"));
        }

        if (mContext.getString(R.string.button_redo).equals(buttonInfo) && mIsRedoAble) {
            button.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (mContext.getString(R.string.button_redo).equals(buttonInfo) && ! mIsRedoAble) {
            button.setTextColor(Color.parseColor("#000000"));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    Button button = v.findViewById(R.id.button_cell_bt);
                    mListener.onItemClick(button, position);
                }
            }
        });

        return view;
    }

    @Override
    public void setOnClickListener(IOnItemClickListener.Button clickListener) {
        mListener = clickListener;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void addItems(ArrayList<String> buttonInfo) {
        mArrayItems = buttonInfo;
    }

    @Override
    public void setUndoRedpAvailable(boolean undo, boolean redo) {
        mIsUndoAble = undo;
        mIsRedoAble = redo;
    }
}
