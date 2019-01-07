package com.example.jumping_i.sudoku_original.views.history;

public interface ISuccessResponse<T> {
    // 성공에 대한 결과값을 반환한다.
    void onSuccess(T result);
}
