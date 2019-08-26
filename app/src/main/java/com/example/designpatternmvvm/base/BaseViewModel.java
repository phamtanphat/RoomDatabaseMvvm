package com.example.designpatternmvvm.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.designpatternmvvm.event.Loadingevent;

public class BaseViewModel extends ViewModel implements LifecycleObserver {

    private Loadingevent mLoading;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onViewModelCreate(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onViewModelDestroy(){
        mLoading = null;
    }

    public void setLoading(Loadingevent event) {
        this.mLoading = event;
    }
    protected void showProgress() {
        if (mLoading != null)
            mLoading.showProgress();
    }

    protected void hideProgress() {
        if (mLoading != null)
            mLoading.hideProgress();
    }
}
