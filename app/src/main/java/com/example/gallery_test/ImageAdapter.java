package com.example.gallery_test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> imageArray;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
        imageArray = loadImagesFromDevice(mContext);
    }

    @Override
    public int getCount() {
        return imageArray.size();
    }

    @Override
    public Object getItem(int position) {
        return imageArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;// = new ImageView(mContext);

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));

        } else {
            imageView = (ImageView) convertView;
        }

        Glide.with(mContext).load(imageArray.get(position))
                .placeholder(R.drawable.ic_launcher_foreground).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);

        return imageView;
    }

    @SuppressLint("Recycle")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private ArrayList<String> loadImagesFromDevice(Context activity){

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

    }
}

