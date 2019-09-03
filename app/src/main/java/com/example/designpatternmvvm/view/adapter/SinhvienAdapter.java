package com.example.designpatternmvvm.view.adapter;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.designpatternmvvm.R;
import com.example.designpatternmvvm.db.entity.Sinhvienentity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;

public class SinhvienAdapter extends RecyclerView.Adapter<SinhvienAdapter.Holder> {

    ArrayList<Sinhvienentity> mSinhvien;


    public SinhvienAdapter(ArrayList<Sinhvienentity> mSinhvien) {
        if (mSinhvien != null){
            this.mSinhvien = new ArrayList<>(mSinhvien);
        }else{
            this.mSinhvien = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_sinhvien, parent, false);
        return new Holder(view);
    }

    public void addSinhvien(Sinhvienentity sinhvien) {
        mSinhvien.add(sinhvien);
        notifyDataSetChanged();
    }

    public <T> void addAll(List<T> array) {
        if (mSinhvien != null) {
            mSinhvien.clear();
            mSinhvien.addAll((Collection<? extends Sinhvienentity>) array);
            notifyDataSetChanged();
        }
    }

    @SuppressLint("CheckResult")
    public void filter(String text , ArrayList<Sinhvienentity> sinhvienentities) {
        Log.d("BBB",text);
        ArrayList<Sinhvienentity> mResult = new ArrayList<>();
        if ( sinhvienentities != null){
            for (Sinhvienentity sinhvienentity : sinhvienentities) {
                if (sinhvienentity.getTen().toLowerCase().equals(text.toLowerCase())) {
                    mResult.add(sinhvienentity);
                }
            }
            this.addAll(mResult);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Sinhvienentity sinhvien = mSinhvien.get(position);

        holder.txtDiachi.setText(String.format("Địa chỉ : %s", sinhvien.getDiachi()));
        holder.txtTen.setText(String.format("Tên : %s", sinhvien.getTen()));
        if (sinhvien.getHinhanh() != null) {
            holder.img.setImageBitmap(BitmapFactory.decodeByteArray(sinhvien.getHinhanh(), 0, sinhvien.getHinhanh().length));
        } else {
            holder.img.setImageResource(R.drawable.image_nophoto);
        }
    }

    @Override
    public int getItemCount() {
        return mSinhvien != null ? mSinhvien.size() : 0;
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtTen, txtDiachi;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageviewSinhvien);
            txtTen = itemView.findViewById(R.id.textviewTen);
            txtDiachi = itemView.findViewById(R.id.textviewDiachi);

        }
    }
}
