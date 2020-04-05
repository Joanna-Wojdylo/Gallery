package com.example.gallery_test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

@SuppressWarnings("deprecation")
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;
    public ViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> listFragment){
        super(fragmentManager);
        this.listFragment = listFragment;
    }


    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}
