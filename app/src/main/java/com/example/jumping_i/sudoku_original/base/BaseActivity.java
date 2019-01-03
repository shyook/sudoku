package com.example.jumping_i.sudoku_original.base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    protected abstract void init();

    @Override
    public Activity getActivity() {
        return this;
    }
}
