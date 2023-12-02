package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finunsize.R;

import java.io.IOException;

import persistence.models.CompanyModel;
import persistence.models.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroPerfil extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private ImageView imagemPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_perfil);

        imagemPerfil = findViewById(R.id.imagem_perfil);

        imagemPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenImage();
            }
        });
    }

    public void OpenHome (View view) {
        String username = getEditTextValue(R.id.username);

        UserModel userModel = new UserModel(null, username, null, null, null,0, 0, null, null, null);

        sendCompanyData(userModel);
    }

    private String getEditTextValue(int editTextId) {
        EditText editText = findViewById(editTextId);
        return editText.getText().toString();
    }


    private void sendCompanyData(UserModel userModel) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finunsize.onrender.com/") // Substitua pela base URL da sua API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Faça a chamada para o método da API
        Call<Void> call = apiService.cadastrarUsuário(userModel);
        Log.d("CadastroPerfil", "URL: " + call.request().url());

        // Faça a solicitação assíncrona
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Sucesso na requisição
                    Toast.makeText(CadastroPerfil.this, "Cadastro finalizado com sucesso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CadastroPerfil.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (response.code() == 404) {
                        try {
                            String errorBody = response.errorBody().string();
                            Log.e("CadastroPerfil", "Erro 404 - Corpo da Resposta: " + errorBody);
                            Toast.makeText(CadastroPerfil.this, "Recurso não encontrado. Verifique a rota no servidor.", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // Erro na requisição, código diferente de 404
                        Toast.makeText(CadastroPerfil.this, "Erro na requisição, código: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Falha na requisição
                t.printStackTrace(); // Imprime o stack trace da exceção
                Log.e("CadastroPerfil", "Falha na requisição", t);
                Toast.makeText(CadastroPerfil.this, "Falha na requisição", Toast.LENGTH_SHORT).show();

                // Exibe a mensagem de erro específica para o usuário
                Toast.makeText(CadastroPerfil.this, "Erro ao Cadastrar o usuário", Toast.LENGTH_SHORT).show();
            }
        });
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
    public void OpenCadastro4 (View view) {
        MainActivity.redirect(this, Cadastro4.class);
    }

}
