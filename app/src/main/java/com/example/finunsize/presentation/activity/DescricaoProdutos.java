package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

import java.time.format.DateTimeFormatter;

import persistence.models.ProductModel;
import request.Connection;

public class DescricaoProdutos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_prod);

        Intent intent = getIntent();
        if (intent.hasExtra("selectedProduct")) {
            ProductModel selectedProduct = (ProductModel) intent.getSerializableExtra("selectedProduct");

            preencherCamposDescricao(selectedProduct);
        }

        if (intent.hasExtra("token")) {
            String token = intent.getStringExtra("token");

            try {
                String apiResponse = Connection.connectHttpWithHeader("https://finunsize.onrender.com/product/", token);
                // Lógica para processar a resposta da API, se necessário
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao obter dados da API", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void preencherCamposDescricao(ProductModel product) {
        TextView nameText = findViewById(R.id.productName);
        TextView descricaoText = findViewById(R.id.DescricaoText);
        TextView marcaText = findViewById(R.id.MarcaText);
        TextView validityText = findViewById(R.id.validityText);
        TextView qtdText = findViewById(R.id.qtdText);
        TextView precoVar = findViewById(R.id.PrecoVar);
        TextView precoAta = findViewById(R.id.PrecoAta);
        TextView fornecedorText = findViewById(R.id.FornecedorText);

        if (nameText != null && descricaoText != null && marcaText != null && validityText != null &&
                qtdText != null && precoVar != null && precoAta != null && fornecedorText != null) {

            nameText.setText(product.getNome());
            descricaoText.setText(product.getDescricao());
            marcaText.setText(product.getInformacoes().getMarca());
            validityText.setText(product.getValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            qtdText.setText(String.valueOf(product.getQuantidade()));
            precoVar.setText("R$ " + product.getVarejo().toString());
            precoAta.setText("R$ " + product.getAtacado().toString());
            fornecedorText.setText(product.getFornecedor().getNome());
        }
    }

    public void openProdutos(View view) {
        MainActivity.redirect(this, Produtos.class);
    }
}