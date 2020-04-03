package com.example.gallery_test;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class FullImageActivity extends Activity {
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView( R.layout.full_image);

        //get indent data
        Intent i = getIntent();

        //Select image id
        int position = Objects.requireNonNull(i.getExtras()).getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = findViewById( R.id.full_image_view);
        imageView.setImageResource(Integer.parseInt(imageAdapter.imageArray.get(position)));
    }
    
}