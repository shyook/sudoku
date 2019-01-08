package com.example.jumping_i.sudoku_original.retrofit.serverInterface;

import android.content.Context;
import android.text.TextUtils;

import com.example.jumping_i.sudoku_original.retrofit.IResultListener;
import com.example.jumping_i.sudoku_original.retrofit.RequestApiType;
import com.example.jumping_i.sudoku_original.retrofit.RetrofitManager;

import org.json.JSONObject;

/**
 * UI 에서 요청된 값을 Retrofit Manager로 전송 한다.
 */
public class ServerInterface extends BaseServerInterface {
    private static ServerInterface mInstance;
    private static Context mContext;

    private static class SingletonHolder {
        private static ServerInterface Instance = new ServerInterface(mContext);
    }

    public static ServerInterface getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return ServerInterface.SingletonHolder.Instance;
    }

    private ServerInterface(Context context) {
        mContext = context;
    }

    public void requestWeatherHourly(int version, String strLat, String strLon, IResultListener listener) {
        JSONObject obj = new JSONObject();
        try {
            obj.put(IParams.PARAM_VERSION, version == 0 ? 2 : version);
            obj.put(IParams.PARAM_LAT, TextUtils.isEmpty(strLat) ? "36.1234" : strLat);
            obj.put(IParams.PARAM_LON, TextUtils.isEmpty(strLon) ? "127.1234" : strLon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RetrofitManager.getInstance(mContext).request(RequestApiType.REQUEST_WEATHER_HOURLY, obj, listener);
    }
}
