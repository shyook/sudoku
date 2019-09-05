package com.example.jumping_i.sudoku_original.views.game2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.base.BaseActivity;
import com.example.jumping_i.sudoku_original.consts.IConsts;
import com.example.jumping_i.sudoku_original.utils.DialogUtils;

public class GameActivity2 extends BaseActivity implements IGameContractView2 {
    private static final String TAG = GameActivity2.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private GamePresenter2 mPresenter;
    private GridView mGridView;
    private GameGridViewAdapter2 mAdapter;           // Game Adapter
    private ButtonGridViewAdapter2 mButtonAdapter;   // 버튼 Adapter

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
        mPresenter = new GamePresenter2();
        mPresenter.attachView(this);

        // 번들에서 난이도 정보를 얻어 온다.
        Intent intent = getIntent();
        if (intent != null) {
            int mode = intent.getIntExtra(IConsts.IntentConsts.BUNDLE_GAME_MODE, -1);
            mPresenter.setGameMode(mode);
            setTitle(getString(R.string.title_game_mode, getString(mode)));
        }

        mPresenter.clearSudoku();
        // mPresenter.createSudoku(mGameMode);

        // Game Cell Area
        mGridView = findViewById(R.id.game_cell_gv);
        mAdapter = new GameGridViewAdapter2(this);
        mGridView.setAdapter(mAdapter);
        mPresenter.setGameAdapterModel(mAdapter);
        mPresenter.setGameAdapterView(mAdapter);

        // Number Button Area
        GridView buttonGridView = findViewById(R.id.game_button_cell_gv);
        mButtonAdapter = new ButtonGridViewAdapter2(this);
        buttonGridView.setAdapter(mButtonAdapter);
        mPresenter.setButtonAdapterModel(mButtonAdapter);
        mPresenter.setButtonAdapterView(mButtonAdapter);

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

        mPresenter.startGame();
    }

    @Override
    public void doResultDisplay(boolean result) {
        if (result) {
            // Toast.makeText(this, R.string.sudoku_game_complete, Toast.LENGTH_SHORT).show();
            DialogUtils.alert(this, getString(R.string.popup_title_complete)
                    , getString(R.string.popup_message_complete), null);
        } else {
            // Toast.makeText(this, R.string.sudoku_game_fail, Toast.LENGTH_SHORT).show();
            DialogUtils.alert(this, getString(R.string.popup_default_title)
                    , getString(R.string.popup_message_game_fail), null);
        }
    }
}
