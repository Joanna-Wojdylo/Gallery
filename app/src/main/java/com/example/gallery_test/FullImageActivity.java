package com.example.gallery_test;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class FullImageActivity extends Activity {
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView( R.layout.full_image);

        ImageView fullScreenImageView = findViewById(R.id.full_image_view);

        Intent callingActivityIntent = getIntent();
        if(callingActivityIntent != null){
            String imagePath = callingActivityIntent.getStringExtra("imagePath");
            Glide.with(this)
                    .load(imagePath)
                    .placeholder(R.drawable.ic_launcher_background).centerInside()
                    .into(fullScreenImageView);
        }
    }
    
}
