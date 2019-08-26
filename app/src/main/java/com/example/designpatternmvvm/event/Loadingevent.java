package com.example.designpatternmvvm.event;

import android.view.View;
import android.widget.ProgressBar;

public class Loadingevent {
    private ProgressBar mProgressBar;

    public Loadingevent() {
    }

    public Loadingevent(ProgressBar mProgressBar) {
        this.mProgressBar = mProgressBar;
    }
    public void showProgress(){
        if (mProgressBar != null){
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }
    public void hideProgress(){
        if (mProgressBar != null){
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
