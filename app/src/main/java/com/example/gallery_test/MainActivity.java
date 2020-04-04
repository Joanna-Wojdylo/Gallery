package com.example.gallery_test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.content.Context;

public class MainActivity extends Activity {

    GridView gridView;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

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
        gridView.setAdapter(new ImageAdapter(this));

        //On Click event for Single GridView Item
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String imagePath = parent.getAdapter().getItem(position).toString();
            Intent intent = new Intent(MainActivity.this, FullImageActivity.class);
            intent.putExtra("imagePath", imagePath);

            startActivity(intent);
            }

        });
    }

}
