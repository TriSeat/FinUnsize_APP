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

    Database DB;

    SharedPreferences preferences;

    private static final String SHARED_MAIN = "Kmain";

    private static final String SHARED_NAME = "Kname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        get_resources();
        DB = new Database(this);
    }

    public void get_resources() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void OpenHome(View view) {
        String emaill = email.getText().toString();
        String pass = password.getText().toString();

        preferences = getSharedPreferences(SHARED_MAIN , MODE_PRIVATE);
        String check = preferences.getString(SHARED_NAME, null);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SHARED_NAME, emaill);
        editor.apply();

        if (check != null) {

            if(TextUtils.isEmpty(emaill) || TextUtils.isEmpty(pass)) {
                Toast.makeText(Login.this, "Os campos não podem ficar nulos", Toast.LENGTH_SHORT).show();
            }
            else {
                Boolean viewMoast = DB.viewAll(emaill, pass);
                if(viewMoast == true) {
                    Toast.makeText(Login.this, "Login efetuado", Toast.LENGTH_SHORT).show();
                    MainActivity.redirect(this, MainActivity.class);
                    finish();
                }
                else {
                    Toast.makeText(Login.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else {
            Toast.makeText(Login.this, "O campo email está nulo", Toast.LENGTH_SHORT).show();
        }
    }

    public void OpenCadastro (View view) {
        MainActivity.redirect(this, Cadastro.class);
    }

    public void RedefinaSenha (View view) {
        MainActivity.redirect(this, Cadastro4.class);
    }

}