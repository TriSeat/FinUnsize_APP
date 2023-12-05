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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;
import persistence.models.AddressModel;
import persistence.models.EmployeeModel;
import persistence.models.OfficeModel;
import request.Connection;

public class Funcionarios extends AppCompatActivity {

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

        qtdFuncionariosTextView = findViewById(R.id.qtd_func);

        employeeList = new ArrayList<>();
        employeeAdapter = new EmployeeAdapter(this, employeeList);

        fetchEmployeeListFromApi(token);
    }

    public void fetchEmployeeListFromApi(String token) {
        String apiUrl = "https://finunsize.onrender.com/employee/";

        try {
            String apiResponse = Connection.connectHttpWithHeader(apiUrl, token);

            if (apiResponse != null && isValidJsonArray(apiResponse)) {
                JSONArray jsonArray = new JSONArray(apiResponse);

                employeeList.clear(); // Limpa a lista antes de adicionar novos itens

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int idFuncionario = jsonObject.getInt("id_funcionario");
                    String cpf = jsonObject.getString("cpf");
                    String nome = jsonObject.getString("nome");
                    String cargo = jsonObject.getString("cargo");
                    String turno = jsonObject.getString("turno");
                    int telefone = jsonObject.getInt("telefone");
                    String admissao = jsonObject.getString("admissao");
                    int id_logradouro = jsonObject.getInt("id_logradouro");
                    int cep = jsonObject.getInt("cep");
                    String rua = jsonObject.getString("rua");
                    String numero = jsonObject.getString("numero");
                    String complemento = jsonObject.getString("complemento");
                    String referencia = jsonObject.getString("referencia");
                    String cidade = jsonObject.getString("cidade");
                    String observacao = jsonObject.getString("observacao");
                    BigDecimal salario = new BigDecimal(jsonObject.getString("salario"));


                    OfficeModel officeModel = new OfficeModel(cargo);

                    // Criar LocalDate (considerando que admissaoString esteja no formato apropriado)
                    LocalDate admissaoo = LocalDate.parse(admissao);

                    // Criar AdressModel
                    AddressModel adressModel = new AddressModel(id_logradouro, cep, rua, numero, complemento, referencia, cidade);

                    // Construindo um objeto EmployeeModel com os dados obtidos da API
                    EmployeeModel employee = new EmployeeModel(idFuncionario, cpf, nome, officeModel, turno, telefone, admissaoo, adressModel, salario, observacao, false, null, null);
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

    private void updateQtdFuncionariosTextView(int qtdFuncionarios) {
        if (qtdFuncionariosTextView != null) {
            qtdFuncionariosTextView.setText(String.valueOf(qtdFuncionarios));
        }
    }
}