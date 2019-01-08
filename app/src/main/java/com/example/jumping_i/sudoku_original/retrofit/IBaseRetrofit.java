package com.example.jumping_i.sudoku_original.retrofit;

import org.json.JSONObject;

import java.util.Map;

public interface IBaseRetrofit {
    /** Request Type 별 Interface 요청 */
    void request(RequestApiType type, JSONObject param, IResultListener responseCallback);

    /**  Request Type 별 Interface 요청 (상세 Url 정보와 Header 정보를 전달 받아 전송) */
    void request(RequestApiType type, String url, Map<String, String> headers, JSONObject param, IResultListener responseCallback);
}
