package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

import java.math.BigDecimal;

import persistence.models.CompanyModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cadastro3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro3);
    }

    public void OpenCadastro4(View view) {
        // Obtenha os RadioGroups
        RadioGroup rendaGroup = findViewById(R.id.renda);
        RadioGroup gastoGroup = findViewById(R.id.gasto);
        RadioGroup balancoGroup = findViewById(R.id.balanco);

        // Obtenha os valores selecionados nos RadioButtons como BigDecimal
        BigDecimal rendaValue = getRadioGroupValue(rendaGroup);
        BigDecimal gastoValue = getRadioGroupValue(gastoGroup);
        BigDecimal balancoValue = getRadioGroupValue(balancoGroup);

        // Verifique se alguma opção foi selecionada
        if (rendaValue == null || gastoValue == null || balancoValue == null) {
            // Nenhuma opção selecionada, exiba uma mensagem ou faça o tratamento adequado
            Toast.makeText(this, "Por favor, selecione uma opção em cada categoria.", Toast.LENGTH_SHORT).show();
            return;
        }

        CompanyModel companyModel = new CompanyModel(null, null, null, null, 0, rendaValue, balancoValue, gastoValue, null);

        // Chame o método para enviar os dados para a API
        sendCompanyData(companyModel);

        // Avance para a próxima tela (Cadastro4)
        Intent intent = new Intent(Cadastro3.this, Cadastro4.class);
        startActivity(intent);
    }

    private BigDecimal getRadioGroupValue(RadioGroup radioGroup) {
        // Obtém o RadioButton selecionado no RadioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // Verifica se algum RadioButton está selecionado
        if (selectedId == -1) {
            return null;
        }

        // Obtém o RadioButton selecionado
        RadioButton radioButton = findViewById(selectedId);

        // Obtém o texto associado ao RadioButton
        String buttonText = radioButton.getText().toString();

        // Retorna o valor associado ao texto do RadioButton como BigDecimal
        return parseBigDecimalFromButtonText(buttonText);
    }

    private BigDecimal parseBigDecimalFromButtonText(String buttonText) {
        // Extrai apenas os dígitos do texto do RadioButton
        String valueString = buttonText.replaceAll("\\D", "");

        // Retorna o valor associado ao texto do RadioButton como BigDecimal
        return new BigDecimal(valueString);
    }

    private void sendCompanyData(CompanyModel companyModel) {
        // Verificações adicionais
        if (companyModel.getRenda_media() == null || companyModel.getDespesa_media() == null ||
                companyModel.getBalanco_atual() == null) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finunsize.onrender.com/") // Substitua pela base URL da sua API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crie a instância do ApiService
        ApiService apiService = retrofit.create(ApiService.class);

        // Faça a chamada para o método da API
        Call<Void> call = apiService.cadastrarEmpresa(companyModel);

        // Faça a solicitação assíncrona
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Sucesso na requisição
                    Toast.makeText(Cadastro3.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Cadastro3.this, Cadastro4.class);
                    startActivity(intent);
                } else {
                    // Erro na requisição
                    Toast.makeText(Cadastro3.this, "Erro ao cadastrar empresa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Falha na requisição
                Toast.makeText(Cadastro3.this, "Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }
    public void OpenCadastro2 (View view) {
        MainActivity.redirect(this, Cadastro2.class);
    }


}
