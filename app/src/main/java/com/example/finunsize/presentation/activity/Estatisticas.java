package com.example.finunsize.presentation.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import persistence.models.ProjectionModel;
import request.Connection;

public class Estatisticas extends AppCompatActivity {

    private List<ProjectionModel> staticsList;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statics);

        token = getIntent().getStringExtra("token");

        staticsList = fetchStaticListFromApi(token);


    }

    public List<ProjectionModel> fetchStaticListFromApi(String token) {
        List<ProjectionModel> staticsList = new ArrayList<>();

        String apiUrl = "https://finunsize.onrender.com/projection/";

        try {
            String apiResponse = Connection.connectHttpWithHeader(apiUrl, token);

            if (apiResponse != null && isValidJsonArray(apiResponse)) {
                JSONArray jsonArray = new JSONArray(apiResponse);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String nome = jsonObject.getString("nome");

                    // Tratamento de datas
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate data = LocalDate.parse(jsonObject.getString("data"), formatter);

                    String url_image = jsonObject.getString("url_image");

                    // Construindo um objeto ProjectionModel com os dados obtidos da API
                    ProjectionModel stats = new ProjectionModel(/* Preencha com os campos necessários */);
                    staticsList.add(stats);
                }
            } else {
                showToast("Erro ao obter dados da API. Tente novamente.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            showToast("Erro ao analisar dados da API.");
        } catch (Exception e) {
            e.printStackTrace();
            showToast("Erro inesperado. Tente novamente mais tarde.");
        }
        return staticsList;
    }

    // Métodos de utilidade para a atividade

    private boolean isValidJsonArray(String json) {
        try {
            new JSONArray(json);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private void showToast(final String message) {
        runOnUiThread(() -> Toast.makeText(Estatisticas.this, message, Toast.LENGTH_SHORT).show());
    }

    // Métodos para abrir outras atividades do menu

    public void OpenNotif(View view) {
        Intent intent = new Intent(this, Notification.class);
        startActivity(intent);
    }

    public void OpenCompras(View view) {
        Intent intent = new Intent(this, Caixa.class);
        startActivity(intent);
    }

    public void OpenProdutos(View view) {
        Intent intent = new Intent(this, Produtos.class);
        startActivity(intent);
    }

    public void OpenHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OpenStats(View view) {
        Intent intent = new Intent(this, Estatisticas.class);
        startActivity(intent);
    }

    public void OpenFunc(View view) {
        Intent intent = new Intent(this, Funcionarios.class);
        startActivity(intent);
    }
}