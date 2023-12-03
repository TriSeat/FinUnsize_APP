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

import integration.AuthRequest;
import integration.AuthResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Inicialize o ApiService (certifique-se de ter retrofit configurado)
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public void OpenHome(View view) {
        String email = getEditTextValue(R.id.email);
        String password = getEditTextValue(R.id.password);

        // Verifique se os campos de e-mail e senha estão preenchidos
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Adicione validação de e-mail
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "E-mail inválido. Por favor, insira um e-mail válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Faça a chamada para autenticar o usuário
        authenticateUser(email, password);
    }

    private String getEditTextValue(int editTextId) {
        EditText editText = findViewById(editTextId);
        return editText.getText().toString();
    }

    private void authenticateUser(String email, String password) {
        // Crie a instância do modelo de autenticação
        AuthRequest authRequest = new AuthRequest(email, password);

        // Faça a chamada para autenticar o usuário
        Call<AuthResponse> call = apiService.authenticateUser(authRequest);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                handleAuthenticationResponse(response);
            }
            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                handleAuthenticationFailure(t);
            }
        });
    }

    private void handleAuthenticationResponse(Response<AuthResponse> response) {
        if (response.isSuccessful()) {
            AuthResponse authResponse = response.body();
            String token = authResponse.getToken();
            saveToken(token);

            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
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

    public void OpenCadastro (View view) {
        MainActivity.redirect(this, Cadastro.class);
    }

    public void RedefinaSenha (View view) {
        MainActivity.redirect(this, Cadastro3.class);
    }

}