package com.example.jumping_i.sudoku_original.retrofit;

/**
 * 응답받을 Listener.
 *
 * @param <T>
 */
public interface IResultListener<T> {
    public void onSuccess(int code, T result);

    public void onFailure(Throwable t);
}
