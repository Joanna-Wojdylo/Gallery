package com.example.gallery_test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

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
                System.out.println(imageAdapter.imageArray);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                bundle.putStringArrayList("imageURLs", imageAdapter.imageArray);
                Intent intent = new Intent(MainActivity.this, FullImageActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            //String imagePath = parent.getAdapter().getItem(position).toString();
            //Intent intent = new Intent(MainActivity.this, FullImageActivity.class);
            //intent.putExtra("imagePath", imagePath);

            //startActivity(intent);
            }

        });
    }
   /** @SuppressLint("Recycle")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void loadImagesFromDevice(Context activity){

        ArrayList<String> imageArray = new ArrayList<>();
        imageArray.clear();

        Uri externalContentPath;
        Cursor cursor;
        int photoIndex;
        int folderIndex;
        String fullImagePath;

        externalContentPath = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] imagesAndFoldersKeeper = {MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = activity.getContentResolver().query(externalContentPath, imagesAndFoldersKeeper,  null, null, orderBy + " DESC");

        assert cursor != null;
        photoIndex = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
        //folderIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        int position = 0;
        while (cursor.moveToNext()){
            fullImagePath = cursor.getString(photoIndex);
            imageArray.add(fullImagePath);
        }
        return imageArray;

    } */
}
