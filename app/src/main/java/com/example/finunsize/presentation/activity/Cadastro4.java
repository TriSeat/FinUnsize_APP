package com.example.finunsize.presentation.activity;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

import persistence.models.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cadastro4 extends AppCompatActivity {

    private EditText email, password, repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro4);
        getResources();
    }

    public Resources getResources() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        return null;
    }

    public void OpenCadastroPerfil(View view) {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userRePassword = repassword.getText().toString();

        if (userPassword.equals(userRePassword)) {
            registerUser(userEmail, userPassword);
        } else {
            Toast.makeText(Cadastro4.this, "As senhas não correspondem", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser(String email, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sua_api_aqui.com/") // Substitua pela base URL da sua API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        UserModel newUser = new UserModel(email, password); // Verifique como construir UserModel

        Call<Void> call = apiService.registerUser(newUser);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Cadastro4.this, "Usuário registrado com sucesso", Toast.LENGTH_SHORT).show();
                    // Aqui você pode redirecionar para a próxima tela após o registro
                    Intent intent = new Intent(Cadastro4.this, CadastroPerfil.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Cadastro4.this, "Falha ao registrar usuário", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Cadastro4.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
