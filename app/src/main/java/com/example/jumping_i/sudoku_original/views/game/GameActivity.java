package com.example.jumping_i.sudoku_original.views.game;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.base.BaseActivity;
import com.example.jumping_i.sudoku_original.base.Config;
import com.example.jumping_i.sudoku_original.consts.IConsts;
import com.example.jumping_i.sudoku_original.utils.SudokuGameController;
import com.example.jumping_i.sudoku_original.views.sudoku_area.SudokuCell;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends BaseActivity implements IGameContractView {
    private static final String TAG = GameActivity.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private GamePresenter mPresenter;
    private GridView mGridView;
    private CustomGridViewAdapter mAdapter;

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
        Intent i = getIntent();
        if (i != null) {
            int mode = i.getIntExtra(IConsts.IntentConsts.BUNDLE_GAME_MODE, -1);
            mPresenter.setGameMode(mode);
            setTitle(getString(R.string.title_game_mode, getString(mode)));
        }
    }
}
