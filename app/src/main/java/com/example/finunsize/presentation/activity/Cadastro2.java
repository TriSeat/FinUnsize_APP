package com.example.finunsize.presentation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

public class Cadastro2 extends AppCompatActivity {

    EditText empresa, segmento, telefoneemp, rendaatual, despesaatual, vendas;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro2);
        get_resources();
        db = new Database(this);
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

            MainActivity.redirect(this, Cadastro3.class);

        String empresaa = empresa.getText().toString();
        String segmentoo = segmento.getText().toString();
        String telefone_emp = telefoneemp.getText().toString();
        String renda_atual = rendaatual.getText().toString();
        String despesa_atual = despesaatual.getText().toString();
        String vendass = vendas.getText().toString();

           /* if (TextUtils.isEmpty(empresaa) || TextUtils.isEmpty(segmentoo) || TextUtils.isEmpty(telefone_emp) || TextUtils.isEmpty(renda_atual) || TextUtils.isEmpty(despesa_atual) || TextUtils.isEmpty(vendass)) {
                Toast.makeText(Cadastro3.this, "Todos os campos devem ser registrados", Toast.LENGTH_SHORT).show();
                Boolean insert = db.insertDb1(empresaa, segmentoo, telefone_emp, renda_atual, despesa_atual, vendass);
            }
            if (insert == true) {
                Toast.makeText(Cadastro3.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                MainActivity.redirect(this, Cadastro2.class);
            } */
        }

    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }

    public void OpenCadastro (View view) {
        MainActivity.redirect(this, Cadastro.class);
    }

}
