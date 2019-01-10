package com.example.jumping_i.sudoku_original.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jumping_i.sudoku_original.db.data.Weather;
import com.example.jumping_i.sudoku_original.db.utils.DatabaseConstants;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherDatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = WeatherDatabaseHelper.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static Context mContext;


    /*******************************************************************************
     * Init.
     *******************************************************************************/
    private WeatherDatabaseHelper(Context context) {
        super(context, DatabaseConstants.WEATHER_DATABASE_NAME, null, DatabaseConstants.WEATHER_DATABASE_VERSION);
    }

    private static class SingletonHolder {
        private static WeatherDatabaseHelper Instance = new WeatherDatabaseHelper(mContext);
    }

    public static WeatherDatabaseHelper getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return WeatherDatabaseHelper.SingletonHolder.Instance;
    }

    /*******************************************************************************
     * Override Method.
     *******************************************************************************/
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            // DB 생성.
            TableUtils.createTable(connectionSource, Weather.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            List<String> allSql = new ArrayList<>();

            switch (oldVersion) {
                case 1:
                    // TODO 실제 upgrade가 필요한 상황에 맞게 수정하도록 한다.
            }

            for (String sql : allSql) {
                database.execSQL(sql);
            }
        } catch (android.database.SQLException e) {
            Log.e(TAG, "exception during onUpgrade: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        super.close();
        DaoManager.clearCache();
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    /**
     * DAO 반환.
     * @param clazz
     * @param <D>
     * @param <T>
     * @return
     */
    public <D extends Dao<T, ?>, T> D getWeatherDao(Class<T> clazz) {
        try {
            return getDao(clazz);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }
}
