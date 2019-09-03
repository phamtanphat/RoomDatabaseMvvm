package com.example.designpatternmvvm.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;

import com.example.designpatternmvvm.R;
import com.example.designpatternmvvm.base.BaseFragment;
import com.example.designpatternmvvm.db.entity.Sinhvienentity;
import com.example.designpatternmvvm.event.Loadingevent;
import com.example.designpatternmvvm.repository.Databaserespository;
import com.example.designpatternmvvm.view.adapter.SinhvienAdapter;
import com.example.designpatternmvvm.viewmodel.ListDataViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListDataFragment extends BaseFragment<ListDataViewModel> {

    SearchView msearchView;
    RecyclerView mrecyclerView;
    SinhvienAdapter msinhvienAdapter;
    ArrayList<Sinhvienentity> mSinhviens = new ArrayList<>();
    ProgressBar progressBar;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_data;
    }


    @Override
    public void initView(View view) {
        msearchView = view.findViewById(R.id.searchview);
        progressBar = view.findViewById(R.id.progressbarDanhsach);
        mrecyclerView = view.findViewById(R.id.recyclerviewDanhsach);
        msinhvienAdapter = new SinhvienAdapter(mSinhviens);
        mrecyclerView.setAdapter(msinhvienAdapter);
    }

    @Override
    public ListDataViewModel getViewModel() {
        return new ListDataViewModel(new Loadingevent(progressBar), Databaserespository.getInstance(getActivity().getApplication()));
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.mSinhvien.observe(ListDataFragment.this, sinhvienentities -> {
            if (sinhvienentities != null){
                mSinhviens = (ArrayList<Sinhvienentity>) sinhvienentities;
                msinhvienAdapter.addAll(sinhvienentities);
            }

        });

        msearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")){
                    msinhvienAdapter.addAll(mSinhviens);
                }else {
                    msinhvienAdapter.filter(newText , mSinhviens);

                }
                return true;
            }
        });
        mViewModel.getSinhvien();

    }
}
