package com.example.finunsize.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finunsize.R;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    private static final String SHARED_MAIN = "Kmain";
    private static final String SHARED_NAME = "Kname";
    TextView nome_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_resources();
    }

    public void get_resources() {
        nome_perfil = findViewById(R.id.nome_perfil);
        preferences = getSharedPreferences(SHARED_MAIN , MODE_PRIVATE);
        String set_name = preferences.getString(SHARED_NAME, null);
        nome_perfil.setText(set_name);

    }


    public static void redirect(Activity activity, Class Class) {
        Intent intent = new Intent(activity, Class);
        activity.startActivity(intent);
    }


    public void OpenNotif(View view){
        Intent intent = new Intent(this, Notification.class);
        startActivity(intent);
    }

    public void OpenConfig(View view){
        Intent intent = new Intent(this, Config.class);
        startActivity(intent);
    }
    public void OpenCatálogo(View view){
        Intent intent = new Intent(this, Caixa.class);
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