package com.example.jumping_i.sudoku_original.retrofit;

import com.example.jumping_i.sudoku_original.retrofit.data.ResponseDataObj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IRequestApi {
    // 시간별 날씨 정보 요청
    @Headers({
            "Accept: application/json"
            , "Content-Type: application/json; charset=UTF-8"
            , "appKey:15301a25-e582-4f95-9f76-0c04cb4cced4"
    })
    @GET("weather/current/hourly")
    Call<ResponseDataObj.HourlyData> getWeatherHourly(@Query("version") int version, @Query("lat") String lat, @Query("lon") String lon);
}
