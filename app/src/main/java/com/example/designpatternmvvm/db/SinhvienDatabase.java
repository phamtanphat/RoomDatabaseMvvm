package com.example.designpatternmvvm.db;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.designpatternmvvm.R;
import com.example.designpatternmvvm.db.dao.SinhvienDao;
import com.example.designpatternmvvm.db.entity.Sinhvienentity;


@Database(entities = {Sinhvienentity.class} , version = 1)
public abstract class SinhvienDatabase extends RoomDatabase {

    private static SinhvienDatabase instance;

    public abstract SinhvienDao sinhvienDao();

    public static synchronized SinhvienDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        SinhvienDatabase.class,
                        "sinhvien_database")
                        .fallbackToDestructiveMigration()
                        .build();
        }
        return instance;
    }


}
