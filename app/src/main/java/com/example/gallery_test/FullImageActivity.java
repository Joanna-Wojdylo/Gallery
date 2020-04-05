package com.example.gallery_test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
public class FullImageActivity extends FragmentActivity {
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

        ViewPager viewPager = findViewById(R.id.viewPager);
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
