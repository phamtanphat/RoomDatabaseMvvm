package com.example.designpatternmvvm.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.designpatternmvvm.base.BaseViewModel;
import com.example.designpatternmvvm.db.entity.Sinhvienentity;

import com.example.designpatternmvvm.event.Loadingevent;
import com.example.designpatternmvvm.repository.Databaserespository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Mainviewmodel extends BaseViewModel {

    private MutableLiveData<List<Sinhvienentity>> mSinhvien = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Databaserespository databaserespository;


    public Mainviewmodel() {

    }

    public Mainviewmodel(Loadingevent loadingevent , Application application) {
        setLoading(loadingevent);
        databaserespository = new Databaserespository(application);

    }



    public void selectAllsinhvien() {
        compositeDisposable.add(
                databaserespository.getAllsinhvien().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(sinhvienentities -> mSinhvien.setValue(sinhvienentities)));
    }
    public void insertSinhvien(Sinhvienentity sinhvienentity){
        compositeDisposable.add(databaserespository.insert(sinhvienentity).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> Log.d("BBB","Ok"))
        );
    }


    public LiveData<List<Sinhvienentity>> getListSinhvien() {
        return mSinhvien;
    }

}
