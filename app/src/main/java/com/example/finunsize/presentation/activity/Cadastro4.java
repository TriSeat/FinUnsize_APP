package com.example.finunsize.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finunsize.R;

public class Cadastro4 extends AppCompatActivity {

    EditText email, password, repassword;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro4);
        get_resources();
        db = new Database(this);
    }

    public void get_resources() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
    }

    public void OpenCadastroPerfil (View view) {

        MainActivity.redirect(this, Cadastro.class);

        String emaill = email.getText().toString();
        String word = password.getText().toString();
        String repass = repassword.getText().toString();

        if(TextUtils.isEmpty(emaill) || TextUtils.isEmpty(word) || TextUtils.isEmpty(repass)) {
            Toast.makeText(Cadastro4.this, "Todos os campos devem ser registrados", Toast.LENGTH_SHORT).show();
        }
        else {
            if(word.equals(repass)) {
                Boolean viewUser = db.viewName(emaill);
                if(viewUser == false) {
                    Boolean insert = db.insertDb(emaill, word);
                    if (insert == true) {
                        Toast.makeText(Cadastro4.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                        MainActivity.redirect(this, Cadastro.class);
                    }
                    else {
                        Toast.makeText(Cadastro4.this, "O cadastro falhou", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Cadastro4.this, "O email já está cadastrado", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(Cadastro4.this, "A senha não corresponde", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }
    public void OpenCadastro3 (View view) {
        MainActivity.redirect(this, Cadastro3.class);
    }
}