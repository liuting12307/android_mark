package com.liuting.jetpackdemp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liuting.jetpackdemp.databinding.ActivityMainBinding;
import com.liuting.jetpackdemp.observer.MyObserver;
import com.liuting.jetpackdemp.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mMainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //可观察生命周期
        getLifecycle().addObserver(new MyObserver());
        mMainActivityViewModel= new ViewModelProvider(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("lt",s);
            }
        });
        viewDataBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityViewModel.updateName();
            }
        });
    }
}
