package com.example.finunsize.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

import integration.AuthResponse;
import persistence.models.UserModel;
import request.Connection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                .baseUrl("URL_DA_SUA_API")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Connection service = retrofit.create(Connection.class);
        UserModel newUser = new UserModel(email, password);

        Call<AuthResponse> call = service.registerUser(newUser);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    if (authResponse != null) {
                        String token = authResponse.getToken();
                        saveToken(token);
                        Intent intent = new Intent(Cadastro4.this, Cadastro4.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Cadastro4.this, "Resposta inválida da API", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Cadastro4.this, "Falha ao registrar usuário", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(Cadastro4.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToken(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("NomeDaSuaPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }
}