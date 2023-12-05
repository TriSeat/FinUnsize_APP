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
import com.example.finunsize.presentation.activity.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import integration.AuthRequest;
import integration.AuthResponse;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
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
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finunsize.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client) // Configurando o cliente com o interceptor
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Crie a instância do modelo de autenticação
        AuthRequest authRequest = new AuthRequest(login, password);

        // Faça a chamada para autenticar o usuário
        Call<ResponseBody> call = apiService.authenticateUser(authRequest);

        call.enqueue(new Callback<ResponseBody>() {


            public void onResponse(Call<ResponseBody> call, Response response) {
                // Adicione o log do corpo da resposta aqui
                Log.d("Login", "Resposta do Servidor: " + response.body());

                handleAuthenticationResponse(response);
            }

            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                handleAuthenticationFailure(t);
            }
        });
    }

    private void handleAuthenticationResponse(Response response) {
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

    private class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.d("Request", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            if ("post".equalsIgnoreCase(request.method())) {
                Log.d("Request", bodyToString(request));
            }

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.d("Response", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            String bodyString = response.body().string();
            Log.d("Response", bodyString);

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                    .build();
        }

        private String bodyToString(final Request request) {
            try {
                final Request copy = request.newBuilder().build();
                final okio.Buffer buffer = new okio.Buffer();
                if (copy.body() == null) return "";
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            } catch (final IOException e) {
                return "did not work";
            }
        }
    }
}
