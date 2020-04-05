package com.example.gallery_test;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class FullImageActivity extends FragmentActivity {
    private ViewPager viewPager;
    private  ViewPagerAdapter viewPagerAdapter;
    private List<Fragment> listFragments = new ArrayList<>();
    private List<String> arrayList = new ArrayList<>();

    private int position=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity_viewpager);

        getArguments();
        createFragments();

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),listFragments);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);
    }
    private void getArguments(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            arrayList = bundle.getStringArrayList("imageURLs");
            position = bundle.getInt("position");
        }
    }

    private void createFragments(){
        for (int i=0; i<arrayList.size(); i++){
            Bundle bundle = new Bundle();
            bundle.putString("imageURL", arrayList.get(i));
            ImageFragment imageFragment = new ImageFragment();
            imageFragment.setArguments(bundle);
            listFragments.add(imageFragment);
        }
    }
}
/**public class FullImageActivity extends Activity {
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
    
} */
