package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import integration.AuthRequest;
import integration.AuthResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void OpenHome(View view) {
        String login = getEditTextValue(R.id.login);
        String password = getEditTextValue(R.id.password);

        // Verifique se os campos de e-mail e senha estão preenchidos
        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password)) {
            showToast("Preencha todos os campos");
            return;
        }

        // Faça a chamada para autenticar o usuário
        authenticateUser(login, password);
    }

    private String getEditTextValue(int editTextId) {
        EditText editText = findViewById(editTextId);
        return editText.getText().toString();
    }

    private void authenticateUser(String login, String password) {
        // Adicione o interceptor de logging ao cliente Retrofit
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finunsize.onrender.com/") // Substitua pela base URL da sua API
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor) // Adiciona o interceptor de logging
                        .build())
                .build();

        // Inicialize o ApiService (certifique-se de ter retrofit configurado)
        ApiService apiService = retrofit.create(ApiService.class);

        // Crie a instância do modelo de autenticação
        AuthRequest authRequest = new AuthRequest(login, password);

        // Faça a chamada para autenticar o usuário
        Call<ResponseBody> call = apiService.authenticateUser(authRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Adicione o log do corpo da resposta aqui
                Log.d("Login", "Resposta do Servidor: " + response.body());

                handleAuthenticationResponse(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                handleAuthenticationFailure(t);
            }
        });
    }

    private void handleAuthenticationResponse(Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            try {
                // Convert the response body to a string
                String responseBody = response.body().string();

                // Assuming the token is directly returned as a string
                AuthResponse authResponse = new AuthResponse(responseBody);

                String token = authResponse.getToken();
                saveToken(token);

                showToast("Autenticação bem-sucedida");

                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (IOException e) {
                e.printStackTrace();
                showToast("Falha na conversão da resposta");
            }
        } else {
            showToast("Credenciais inválidas");
        }
    }

    private void handleAuthenticationFailure(Throwable t) {
        Log.e("Login", "Falha na requisição", t);
        showToast("Falha na requisição");
    }

    private void saveToken(String token) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("token", token).apply();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void OpenCadastro(View view) {
        MainActivity.redirect(this, Cadastro.class);
    }

    public void RedefinaSenha(View view) {
        MainActivity.redirect(this, Cadastro3.class);
    }
}
