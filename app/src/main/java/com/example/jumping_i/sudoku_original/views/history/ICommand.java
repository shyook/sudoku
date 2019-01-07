package com.example.jumping_i.sudoku_original.views.history;

public interface ICommand<T extends AbstractResponseSet> {
    // command 실행.
    T execute(IRequestSet<?> request);
    // history back
    T undo(IRequestSet<?> request);
    // history forward
    boolean redo(ICommand cmd);
    // 결과를 전달
    void response(T result);
}
