package com.example.jumping_i.sudoku_original.retrofit;

/**
 * 응답받을 Listener.
 *
 * @param <T>
 */
public interface IResultListener<T> {
    /** 성공시 응답 */
    void onSuccess(int code, T result);

    /** 실패시 응답 */
    void onFailure(Throwable t);
}
