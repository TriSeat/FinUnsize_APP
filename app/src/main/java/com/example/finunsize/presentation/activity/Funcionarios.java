package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

import async.EmployeeFetcher;
import exception.EmployeeFetchException;
import listener.OnEmployeeFetchListener;
import persistence.models.EmployeeModel;

import java.util.List;

public class Funcionarios extends AppCompatActivity {

    private TextView qtdFuncionarios;
    private TextView listaFuncionarios; // Adicione um TextView para exibir os nomes dos funcionários

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcionarios);

        qtdFuncionarios = findViewById(R.id.qtd_prod);

        fetchEmployees();
    }

    private void fetchEmployees() {
        EmployeeFetcher employeeFetcher = new EmployeeFetcher(getMainLooper(), new OnEmployeeFetchListener() {
            @Override
            public void onEmployeeFetchSuccess(List<EmployeeModel> employees) {
                if (qtdFuncionarios != null) {
                    qtdFuncionarios.setText(String.valueOf(employees.size()));
                }

                StringBuilder funcionariosText = new StringBuilder();
                for (EmployeeModel employee : employees) {
                    funcionariosText.append("Nome: ").append(employee.getNome())
                            .append("\nCargo: ").append(employee.getCargo())
                            .append("\nTelefone: ").append(employee.getTelefone())
                            .append("\nSalário: ").append(employee.getSalario())
                            .append("\n\n");
                }
                if (listaFuncionarios != null) {
                    listaFuncionarios.setText(funcionariosText.toString());
                }
            }

            @Override
            public void onEmployeeFetchError() {
                Toast.makeText(Funcionarios.this, "Erro ao buscar funcionários", Toast.LENGTH_SHORT).show();
            }
        });

        try {
            employeeFetcher.fetchEmployees();
        } catch (EmployeeFetchException e) {
            e.printStackTrace();
            Toast.makeText(this, "Exceção ao buscar funcionários", Toast.LENGTH_SHORT).show();
        }
    }
    // Métodos de intents do menu (não alterados)
    public void OpenCompras(View view) {
        Intent intent = new Intent(this, Compras.class);
        startActivity(intent);
    }

    public void OpenProdutos(View view) {
        Intent intent = new Intent(this, Produtos.class);
        startActivity(intent);
    }

    public void OpenHome(View view) {
        Intent intent = new Intent(this, MainActivityNoLogin.class);
        startActivity(intent);
    }

    public void OpenStats(View view) {
        Intent intent = new Intent(this, Estatisticas.class);
        startActivity(intent);
    }

    public void OpenFunc(View view) {
        // Não faz nada, pois já está na tela de funcionários
    }
}