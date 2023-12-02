package com.example.finunsize.presentation.activity;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

import java.io.IOException;

import persistence.models.CompanyModel;
import persistence.models.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cadastro4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro4);
    }

    public void OpenCadastroPerfil(View view) {
        String email = getEditTextValue(R.id.email);
        String password = getEditTextValue(R.id.password);
        String repassword = getEditTextValue(R.id.repassword);

        // Verificar se as senhas coincidem
        if (!password.equals(repassword)) {
            Toast.makeText(this, "As senhas não coincidem. Por favor, verifique.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar se todos os campos estão preenchidos
        if (email.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Adicione validação de e-mail
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "E-mail inválido. Por favor, insira um e-mail válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        UserModel userModel = new UserModel(null, null, null, password, email,0, 0, null, null, null);

        sendCompanyData(userModel);
    }

    private String getEditTextValue(int editTextId) {
        EditText editText = findViewById(editTextId);
        return editText.getText().toString();
    }


    private void sendCompanyData(UserModel userModel) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finunsize.onrender.com/") // Substitua pela base URL da sua API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Faça a chamada para o método da API
        Call<Void> call = apiService.cadastrarUsuário(userModel);
        Log.d("Cadastro4", "URL: " + call.request().url());

        // Faça a solicitação assíncrona
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Sucesso na requisição
                    Toast.makeText(Cadastro4.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Cadastro4.this, CadastroPerfil.class);
                    startActivity(intent);
                } else {
                    if (response.code() == 404) {
                        try {
                            String errorBody = response.errorBody().string();
                            Log.e("Cadastro4", "Erro 404 - Corpo da Resposta: " + errorBody);
                            Toast.makeText(Cadastro4.this, "Recurso não encontrado. Verifique a rota no servidor.", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // Erro na requisição, código diferente de 404
                        Toast.makeText(Cadastro4.this, "Erro na requisição, código: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Falha na requisição
                t.printStackTrace(); // Imprime o stack trace da exceção
                Log.e("Cadastro2", "Falha na requisição", t);
                Toast.makeText(Cadastro4.this, "Falha na requisição", Toast.LENGTH_SHORT).show();

                // Exibe a mensagem de erro específica para o usuário
                Toast.makeText(Cadastro4.this, "Erro ao Cadastrar o usuário", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }

    public void OpenCadastro3 (View view) {
        MainActivity.redirect(this, Cadastro3.class);
    }
}
