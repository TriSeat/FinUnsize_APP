package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import persistence.models.CashierModel;
import persistence.models.ProductModel;
import request.Connection;

public class Caixa extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CashierAdapter cashierAdapter;
    private List<CashierModel> cashierList;
    private TextView lancaTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caixa);

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
            String apiResponse = Connection.connectHttp(apiUrl);

            if (apiResponse != null) {
                JSONArray jsonArray = new JSONArray(apiResponse);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int id_caixa = jsonObject.getInt("id_caixa");
                    String nome = jsonObject.getString("nome");
                    String status = jsonObject.getString("status");

                    CashierModel cashier = new CashierModel(id_caixa, nome, status,null);
                    cashierList.add(cashier);
                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Caixa.this, "Erro ao obter dados da API. Tente novamente.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Caixa.this, "Erro ao analisar dados da API.", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Caixa.this, "Erro inesperado. Tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return cashierList;
    }

    public void verDescricao(View view) {
        // Obtém o item associado ao botão clicado na RecyclerView
        int position = recyclerView.getChildLayoutPosition((View) view.getParent());
        CashierModel selectedCashier = cashierList.get(position);

        // Inicia a nova atividade para exibir a descrição do produto
        Intent intent = new Intent(this, DescricaoCaixa.class);
        intent.putExtra("selectedCashier", selectedCashier);
        startActivity(intent);
    }

    private void updatelancaTextView(int lancamentos) {
        if (lancaTextView != null) {
            lancaTextView.setText(String.valueOf(lancamentos));
        }
    }

    public void OpenNotif(View view){
        Intent intent = new Intent(this, Notification.class);
        startActivity(intent);
    }

    //INTENTS DO MENU
    public void OpenCompras(View view){
        Intent intent = new Intent(this, Caixa.class);
        startActivity(intent);
    }

    public void OpenProdutos(View view){
        Intent intent = new Intent(this, Produtos.class);
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
