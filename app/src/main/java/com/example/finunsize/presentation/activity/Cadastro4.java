package com.example.finunsize.presentation.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finunsize.R;

import java.util.UUID;

public class Cadastro4 extends AppCompatActivity {

    EditText email, password, repassword;
    Button btncadastro;

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
        btncadastro = findViewById(R.id.btncadastro);
    }

    public void OpenCadastroPerfil() {
        String emaill = email.getText().toString();
        String senha = password.getText().toString();
        String resenha = repassword.getText().toString();

        MainActivity.redirect(this, CadastroPerfil.class);

        /* if (password.equals(resenha)) {
            String token = UUID.randomUUID().toString();

            sendTokenToApi(token);

            registerUser(emaill, senha);
        } else {

            Toast.makeText(Cadastro4.this, "As senhas não correspondem", Toast.LENGTH_SHORT).show();
        } */
    }

    private void sendTokenToApi(String token) {
    }

    private void registerUser(String email, String password) {
    }

    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }
    public void OpenCadastro3 (View view) {
        MainActivity.redirect(this, Cadastro3.class);
    }
}