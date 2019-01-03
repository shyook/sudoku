package com.example.jumping_i.sudoku_original.base;

import android.view.View;

public abstract class BasePresenter<T extends IBaseView> {
    public abstract void attachView(T view);
    public abstract void detachView();
}
