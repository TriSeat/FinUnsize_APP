package com.example.finunsize.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.finunsize.R;

public class Cadastro4 extends AppCompatActivity {

    EditText email, password, repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro4);
        get_resources();
    }

    public void get_resources() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);

        
    }

    public void OpenCadastroPerfil (View view) {
        MainActivity.redirect(this, MainActivity.class);
    }
    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }
    public void OpenCadastro3 (View view) {
        MainActivity.redirect(this, Cadastro3.class);
    }
}