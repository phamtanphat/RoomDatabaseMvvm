package com.example.designpatternmvvm.repository;

import android.app.Application;
import android.util.Log;

import com.example.designpatternmvvm.db.SinhvienDatabase;
import com.example.designpatternmvvm.db.dao.SinhvienDao;
import com.example.designpatternmvvm.db.entity.Sinhvienentity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class Databaserespository {
    private static Databaserespository instance = null;
    private SinhvienDao sinhvienDao;


    private Databaserespository(Application application) {
        SinhvienDatabase sinhvienDatabase = SinhvienDatabase.getInstance(application);
        sinhvienDao = sinhvienDatabase.sinhvienDao();

    }
    public  static Databaserespository getInstance(Application application){
        if (instance == null){
            instance = new Databaserespository(application);
        }
        return instance;
    }


    public Completable insert(Sinhvienentity sinhvienentity) {
        return sinhvienDao.insert(sinhvienentity);
    }

    public Completable update(Sinhvienentity sinhvienentity) {
        return sinhvienDao.update(sinhvienentity);
    }

    public Completable delete(Sinhvienentity sinhvienentity) {
        return sinhvienDao.delete(sinhvienentity);
    }

    public Completable deleteAll() {
        return sinhvienDao.deleteAllSinhvien();
    }

    public Observable<List<Sinhvienentity>> getAllsinhvien() {
        return sinhvienDao.getAllsinhvien();
    }
}
