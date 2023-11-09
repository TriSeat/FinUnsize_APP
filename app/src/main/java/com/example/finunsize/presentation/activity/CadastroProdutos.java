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

        Database db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.cadastro_produtos);
            get_resources();
            db = new Database(this);
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
            String nomee = nome.getText().toString();
            String marcaa = marca.getText().toString();
            String validadee = validade.getText().toString();
            String quantidadee = quantidade.getText().toString();
            String categoriaa = categoria.getText().toString();
            String descricaoo = descricao.getText().toString();

           /* if (TextUtils.isEmpty(empresaa) || TextUtils.isEmpty(segmentoo) || TextUtils.isEmpty(telefone_emp) || TextUtils.isEmpty(renda_atual) || TextUtils.isEmpty(despesa_atual) || TextUtils.isEmpty(vendass)) {
                Toast.makeText(Cadastro3.this, "Todos os campos devem ser registrados", Toast.LENGTH_SHORT).show();
                Boolean insert = db.insertDb1(empresaa, segmentoo, telefone_emp, renda_atual, despesa_atual, vendass);
            }
            if (insert == true) {
                Toast.makeText(Cadastro3.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                MainActivity.redirect(this, Cadastro2.class);
            } */
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
