package com.example.jumping_i.sudoku_original.views.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.base.BasePresenter;
import com.example.jumping_i.sudoku_original.consts.IConsts;
import com.example.jumping_i.sudoku_original.db.dao.WeatherDao;
import com.example.jumping_i.sudoku_original.db.data.Weather;
import com.example.jumping_i.sudoku_original.retrofit.IResultListener;
import com.example.jumping_i.sudoku_original.retrofit.data.ResponseDataObj;
import com.example.jumping_i.sudoku_original.retrofit.serverInterface.ServerInterface;
import com.example.jumping_i.sudoku_original.utils.DialogUtils;
import com.example.jumping_i.sudoku_original.utils.SudokuGenerator;
import com.example.jumping_i.sudoku_original.views.game.GameActivity;

import java.util.List;

public class ModePresenter extends BasePresenter<IModeContractView> {
    private static final String TAG = ModePresenter.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private IModeContractView mView = null;
    private Activity mActivity = null;
    private SudokuGenerator.eGameMode mGameMode = SudokuGenerator.eGameMode.SUDOKU_LEVEL_5;
    /** BackKey press Count. */
    private int mBackButtonClickCount = 0;

    /*******************************************************************************
     * Interface Override.
     *******************************************************************************/
    @Override
    public void attachView(IModeContractView view) {
        mView = view;
        mActivity = view.getActivity();
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    /*******************************************************************************
     * public method.
     *******************************************************************************/
    /**
     * 게임 모드 선택 변경에 대한 동작을 수행한다.
     * @param isUp
     */
    public void changeMode(boolean isUp) {
        Log.d(TAG, "changeMode()");
        if (isUp) {
            mGameMode = GameModeState.getInstance().getNextUpState();
        } else {
            mGameMode = GameModeState.getInstance().getNextDownState();
        }

        updateGameMode(mGameMode);
    }

    /**
     * 처음 진입시 게임 모드를 셋팅한다.
     */
    public void initGameMode() {
        Log.d(TAG, "initGameMode()");
        mView.updateGameModeView(mGameMode.getMode());
        GameModeState.getInstance().initGameMode(mGameMode);    // 게임 모드 초기화.
    }

    /**
     * 선택한 모드로 게임을 시작한다.
     */
    public void startGame() {
        Log.d(TAG, "initGameMode()");
        long startTime = System.currentTimeMillis();
        clearSudoku();
        createSudoku(mGameMode);
        long endTime = System.currentTimeMillis();
        Log.i(TAG, "create sudoku : " + (endTime - startTime) / 1000.0);
        Intent i = new Intent(mActivity, GameActivity.class);
        i.putExtra(IConsts.IntentConsts.BUNDLE_GAME_MODE, mGameMode.getMode());
        mActivity.startActivity(i);
    }

    /**
     * 뒤로 버튼을 두번 클릭했을때 앱 종료 하도록 체크.
     * @return
     */
    public boolean checkExitApp() {
        Log.d(TAG, "checkExitApp()");
        mBackButtonClickCount++;
        if (mBackButtonClickCount == 1) {
            Toast.makeText(mActivity, R.string.toast_finish_app, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    mBackButtonClickCount = 0;
                }
            }, 3000);
            return false;
        }
        return true;
    }

    /**
     * 현재 게임 모드를 반환 한다.(Unit Test를 위해 추가.)
     *
     * @return
     */
    public SudokuGenerator.eGameMode getGameMode() {
        return mGameMode;
    }

    /**
     * retrofit 라이브러리를 통해 Weather 데이터를 읽어 오고<br>
     * OrmLite 라이브러를 통해 db에 저장 하는 테스트를 위한 Method.
     */
    public void retrofitTest() {
        ServerInterface.getInstance(mActivity).requestWeatherHourly(2, "36.1234", "127.1234", new IResultListener<ResponseDataObj.HourlyData>() {
            @Override
            public void onSuccess(int code, ResponseDataObj.HourlyData result) {
                if (result != null) {
                    Log.i(TAG, "result message : " + result.getResult().getMessage());
                    Weather weather = new Weather();

                    if (result.getWeather() != null && result.getWeather().getHourly() != null && result.getWeather().getHourly().size() > 0) {
                        for (int i = 0; i < result.getWeather().getHourly().size(); i++) {
                            Log.i(TAG, "Precipitation SinceOntime : " + result.getWeather().getHourly().get(i).getPrecipitation().getSinceOntime());
                            Log.i(TAG, "Sky Name : " + result.getWeather().getHourly().get(i).getSky().getName());
                            Log.i(TAG, "Temperature tc : " + result.getWeather().getHourly().get(i).getTemperature().getTc());
                            Log.i(TAG, "Wind Wdir : " + result.getWeather().getHourly().get(i).getWind().getWdir());
                            Log.i(TAG, "Grid City : " + result.getWeather().getHourly().get(i).getGrid().getCity());

                            weather.setCity(result.getWeather().getHourly().get(i).getGrid().getCity());
                            weather.setCounty(result.getWeather().getHourly().get(i).getGrid().getCounty());
                            weather.setLatitude(result.getWeather().getHourly().get(i).getGrid().getLatitude());
                            weather.setLongitude(result.getWeather().getHourly().get(i).getGrid().getLongitude());
                            weather.setVillage(result.getWeather().getHourly().get(i).getGrid().getVillage());
                        }
                    }

                    WeatherDao.getInstance(mActivity).createOrUpdateWeather(weather);

                    List<Weather> weathers =  WeatherDao.getInstance(mActivity).getWeather();
                    for (Weather data : weathers) {
                        Log.i(TAG, "getIdx : " + data.getIdx());
                        Log.i(TAG, "getCity : " + data.getCity());
                        Log.i(TAG, "getCounty : " + data.getCounty());
                        Log.i(TAG, "getLatitude : " + data.getLatitude());
                        Log.i(TAG, "getLongitude : " + data.getLongitude());
                        Log.i(TAG, "getVillage : " + data.getVillage());
                    }

                    weathers.get(0).setCity("인천");
                    WeatherDao.getInstance(mActivity).createOrUpdateWeather(weathers.get(0));

                    for (Weather data : weathers) {
                        Log.i(TAG, "getIdx : " + data.getIdx());
                        Log.i(TAG, "getCity : " + data.getCity());
                        Log.i(TAG, "getCounty : " + data.getCounty());
                        Log.i(TAG, "getLatitude : " + data.getLatitude());
                        Log.i(TAG, "getLongitude : " + data.getLongitude());
                        Log.i(TAG, "getVillage : " + data.getVillage());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    /*******************************************************************************
     * Private method.
     *******************************************************************************/
    /**
     * 현재 모드에 대한 State Class  반환.
     * @param gameMode
     * @return
     */
    private IGameModeState getCurrentModeState(SudokuGenerator.eGameMode gameMode) {
        switch (gameMode.getMode()) {
            case R.string.sudoku_game_mode_1:
                return new GameModeEasyState();

            case R.string.sudoku_game_mode_2:
                return new GmaeModeNormalState();

            case R.string.sudoku_game_mode_3:
                return new GameModeHardState();

            default:
                return new GameModeEasyState();
        }
    }

    /**
     * 게임 모드를 변경을 View에 알린다.
     *
     * @param gameMode
     */
    private void updateGameMode(SudokuGenerator.eGameMode gameMode) {
        Log.d(TAG, "updateGameMode()");
        mView.updateGameModeView(gameMode.getMode());
    }

}
