package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

import java.time.format.DateTimeFormatter;

import persistence.models.CashierModel;
import persistence.models.LogCashierModel;
import request.Connection;

public class DescricaoCaixa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_caixa);

        Intent intent = getIntent();
        if (intent.hasExtra("selectedCashier") && intent.hasExtra("logCashier")) {
            CashierModel selectedCashier = (CashierModel) intent.getSerializableExtra("selectedCashier");
            LogCashierModel logCashier = (LogCashierModel) intent.getSerializableExtra("logCashier");

            preencherCamposDescricao(selectedCashier, logCashier);
        }

        if (intent.hasExtra("token")) {
            String token = intent.getStringExtra("token");

            try {
                String apiResponse = Connection.connectHttpWithHeader("https://finunsize.onrender.com/cashier/", token);
                // Lógica para processar a resposta da API, se necessário
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao obter dados da API", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void preencherCamposDescricao(CashierModel cashier, LogCashierModel logCashier) {
        TextView nomeId = findViewById(R.id.caixaName);
        TextView status = findViewById(R.id.caixaStatus);
        TextView dataFunc = findViewById(R.id.dataFuncionamentoText);
        TextView valorIni = findViewById(R.id.valor_inicial);
        TextView valorFin = findViewById(R.id.valor_final);
        TextView dataAber = findViewById(R.id.abertura);
        TextView dataFecha = findViewById(R.id.fechamento);

        if (nomeId != null && status != null && dataFunc != null && valorIni != null &&
                valorFin != null && dataAber != null && dataFecha != null) {

            String nomeComId = cashier.getNome() + " (" + cashier.getIdcaixa() + ")";

            nomeId.setText(nomeComId);
            status.setText(cashier.getStatus());
            dataFunc.setText(logCashier.getData_funcionamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            valorIni.setText("R$ " + logCashier.getValor_inicial().toString());
            valorFin.setText("R$ " + logCashier.getValor_final().toString());
            dataAber.setText(logCashier.getAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            dataFecha.setText(logCashier.getFechamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }

    public void openCaixa(View view) {
        MainActivity.redirect(this, Caixa.class);
    }
}
