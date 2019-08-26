package com.example.designpatternmvvm.view.activity;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.designpatternmvvm.R;
import com.example.designpatternmvvm.base.BaseActivity;
import com.example.designpatternmvvm.db.entity.Sinhvienentity;
import com.example.designpatternmvvm.event.Loadingevent;
import com.example.designpatternmvvm.view.adapter.ViewpagerAdapter;
import com.example.designpatternmvvm.view.fragment.AddFragment;
import com.example.designpatternmvvm.view.fragment.EditFragment;
import com.example.designpatternmvvm.view.fragment.HistoryFragment;
import com.example.designpatternmvvm.view.fragment.ListDataFragment;
import com.example.designpatternmvvm.viewmodel.Mainviewmodel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends BaseActivity<Mainviewmodel> {

    ViewPager mviewPager;
    BottomNavigationView mbottomNavigationView;
    ProgressBar progressBar;
    ViewpagerAdapter viewpagerAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mbottomNavigationView = findViewById(R.id.bottomnavi);
        mviewPager = findViewById(R.id.viewpager);
        progressBar = findViewById(R.id.progressbar);
    }

    @Override
    public Mainviewmodel getViewModel() {
        return new Mainviewmodel(new Loadingevent(progressBar) , getApplication());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViewPager();

        getViewModel().getListSinhvien().observe(this , sinhvienentities -> {
            Log.d("BBB",sinhvienentities.size() + "");
        });
        getViewModel().selectAllsinhvien();


    }
    private void setUpViewPager(){
        viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewpagerAdapter.addFragment(new ListDataFragment());
        viewpagerAdapter.addFragment(new AddFragment());
        viewpagerAdapter.addFragment(new EditFragment());
        viewpagerAdapter.addFragment(new HistoryFragment());
        mviewPager.setAdapter(viewpagerAdapter);

        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position >-1){
                    mbottomNavigationView.getMenu().getItem(position).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mbottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item_home:
                    mviewPager.setCurrentItem(0);
                    break;
                case R.id.item_add:
                    mviewPager.setCurrentItem(1);
                    break;
                case R.id.item_edit:
                    mviewPager.setCurrentItem(2);
                    break;
                case R.id.item_history:
                    mviewPager.setCurrentItem(3);
                    break;
            }
            return false;
        });
    }
}
