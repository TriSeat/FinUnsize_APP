package com.example.finunsize.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.finunsize.R;

public class TelaCheiaIMG extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_fullscreen);

        Intent intent = getIntent();
        if (intent != null) {
            String imagePath = intent.getStringExtra("imagePath");
            if (imagePath != null) {
                ImageView fullscreenImageView = findViewById(R.id.fullscreenImageView);
                Glide.with(this).load(imagePath).into(fullscreenImageView);
            }
        }
    }
}
