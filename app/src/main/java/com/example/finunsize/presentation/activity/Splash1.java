package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

public class Splash1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash1);
    }

    public void goToSplash2(View v) {
        MainActivity.redirect(this, Splash2.class);
    }

    public void goToSplash3(View v) {
        MainActivity.redirect(this, Splash3.class);
    }

}
