package com.example.jumping_i.sudoku_original.db.dao;

import android.content.Context;
import android.util.Log;

import com.example.jumping_i.sudoku_original.db.WeatherDatabaseHelper;
import com.example.jumping_i.sudoku_original.db.data.Weather;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class WeatherDao {
    private static final String TAG = WeatherDatabaseHelper.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static Context mContext;

    /*******************************************************************************
     * Init.
     *******************************************************************************/
    private WeatherDao(Context context) {
    }

    private static class SingletonHolder {
        private static WeatherDao Instance = new WeatherDao(mContext);
    }

    public static WeatherDao getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return WeatherDao.SingletonHolder.Instance;
    }

    /*******************************************************************************
     * Operation.
     *******************************************************************************/
    /**
     * Dao를 획득 한다.
     *
     * @return
     */
    private Dao<Weather, Integer> getWeatherOperation() {
        return WeatherDatabaseHelper.getInstance(mContext).getWeatherDao(Weather.class);
    }

    /*******************************************************************************
     * CRUD Method.
     *******************************************************************************/
    /**
     * 모든 정보를 가져온다.
     *
     * @return
     */
    public List<Weather> getWeather() {
        try {
            return getWeatherOperation().queryForAll();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    /**
     * 특정 1개의 정보를 가져온다.
     *
     * @param nIdx
     * @return
     */
    public Weather getWeatherWithIdx(int nIdx) {
        try {
            return getWeatherOperation().queryForId(nIdx);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    /**
     * 정보를 DB에 생성 한다.
     * @param weather
     * @return
     */
    public int createWeather(Weather weather) {
        try {
            return getWeatherOperation().create(weather);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return 0;
    }

    /**
     * 정보를 Update 한다.
     * @param weather
     * @return
     */
    public int updateWeather(Weather weather) {
        try {
            return getWeatherOperation().update(weather);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return 0;
    }

    /**
     * 기본키를 검색하여 생성 및 업데이트 처리를 한다.
     *
     * @param weather
     * @return {@link Dao.CreateOrUpdateStatus}
     */
    public Dao.CreateOrUpdateStatus createOrUpdateWeather(Weather weather) {
        try {
            return getWeatherOperation().createOrUpdate(weather);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    /**
     * index로 정보를 삭제한다.
     *
     * @param nIdx
     * @return The number of rows updated in the database. This should be 1.
     */
    public int deleteLocationWithIdx(int nIdx) {
        try {
            return getWeatherOperation().deleteById(nIdx);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return 0;
    }
}
