package com.example.finunsize.presentation.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

public class Login extends AppCompatActivity {

    EditText email, password;
    SharedPreferences preferences;
    private static final String SHARED_MAIN = "Kmain";
    private static final String SHARED_NAME = "Kname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        get_resources();
    }

    public void get_resources() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void OpenHome(View view) {

        MainActivity.redirect(this, MainActivity.class);

    }

    public void OpenCadastro (View view) {
        MainActivity.redirect(this, Cadastro.class);
    }

    public void RedefinaSenha (View view) {
        MainActivity.redirect(this, Cadastro4.class);
    }

}