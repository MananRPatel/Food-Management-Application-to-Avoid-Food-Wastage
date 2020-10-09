package com.mananhirak.annadata.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mananhirak.annadata.R;

import java.util.Objects;

public class UserHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}