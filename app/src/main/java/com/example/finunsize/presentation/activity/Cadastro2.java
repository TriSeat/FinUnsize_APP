package com.example.finunsize.presentation.activity;

import android.content.Intent;
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

public class Cadastro2 extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
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

    public void EditImage(View view) {
        openGallery();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
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

            // Redimensionar a imagem para 180x180dp usando Glide
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                Glide.with(this)
                        .load(bitmap)
                        .apply(RequestOptions.overrideOf(180, 180))
                        .into(imagemPerfil);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Obtém o caminho da imagem
            String imagePath = selectedImageUri.toString();

            // Salva o caminho da imagem na base de dados DatabaseImage
            DatabaseImage databaseImage = new DatabaseImage(this);
            if (databaseImage.insertImage(imagePath)) {
                Toast.makeText(this, "Imagem salva com sucesso na base de dados", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao salvar imagem na base de dados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void OpenCadastro3 (View view) {
        MainActivity.redirect(this, Cadastro3.class);
    }

    public void OpenLogin (View view) {
        MainActivity.redirect(this, Login.class);
    }

    public void OpenCadastro (View view) {
        MainActivity.redirect(this, Cadastro.class);
    }

}
