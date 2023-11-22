package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import persistence.models.ProductModel;

public class Produtos extends AppCompatActivity {

    LinearLayout main_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produtos);
    }

    public void OpenNotif(View view){
        Intent intent = new Intent(this, Notification.class);
        startActivity(intent);
    }

    //INTENTS DO MENU
    public void OpenCompras(View view){
        Intent intent = new Intent(this, Compras.class);
        startActivity(intent);
    }

    public void OpenProdutos(View view){
        Intent intent = new Intent(this, Produtos.class);
        startActivity(intent);
    }

    public void OpenHome(View view){
        Intent intent = new Intent(this, MainActivityNoLogin.class);
        startActivity(intent);
    }

    public void OpenStats(View view){
        Intent intent = new Intent(this, Estatisticas.class);
        startActivity(intent);
    }

    public void OpenFunc(View view){
        Intent intent = new Intent(this, Compras.class);
        startActivity(intent);
    }
}
