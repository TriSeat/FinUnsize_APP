package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finunsize.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import persistence.models.EmployeeModel;
import request.Connection;

public class Funcionarios extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;
    private List<EmployeeModel> employeeList;
    private TextView qtdFuncionariosTextView;
    private String token;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcionarios);

        token = getIntent().getStringExtra("token");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        qtdFuncionariosTextView = findViewById(R.id.qtd_func);

        employeeList = new ArrayList<>();
        employeeAdapter = new EmployeeAdapter(this, employeeList);
        recyclerView.setAdapter(employeeAdapter);

        fetchEmployeeListFromApi(token);
    }

    public void fetchEmployeeListFromApi(String token) {
        String apiUrl = "https://finunsize.onrender.com/employee/";

        try {
            String apiResponse = Connection.connectHttp(apiUrl, token);

            if (apiResponse != null && isValidJsonArray(apiResponse)) {
                JSONArray jsonArray = new JSONArray(apiResponse);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int idFuncionario = jsonObject.getInt("idFuncionario");
                    String nome = jsonObject.getString("nome");
                    String cpf = jsonObject.getString("cpf");
                    String turno = jsonObject.getString("turno");
                    BigDecimal salario = (BigDecimal) jsonObject.get("salario");

                    // Construindo um objeto EmployeeModel com os dados obtidos da API
                    EmployeeModel employee = new EmployeeModel(idFuncionario, cpf, nome, turno, salario);
                    employeeList.add(employee);
                }

                updateQtdFuncionariosTextView(employeeList.size());
                employeeAdapter.notifyDataSetChanged();

            } else {
                showToast("Erro ao obter dados da API. Tente novamente.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            showToast("Erro ao analisar dados da API.");
        } catch (Exception e) {
            e.printStackTrace();
            showToast("Erro inesperado. Tente novamente mais tarde.");
        }
    }

    private boolean isValidJsonArray(String json) {
        try {
            new JSONArray(json);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private void showToast(final String message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Funcionarios.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void OpenNotif(View view) {
        Intent intent = new Intent(this, Notification.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }


    private void updateQtdFuncionariosTextView(int qtdFuncionarios) {
        if (qtdFuncionariosTextView != null) {
            qtdFuncionariosTextView.setText(String.valueOf(qtdFuncionarios));
        }
    }
}
