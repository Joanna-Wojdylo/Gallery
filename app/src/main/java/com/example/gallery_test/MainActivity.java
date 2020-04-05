package com.example.gallery_test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends Activity {

    GridView gridView;
    ImageAdapter imageAdapter;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private ArrayList<String> imageArray = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Check if app has permissions to read storage, if not ask for permission
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }

        GridView gridView = findViewById(R.id.gridView);

        //Instance of ImageAdapter Class
        imageAdapter = new ImageAdapter(this);

        gridView.setAdapter(imageAdapter);

        //On Click event for Single GridView Item
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                bundle.putStringArrayList("imageURLs", imageAdapter.imageArray);
                Intent intent = new Intent(MainActivity.this, FullImageActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }

        });
    }
}
