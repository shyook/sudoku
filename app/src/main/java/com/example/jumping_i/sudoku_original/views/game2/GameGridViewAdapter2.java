package com.example.jumping_i.sudoku_original.views.game2;

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

public class GameGridViewAdapter2  extends ArrayAdapter<SudokuData> implements IGameAdapterContract.Model, IGameAdapterContract.View {
    private static final String TAG = GameGridViewAdapter2.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private IOnItemClickListener.Game mOnItemClickListener;
    private Context mContext;
    private ArrayList<SudokuData> mArrayItems;

    /*******************************************************************************
     * 생성자.
     *******************************************************************************/
    public GameGridViewAdapter2(@NonNull Context context) {
        super(context, R.layout.list_item_sudoku);
        mContext = context;
    }

    /*******************************************************************************
     * Override Method.
     *******************************************************************************/
    @Override
    public int getCount() {
        return mArrayItems != null ? mArrayItems.size() : 0;
    }

    @Override
    public void addItems(ArrayList<SudokuData> items) {
        mArrayItems = items;
    }

    @Override
    public void changeItem(int position, SudokuData data) {

    }

    @Override
    public void clearItem() {
        if (mArrayItems != null) {
            mArrayItems.clear();
        }
    }

    @Override
    public ArrayList<SudokuData> getItems() {
        return mArrayItems;
    }


    @Override
    public void setOnClickListener(IOnItemClickListener.Game clickListener) {
        mOnItemClickListener = clickListener;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public SudokuData getItem(int position) {
        return mArrayItems != null ? mArrayItems.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    TextView tv = v.findViewById(R.id.sudoku_cell_tv);
                    mOnItemClickListener.onItemClick(tv, mArrayItems.get(position), position);
                }
            }
        });

        return view;
    }
}
