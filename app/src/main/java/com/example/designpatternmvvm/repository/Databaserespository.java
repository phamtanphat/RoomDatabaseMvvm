package com.example.designpatternmvvm.repository;

import android.app.Application;
import android.util.Log;

import com.example.designpatternmvvm.db.SinhvienDatabase;
import com.example.designpatternmvvm.db.dao.SinhvienDao;
import com.example.designpatternmvvm.db.entity.Sinhvienentity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class Databaserespository {
    private SinhvienDao sinhvienDao;


    public Databaserespository(Application application) {
        SinhvienDatabase sinhvienDatabase = SinhvienDatabase.getInstance(application);
        sinhvienDao = sinhvienDatabase.sinhvienDao();

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

    public Flowable<List<Sinhvienentity>> getAllsinhvien() {
        return sinhvienDao.getAllsinhvien();
    }
}
