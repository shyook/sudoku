package com.example.jumping_i.sudoku_original.views.history;

public interface IRequestSet<T> {
    // 요청 ID를 반환한다.
    RequestCommandID.eRequestID getRequestID();
    // Redo 요청 여부를 반환 한다.
    boolean isRequestRedo();
    // Redo 요청시 셋팅.
    void setRequestRedo(boolean isRedo);

}
