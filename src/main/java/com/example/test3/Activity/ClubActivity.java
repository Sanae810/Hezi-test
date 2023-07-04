package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test3.R;

public class ClubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}