package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

public class CadastroProdutos extends AppCompatActivity {

        ImageView perfil;

        EditText nome, marca, validade, quantidade, categoria, descricao;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.cadastro_produtos);
            get_resources();
        }

        public void get_resources() {
            nome = findViewById(R.id.nome);
            marca = findViewById(R.id.marca);
            validade = findViewById(R.id.validade);
            quantidade = findViewById(R.id.quantidade);
            categoria = findViewById(R.id.despesaatual);
            descricao = findViewById(R.id.descricao);
        }

        public void ProdCadastrado (View view) {
        }



    // Intents para as outras activities
    /* public void OpenCompras(View view){
        Intent intent = new Intent(this, Compras.class);
        startActivity(intent);
    } */

    public void OpenProdutos(View view){
        Intent intent = new Intent(this, Produtos.class);
        startActivity(intent);
    }

    public void OpenHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OpenCaixa(View view){
        Intent intent = new Intent(this, Produtos.class);
        startActivity(intent);
    }

    /* public void OpenStats(View view){
        Intent intent = new Intent(this, Estatisticas.class);
        startActivity(intent);
    } */
}
