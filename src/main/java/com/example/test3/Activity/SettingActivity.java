package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test3.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}