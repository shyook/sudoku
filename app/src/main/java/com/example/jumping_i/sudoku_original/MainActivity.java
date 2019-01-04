package com.example.jumping_i.sudoku_original;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jumping_i.sudoku_original.base.BaseActivity;
import com.example.jumping_i.sudoku_original.views.menu.IModeContractView;
import com.example.jumping_i.sudoku_original.views.menu.ModePresenter;

public class MainActivity extends BaseActivity implements IModeContractView {
    private static final String TAG = MainActivity.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private ModePresenter mPresenter;
    private ImageButton mUpButton;
    private ImageButton mDownButton;
    private TextView mModeDisplay;
    private Button mStartButton;

    /*******************************************************************************
     * Life Cycle.
     *******************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
        setTitle(R.string.title_game_mode_select);

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
        Log.d(TAG, "MainActivity - init()");

        mPresenter = new ModePresenter();
        mPresenter.attachView(this);

        mUpButton = findViewById(R.id.arrow_up_iv);
        mDownButton = findViewById(R.id.arrow_down_iv);
        mModeDisplay = findViewById(R.id.game_mode_tv);
        mStartButton = findViewById(R.id.game_start_bt);

        mUpButton.setOnClickListener(mClickListener);
        mDownButton.setOnClickListener(mClickListener);
        mStartButton.setOnClickListener(mClickListener);

        mPresenter.initGameMode();
    }

    @Override
    public void updateGameModeView(int mode) {
        Log.d(TAG, "updateGameModeView()");
        mModeDisplay.setText(mode);
    }

    /*******************************************************************************
     * Private Method.
     *******************************************************************************/
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.arrow_up_iv:
                    mPresenter.changeMode(true);
                    break;

                case R.id.arrow_down_iv:
                    mPresenter.changeMode(false);
                    break;

                case R.id.game_start_bt:
                    mPresenter.startGame();
                    break;
            }
        }
    };
}
