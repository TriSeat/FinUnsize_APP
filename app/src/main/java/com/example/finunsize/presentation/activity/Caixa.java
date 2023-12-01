package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finunsize.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import persistence.models.CashierModel;
import request.Connection;

public class Caixa extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CashierAdapter cashierAdapter;
    private List<CashierModel> cashierList;
    private TextView lancaTextView;
    private String token;

    private final Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caixa);

        // Obter token da intent
        token = getIntent().getStringExtra("token");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cashierList = fetchCashierListFromApi();

        lancaTextView = findViewById(R.id.lanca);
        updatelancaTextView(cashierList.size());

        cashierAdapter = new CashierAdapter(this, cashierList);
        recyclerView.setAdapter(cashierAdapter);
    }

    public List<CashierModel> fetchCashierListFromApi() {
        List<CashierModel> cashierList = new ArrayList<>();

        String apiUrl = "https://finunsize.onrender.com/cashier/";

        try {
            String apiResponse = Connection.connectHttp(apiUrl, token);

            if (apiResponse != null && isValidJsonArray(apiResponse)) {
                JSONArray jsonArray = new JSONArray(apiResponse);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int id_caixa = jsonObject.getInt("id_caixa");
                    String nome = jsonObject.getString("nome");
                    String status = jsonObject.getString("status");

                    CashierModel cashier = new CashierModel(id_caixa, nome, status, null);
                    cashierList.add(cashier);
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
        return cashierList;
    }

    private boolean isValidJsonArray(String json) {
        try {
            new JSONArray(json);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private void showToast(final String message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Caixa.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void verDescricao(View view) {
        int position = recyclerView.getChildLayoutPosition((View) view.getParent());
        CashierModel selectedCashier = cashierList.get(position);

        Intent intent = new Intent(this, DescricaoCaixa.class);
        intent.putExtra("selectedCashier", selectedCashier);
        startActivity(intent);
    }

    private void updatelancaTextView(int lancamentos) {
        if (lancaTextView != null) {
            lancaTextView.setText(String.valueOf(lancamentos));
        }
    }

    public void OpenNotif(View view) {
        Intent intent = new Intent(this, Notification.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    //INTENTS DO MENU
    public void OpenCompras(View view){
        Intent intent = new Intent(this, Caixa.class);
        startActivity(intent);
    }

    public void OpenProdutos(View view){
        Intent intent = new Intent(this, Produtos.class);
        intent.putExtra("token", token); // Passa o token para a próxima atividade
        startActivity(intent);
    }

    public void OpenHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OpenStats(View view){
        Intent intent = new Intent(this, Estatisticas.class);
        startActivity(intent);
    }

    public void OpenFunc(View view){
        Intent intent = new Intent(this, Funcionarios.class);
        startActivity(intent);
    }
}
