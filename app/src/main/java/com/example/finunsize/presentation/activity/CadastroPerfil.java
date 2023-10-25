package com.example.finunsize.presentation.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

public class CadastroPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_perfil);
    }

    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }
    public void OpenCadastro4 (View view) {
        MainActivity.redirect(this, Cadastro4.class);
    }
    public void OpenHome (View view) {
        MainActivity.redirect(this, MainActivity.class);
    }


}
