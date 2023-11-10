package com.example.finunsize.presentation.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

public class MainActivityNoLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_nologin);
    }

    public void OpenCadastro2(View view) {
        Intent intent = new Intent(this, Cadastro2.class);
        startActivity(intent);
    }

    public void OpenNotif(View view) {
        Intent intent = new Intent(this, Notification.class);
        startActivity(intent);
    }

    public void OpenConfig(View view) {
        Intent intent = new Intent(this, Config.class);
        startActivity(intent);
    }


    // Intents para as outras activities
    public void OpenCompras(View view){
        Intent intent = new Intent(this, Compras.class);
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

    public void OpenCaixa(View view){
        Intent intent = new Intent(this, Produtos.class);
        startActivity(intent);
    }

    public void OpenStats(View view){
        Intent intent = new Intent(this, Estatisticas.class);
        startActivity(intent);
    }
}
