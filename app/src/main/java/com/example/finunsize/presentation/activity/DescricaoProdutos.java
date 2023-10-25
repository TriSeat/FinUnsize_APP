package com.example.finunsize.presentation.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

public class DescricaoProdutos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_perfil);
    }

    public void OpenProdutos (View view) {
        MainActivity.redirect(this, Produtos.class);
    }
}
