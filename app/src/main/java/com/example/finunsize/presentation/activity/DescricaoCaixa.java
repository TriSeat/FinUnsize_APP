package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

import java.time.format.DateTimeFormatter;

import persistence.models.CashierModel;
import persistence.models.LogCashierModel;
import persistence.models.ProductModel;

public class DescricaoCaixa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_caixa);

        Intent intent = getIntent();
        if (intent.hasExtra("selectedCashier") && intent.hasExtra("logCashier")) {
            CashierModel selectedCashier = (CashierModel) intent.getSerializableExtra("selectedCashier");
            LogCashierModel logCashier = (LogCashierModel) intent.getSerializableExtra("logCashier");

            // Agora você pode usar selectedCashier e logCashier para preencher os campos
            preencherCamposDescricao(selectedCashier, logCashier);
        }
    }

    public void preencherCamposDescricao(CashierModel cashier, LogCashierModel logcashier) {

        // Obtenha referências para os TextViews do layout
        TextView NomeId = findViewById(R.id.caixaName);
        TextView Status = findViewById(R.id.caixaStatus);
        TextView dataFunc = findViewById(R.id.dataFuncionamentoText);
        TextView ValorIni = findViewById(R.id.valor_inicial);
        TextView ValorFin = findViewById(R.id.valor_final);
        TextView DataAber = findViewById(R.id.abertura);
        TextView DataFecha = findViewById(R.id.fechamento);



        // Preencha os campos com os dados do produto
        if (NomeId != null && Status != null && dataFunc != null && ValorIni != null &&
                ValorFin != null && DataAber != null && DataFecha != null) {

            String nomeComId = cashier.getNome() + " (" + cashier.getIdcaixa() + ")";

            // Preencha os campos com os dados do produto
            NomeId.setText(nomeComId);
            Status.setText(cashier.getStatus());
            dataFunc.setText(logcashier.getData_funcionamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ValorIni.setText("R$ " + logcashier.getValor_inicial().toString());
            ValorFin.setText("R$ " + logcashier.getValor_final().toString());
            DataAber.setText(logcashier.getAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            DataFecha.setText(logcashier.getFechamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }


    public void OpenCaixa (View view) {
        MainActivity.redirect(this, Caixa.class);
    }
}
