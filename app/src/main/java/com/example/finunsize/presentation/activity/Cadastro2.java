package com.example.finunsize.presentation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

public class Cadastro2 extends AppCompatActivity {

    EditText empresa, segmento, telefoneemp, rendaatual, despesaatual, vendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro2);
        get_resources();
    }

    public void get_resources() {
        empresa = findViewById(R.id.empresa);
        segmento = findViewById(R.id.segmento);
        telefoneemp = findViewById(R.id.telefoneemp);
        rendaatual = findViewById(R.id.rendaatual);
        despesaatual = findViewById(R.id.despesaatual);
        vendas = findViewById(R.id.vendas);
    }

    public void OpenCadastro3 (View view) {

        MainActivity.redirect(this, Cadastro3.class);;

        }

    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }

    public void OpenCadastro (View view) {
        MainActivity.redirect(this, Cadastro.class);
    }

}
