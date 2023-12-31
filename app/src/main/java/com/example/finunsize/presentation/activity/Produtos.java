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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import persistence.models.ProductModel;
import request.Connection;

public class Produtos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<ProductModel> productList;
    private TextView qtdProdTextView;
    private String token;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produtos);

        token = getIntent().getStringExtra("token");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = fetchProductListFromApi(token);

        qtdProdTextView = findViewById(R.id.qtd_prod);
        updateQtdProdTextView(productList.size());

        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
    }

    public List<ProductModel> fetchProductListFromApi(String token) {
        List<ProductModel> productList = new ArrayList<>();

        String apiUrl = "https://finunsize.onrender.com/product/";

        try {
            String apiResponse = Connection.connectHttpWithHeader(apiUrl, token);

            if (apiResponse != null && isValidJsonArray(apiResponse)) {
                JSONArray jsonArray = new JSONArray(apiResponse);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String nome = jsonObject.getString("nome");
                    String cod_barras = jsonObject.getString("cod_barras");
                    int quantidade = jsonObject.getInt("quantidade");



                    // Tratamento de datas
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate validade = LocalDate.parse(jsonObject.getString("validade"), formatter);
                    LocalDate data_cadastro = LocalDate.parse(jsonObject.getString("data_cadastro"), formatter);

                    String url_image = jsonObject.getString("url_image");

                    // Construindo um objeto ProductModel com os dados obtidos da API
                    ProductModel product = new ProductModel(cod_barras, nome, quantidade, null, validade, null, null, null, null, data_cadastro, url_image);
                    productList.add(product);
                }
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
        return productList;
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
                Toast.makeText(Produtos.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void verDescricao(View view) {
        // Obtém o item associado ao botão clicado na RecyclerView
        int position = recyclerView.getChildLayoutPosition((View) view.getParent());
        ProductModel selectedProduct = productList.get(position);

        // Inicia a nova atividade para exibir a descrição do produto
        Intent intent = new Intent(this, DescricaoProdutos.class);
        intent.putExtra("selectedProduct", selectedProduct);
        startActivity(intent);
    }

    private void updateQtdProdTextView(int qtdProd) {
        if (qtdProdTextView != null) {
            qtdProdTextView.setText(String.valueOf(qtdProd));
        }
    }

    public void OpenNotif(View view) {
        Intent intent = new Intent(this, Notification.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    //INTENTS DO MENU
    public void OpenCompras(View view) {
        Intent intent = new Intent(this, Caixa.class);
        startActivity(intent);
    }

    public void OpenProdutos(View view) {
        Intent intent = new Intent(this, Produtos.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    public void OpenHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OpenStats(View view) {
        Intent intent = new Intent(this, Estatisticas.class);
        startActivity(intent);
    }

    public void OpenFunc(View view) {
        Intent intent = new Intent(this, Funcionarios.class);
        startActivity(intent);
    }
}
