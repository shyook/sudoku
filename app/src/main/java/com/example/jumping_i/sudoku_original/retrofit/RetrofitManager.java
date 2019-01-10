package com.example.jumping_i.sudoku_original.retrofit;

import android.content.Context;
import android.util.Log;

import com.example.jumping_i.sudoku_original.retrofit.data.ResponseDataObj;
import com.example.jumping_i.sudoku_original.retrofit.serverInterface.IParams;
import com.example.jumping_i.sudoku_original.retrofit.serverInterface.ServerInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager implements IBaseRetrofit {
    private static final String TAG = RetrofitManager.class.getSimpleName();

    private static Context mContext;
    private IRequestApi mRequestApi;

    private static class SingletonHolder {
        private static RetrofitManager Instance = new RetrofitManager(mContext);
    }

    public static RetrofitManager getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.Instance;
    }

    private RetrofitManager(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ServerInterface.getInstance(context).getBaseUrl())
                .build();

        mRequestApi = retrofit.create(IRequestApi.class);
    }

    @Override
    public void request(RequestApiType type, JSONObject param, final IResultListener responseCallback) {
        Log.d(TAG, "request : " + type.name());
        switch (type) {
            case REQUEST_WEATHER_HOURLY:
                Call<ResponseDataObj.HourlyData> reqHourly = null;
                try {
                    reqHourly = mRequestApi.getWeatherHourly(
                            param.getInt(IParams.PARAM_VERSION)
                            , param.getString(IParams.PARAM_LAT)
                            , param.getString(IParams.PARAM_LON)
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                reqHourly.enqueue(new Callback<ResponseDataObj.HourlyData>() {
                    @Override
                    public void onResponse(Call<ResponseDataObj.HourlyData> call, Response<ResponseDataObj.HourlyData> response) {
                        if (response.isSuccessful()) {
                            Log.i(TAG, "message : " + response.message());
                            Log.i(TAG, "getAlertYn : " + response.body().getCommon().getAlertYn());
                            Log.i(TAG, "result code : " + response.body().getResult().getCode());
                            Log.i(TAG, "hourly size : " + response.body().getWeather().getHourly().size());
                            responseCallback.onSuccess(response.code(), response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDataObj.HourlyData> call, Throwable t) {
                        responseCallback.onFailure(t);
                    }
                });
                break;

            default:
                break;
        }
    }

    @Override
    public void request(RequestApiType type, String url, Map<String, String> headers, JSONObject param , IResultListener responseCallback) {
        switch (type) {
            case REQUEST_WEATHER_HOURLY:
//                Call<ResponseDataObj> reqHourly = mRequestApi.getWeatherHourly(
//                        type.getUrl()
//                        ,
//                )
                break;

            default:
                break;
        }
    }

}
