package com.example.jumping_i.sudoku_original;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jumping_i.sudoku_original.base.BaseActivity;
import com.example.jumping_i.sudoku_original.base.Config;
import com.example.jumping_i.sudoku_original.views.ad.dialog.NativeAdDialog;
import com.example.jumping_i.sudoku_original.views.menu.IModeContractView;
import com.example.jumping_i.sudoku_original.views.menu.ModePresenter;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends BaseActivity implements IModeContractView {
    private static final String TAG = MainActivity.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private ModePresenter mPresenter;
    private ImageButton mUpButton;
    private ImageButton mDownButton;
    private TextView mModeDisplay;
    private ImageView mModeImage;
    private Button mStartButton;
    private Button mServerInterfaceTestButton; // Retrofit Test Button

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

    @Override
    public void onBackPressed() {
//        if (mPresenter.checkExitApp()) {
//            super.onBackPressed();
//        }
        NativeAdDialog dialog = new NativeAdDialog(this);
        dialog.show();
    }

    /*******************************************************************************
     * View Implement.
     *******************************************************************************/
    @Override
    protected void init() {
        Log.d(TAG, "MainActivity - init()");

        mPresenter = new ModePresenter();
        mPresenter.attachView(this);
        MobileAds.initialize(this, getString(R.string.admob_app_id));

        mUpButton = findViewById(R.id.arrow_up_iv);
        mDownButton = findViewById(R.id.arrow_down_iv);
        mModeDisplay = findViewById(R.id.game_mode_tv);
        mModeImage = findViewById(R.id.game_mode_iv);
        mStartButton = findViewById(R.id.game_start_bt);
        mServerInterfaceTestButton = findViewById(R.id.server_api_test_bt);
        if (Config.IS_DEBUG) {
            mServerInterfaceTestButton.setVisibility(View.VISIBLE);
        }

        mUpButton.setOnClickListener(mClickListener);
        mDownButton.setOnClickListener(mClickListener);
        mStartButton.setOnClickListener(mClickListener);
        mServerInterfaceTestButton.setOnClickListener(mClickListener);

        mPresenter.initGameMode();
    }

    @Override
    public void updateGameModeView(int mode) {
        Log.d(TAG, "updateGameModeView()");
        mModeDisplay.setText(mode);
        changeGameModeImage(mode);
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

                case R.id.server_api_test_bt:
                    mPresenter.retrofitTest();
                    break;
            }
        }
    };

    private void changeGameModeImage(int mode) {
        switch (mode) {
            case R.string.sudoku_game_mode_1:
                mModeImage.setImageResource(R.drawable.main_icon_info_d);
                break;

            case R.string.sudoku_game_mode_2:
                mModeImage.setImageResource(R.drawable.main_icon_setting_d);
                break;

            case R.string.sudoku_game_mode_3:
                mModeImage.setImageResource(R.drawable.main_icon_ap_d);
                break;

            case R.string.sudoku_game_mode_4:
                mModeImage.setImageResource(R.drawable.main_icon_ap_n);
                break;

            case R.string.sudoku_game_mode_5:
                mModeImage.setImageResource(R.drawable.main_icon_setting_n);
                break;

            case R.string.sudoku_game_mode_6:
                mModeImage.setImageResource(R.drawable.main_icon_info_n);
                break;

            case R.string.sudoku_game_mode_7:
                mModeImage.setImageResource(R.drawable.main_icon_ap_n);
                break;

            case R.string.sudoku_game_mode_8:
                mModeImage.setImageResource(R.drawable.main_icon_ap_d);
                break;

            case R.string.sudoku_game_mode_9:
                mModeImage.setImageResource(R.drawable.main_icon_setting_d);
                break;

            case R.string.sudoku_game_mode_10:
                mModeImage.setImageResource(R.drawable.main_icon_setting_n);
                break;
        }
    }
}
