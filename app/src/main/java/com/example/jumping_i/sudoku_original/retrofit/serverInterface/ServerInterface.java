package com.example.jumping_i.sudoku_original.retrofit.serverInterface;

import android.content.Context;
import android.text.TextUtils;

import com.example.jumping_i.sudoku_original.retrofit.IResultListener;
import com.example.jumping_i.sudoku_original.retrofit.retrofit.RetrofitManager;

import org.json.JSONObject;

/**
 * UI 에서 요청된 값을 Retrofit Manager로 전송 한다.
 */
public class ServerInterface extends BaseServerInterface {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static ServerInterface mInstance;
    private static Context mContext;

    /*******************************************************************************
     * Init.
     *******************************************************************************/
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

    /*******************************************************************************
     * Server I/F API.
     *******************************************************************************/
    /**
     * 시간별 날씨 정보를 요청한다.
     * @param version api 버전
     * @param strLat 위도
     * @param strLon 경도
     * @param listener 응답받을 listener
     */
    public void requestWeatherHourly(int version, String strLat, String strLon, IResultListener listener) {
        // 요청을 위한 데이터를 만든다.
        JSONObject obj = new JSONObject();
        try {
            obj.put(IParams.PARAM_VERSION, version == 0 ? 2 : version);
            obj.put(IParams.PARAM_LAT, TextUtils.isEmpty(strLat) ? "36.1234" : strLat);
            obj.put(IParams.PARAM_LON, TextUtils.isEmpty(strLon) ? "127.1234" : strLon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retrofit을 통해 요청 한다.
        RetrofitManager.getInstance(mContext).request(RequestApiType.REQUEST_WEATHER_HOURLY, obj, listener);
    }
}
