package com.example.designpatternmvvm.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.designpatternmvvm.base.BaseViewModel;
import com.example.designpatternmvvm.db.entity.Sinhvienentity;

import com.example.designpatternmvvm.event.Loadingevent;
import com.example.designpatternmvvm.repository.Databaserespository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListDataViewModel extends BaseViewModel {

    public MutableLiveData<List<Sinhvienentity>> mSinhvien = new MutableLiveData<>();
    public MutableLiveData<String> mInsert = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();
    private Databaserespository mdatabaserespository;


    public ListDataViewModel() {

    }

    public ListDataViewModel(Loadingevent loadingevent, Databaserespository databaserespository) {
        setLoading(loadingevent);
        this.mdatabaserespository = databaserespository;

    }


    @SuppressLint("CheckResult")
    public void getSinhvien() {
        showProgress();
        mdatabaserespository.getAllsinhvien()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> notify())
                .subscribe(sinhvienentities -> {
                    if (sinhvienentities != null)
                        mSinhvien.setValue(sinhvienentities);
                    Log.d("BBB",mSinhvien.getValue().size() + " rx");
                    hideProgress();
                }, throwable -> {
                    error.setValue(throwable.getMessage());
                    hideProgress();
                });

    }

    @SuppressLint("CheckResult")
    public void insertSinhvien(Sinhvienentity sinhvienentity) {
        showProgress();
        mdatabaserespository.insert(sinhvienentity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> mInsert.setValue("Thành công"),
                        throwable -> error.setValue(throwable.getMessage()));
    }



}
