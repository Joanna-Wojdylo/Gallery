package com.example.gallery_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.provider.MediaStore;
import android.net.Uri;
import android.widget.AdapterView.OnItemClickListener;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    public Cursor cursor; //cursor to access the results from querying for images on the SD card
    private int columnIndex; //column index for the Thumbnails Image IDs
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up an array of the Thumbnail Image ID column we want
        String[] projection = {MediaStore.Images.Thumbnails._ID};
        //Create cursor pointing to the SDCard
        cursor = getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                projection, // which columns to return
                null, //Return all rows
                null,
                MediaStore.Images.Thumbnails._ID+""); // Sorted by image ID (probably)
        //Get the column index of the Thumbnails Image ID
        assert cursor != null;
        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);

        GridView gridView;
        gridView = (GridView) findViewById(R.id.gridView);

        //Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this, cursor, columnIndex));

        //On Click event for Single GridView Item
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id){
                //Get the data location of the image
                String [] projection = {MediaStore.Images.Media.DATA};
                cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        projection, // which columns to return
                        null, //Return all rows
                        null,
                        null);
                assert cursor != null;
                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToPosition(position); //get image filename
                String imagePath = cursor.getString(columnIndex); //this path will be used to do further processing ex. full screen display
            }

        });
        /**gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView<?> parent, View v, int position, long id){
                //Send image id to FullImageActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                //passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
         */
    }

}
