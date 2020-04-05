package com.example.gallery_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class ImageFragment extends Fragment {
    private String imageURL;
    public ImageFragment(){
        //required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageURL = getArguments().getString("imageURL");
        Glide.with(this)
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_background).centerInside()
                .into(imageView);
        return view;
    }

}
