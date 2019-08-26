package com.example.designpatternmvvm.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Sinhvien")
public class Sinhvienentity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String ten;
    private String diachi;
    private byte[] hinhanh;

    public Sinhvienentity(String ten, String diachi, byte[] hinhanh) {
        this.ten = ten;
        this.diachi = diachi;
        this.hinhanh = hinhanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }
}
