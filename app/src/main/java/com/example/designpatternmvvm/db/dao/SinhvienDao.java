package com.example.designpatternmvvm.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.designpatternmvvm.db.entity.Sinhvienentity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;


@Dao
public interface SinhvienDao {

    @Insert
    Completable insert(Sinhvienentity sinhvienentity);

    @Update
    Completable update(Sinhvienentity sinhvienentity);

    @Delete
    Completable delete(Sinhvienentity sinhvienentity);

    @Query("DELETE FROM Sinhvien")
    Completable deleteAllSinhvien();

    @Query("SELECT * FROM Sinhvien")
    Observable<List<Sinhvienentity>> getAllsinhvien();


}
