package com.example.gallery_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.content.Context;

public class MainActivity extends Activity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        //Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));

        //On Click event for Single GridView Item
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView<?> parent, View v, int position, long id){
                //Send image id to FullImageActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                //passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }

}
