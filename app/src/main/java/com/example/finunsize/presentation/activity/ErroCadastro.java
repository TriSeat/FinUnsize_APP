package com.example.finunsize.presentation.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

public class ErroCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erro_cadastro);
    }

    public void OpenHomeNoLogin (View view) {
        MainActivity.redirect(this, MainActivityNoLogin.class);
    }

    public void OpenCadastro2 (View view) {
        MainActivity.redirect(this, Cadastro.class);
    }
}
