package com.example.finunsize.presentation.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
    }

    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }

    public void OpenCadastro2 (View view) {
        MainActivity.redirect(this, Cadastro2.class);
    }

    public void OpenErroCadastro (View view) {
        MainActivity.redirect(this, ErroCadastro.class);
    }
}
