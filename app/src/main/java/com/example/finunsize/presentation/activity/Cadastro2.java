package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finunsize.R;

import java.io.IOException;

import persistence.models.CompanyModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cadastro2 extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private ImageView imagemPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro2);

        imagemPerfil = findViewById(R.id.imagem_perfil);

        imagemPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenImage();
            }
        });
    }


    public void OpenCadastro3(View view) {
        String nomeEmpresa = getEditTextValue(R.id.nomeEmpresa);
        String segmento = getEditTextValue(R.id.segmento);
        String slogan = getEditTextValue(R.id.slogan);
        String cnpj = getEditTextValue(R.id.cnpj);
        String cep = getEditTextValue(R.id.cep);
        String razao = getEditTextValue(R.id.razao);

        CompanyModel companyModel = new CompanyModel(cnpj, nomeEmpresa, slogan, segmento, Integer.parseInt(cep), null, null, null, razao);

        // Inicie a chamada para a API
        sendCompanyData(companyModel);
    }


    private String getEditTextValue(int editTextId) {
        EditText editText = findViewById(editTextId);
        return editText.getText().toString();
    }

    private void sendCompanyData(CompanyModel companyModel) {
        // Verificações adicionais
        if (companyModel.getNome().isEmpty() || companyModel.getCnpj().isEmpty() || companyModel.getRazao().isEmpty() ||
                companyModel.getSlogan().isEmpty() || companyModel.getSegmento().isEmpty() || companyModel.getCep() == 0) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se o CNPJ possui um formato válido
        if (!isValidCNPJ(companyModel.getCnpj())) {
            Toast.makeText(this, "CNPJ inválido. Certifique-se de inserir um CNPJ válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Continue com a chamada para a API
        // Configure o Retrofit
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
                    Toast.makeText(Cadastro2.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Cadastro2.this, Cadastro3.class);
                    startActivity(intent);
                } else {
                    // Erro na requisição
                    Toast.makeText(Cadastro2.this, "Erro ao cadastrar empresa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Falha na requisição
                Toast.makeText(Cadastro2.this, "Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidCNPJ(String cnpj) {
        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifica se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * (13 - i);
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : (11 - resto);

        // Verifica o primeiro dígito verificador
        if (Character.getNumericValue(cnpj.charAt(12)) != digito1) {
            return false;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * (14 - i);
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : (11 - resto);

        // Verifica o segundo dígito verificador
        return Character.getNumericValue(cnpj.charAt(13)) == digito2;
    }

    public void EditImage(View view) {
        openGallery();
    }

    private void openGallery() {
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Se já tiver permissão, abra a galeria
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        } else {
            // Se não tiver permissão, solicite ao usuário
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    // Adicione este método para tratar a resposta da solicitação de permissão
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permissão concedida, abra a galeria
                    openGallery();
                } else {
                    // Permissão negada, informe ao usuário
                    Toast.makeText(this, "A permissão é necessária para acessar a galeria.", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void OpenImage() {
        // Obtém o caminho da imagem da base de dados
        DatabaseImage databaseImage = new DatabaseImage(this);
        String imagePath = databaseImage.getImagePath();

        // Verifica se o caminho da imagem é válido
        if (imagePath != null) {
            // Abre a tela de visualização em tela cheia
            Intent fullscreenIntent = new Intent(this, TelaCheiaIMG.class);
            fullscreenIntent.putExtra("imagePath", imagePath);
            startActivity(fullscreenIntent);
        } else {
            Toast.makeText(this, "Imagem não encontrada", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();

            // Verifique se o URI da imagem não é nulo
            if (selectedImageUri != null) {
                // Salvar o URI da imagem no banco de dados
                saveImageUri(selectedImageUri.toString());

                // Exibir a imagem usando Glide
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    Glide.with(this)
                            .load(bitmap)
                            .apply(RequestOptions.overrideOf(180, 180))
                            .into(imagemPerfil);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(this, "Imagem salva com sucesso no banco de dados", Toast.LENGTH_SHORT).show();
            } else {
                // O usuário não escolheu uma imagem
                Toast.makeText(this, "Escolha uma imagem válida", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void saveImageUri(String imagePath) {
        // Salvar o URI da imagem na base de dados local
        DatabaseImage databaseImage = new DatabaseImage(this);
        databaseImage.insertImage(imagePath);
    }


    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }

    public void OpenCadastro (View view) {
        MainActivity.redirect(this, Cadastro.class);
    }

}
