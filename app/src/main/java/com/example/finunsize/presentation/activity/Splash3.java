package com.example.finunsize.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.finunsize.R;

public class Splash3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash3);
    }

    public void goToCadastro(View v) {
        MainActivity.redirect(this, Cadastro.class);
    }

    public void goToSplash1(View v) {
        MainActivity.redirect(this, Splash1.class);
    }
    public void goToSplash2(View v) {
        MainActivity.redirect(this, Splash2.class);
    }

    public void goToSplash3(View v) {
        MainActivity.redirect(this, Splash3.class);
    }
}