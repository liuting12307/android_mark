package com.liuting.mvvmdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.database.DatabaseUtils;
import android.os.Bundle;

import com.liuting.mvvmdemo.databinding.ActivityMainBinding;
import com.liuting.mvvmdemo.vm.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        new LoginViewModel(viewDataBinding);
    }
}
