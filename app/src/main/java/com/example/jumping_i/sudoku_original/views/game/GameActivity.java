package com.example.jumping_i.sudoku_original.views.game;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.base.BaseActivity;
import com.example.jumping_i.sudoku_original.consts.IConsts;
import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.ArrayList;

public class GameActivity extends BaseActivity implements IGameContractView {
    private static final String TAG = GameActivity.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private GamePresenter mPresenter;
    private GridView mGridView;
    private GameGridViewAdapter mAdapter;           // Game Adapter
    private ButtonGridViewAdapter mButtonAdapter;   // 버튼 Adapter

    /*******************************************************************************
     * Life Cycle.
     *******************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_game);

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    /*******************************************************************************
     * View Implement.
     *******************************************************************************/
    @Override
    protected void init() {
        mPresenter = new GamePresenter();
        mPresenter.attachView(this);

        // 번들에서 난이도 정보를 얻어 온다.
        Intent intent = getIntent();
        if (intent != null) {
            int mode = intent.getIntExtra(IConsts.IntentConsts.BUNDLE_GAME_MODE, -1);
            mPresenter.setGameMode(mode);
            setTitle(getString(R.string.title_game_mode, getString(mode)));
        }

        mPresenter.clearSudoku();
        mPresenter.createSudoku();

        // Game Cell Area
        mGridView = findViewById(R.id.game_cell_gv);
        mAdapter = new GameGridViewAdapter(this, mPresenter.getArraySudoku());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(mItemClickListener);

        // Number Button Area
        GridView buttonGridView = findViewById(R.id.game_button_cell_gv);
        mButtonAdapter = new ButtonGridViewAdapter(this, mPresenter.getButtonInfo());
        buttonGridView.setAdapter(mButtonAdapter);
        buttonGridView.setOnItemClickListener(mButtonItemClickListener);

        // Confirm Button Area
        Button confirmButton = findViewById(R.id.game_confirm_bt);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPresenter.confirmGame()) {
                    mPresenter.checkCompleteGame();
                } else {
                    Toast.makeText(getActivity(), R.string.sudoku_game_file_because_empty_cell, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mPresenter.setGameState();
    }

    /**
     * 데이터 변경으로 다시 그린다.
     * @param arrItems
     */
    @Override
    public void changeSudokuDataSet(ArrayList<SudokuData> arrItems) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void changeButtonDataSet() {
        mButtonAdapter.notifyDataSetChanged();
    }

    /**
     * 성공 여부를 디스플레이 한다.
     *
     * @param result
     */
    @Override
    public void doResultDisplay(boolean result) {
        if (result) {
            Toast.makeText(this, R.string.sudoku_game_complete, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.sudoku_game_fail, Toast.LENGTH_SHORT).show();
        }
    }

    /*******************************************************************************
     * Click Event.
     *******************************************************************************/
    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {
            Log.i(TAG, "position : " + position + "  , l_position : " + l_position);
            TextView tv = view.findViewById(R.id.sudoku_cell_tv);
            mPresenter.setSelectSudokuCell(tv, mAdapter.getItem(position), position);

            Toast.makeText(getActivity(), position + ", " + tv.getText(), Toast.LENGTH_SHORT).show();
        }
    };

    private AdapterView.OnItemClickListener mButtonItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {
            Log.i(TAG, "position : " + position + "  , l_position : " + l_position);
            Button button = view.findViewById(R.id.button_cell_bt);
            mPresenter.onClickButtonEvent(button, position);

            Toast.makeText(getActivity(), position + ", " + button.getTag(), Toast.LENGTH_SHORT).show();
        }
    };
}
