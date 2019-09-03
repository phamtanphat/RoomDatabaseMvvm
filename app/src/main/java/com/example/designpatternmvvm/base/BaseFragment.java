package com.example.designpatternmvvm.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {
    public VM mViewModel;
    private View mRootview;
    public abstract int getLayoutId();
    public abstract void initView(View view);
    public abstract VM getViewModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootview = inflater.inflate(getLayoutId(),container,false);
        initView(mRootview);

        return mRootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = getViewModel();
        getLifecycle().addObserver(mViewModel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.onViewModelDestroy();
    }
}
