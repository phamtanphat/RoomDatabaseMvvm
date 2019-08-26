package com.example.designpatternmvvm.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {

    public T mViewModel;
    public abstract int getLayoutId();
    public abstract void initView();
    public abstract T getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        mViewModel = getViewModel();
        mViewModel = (T) ViewModelProviders.of(this).get(mViewModel.getClass());
        mViewModel.onViewModelCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.onViewModelDestroy();
    }
}
