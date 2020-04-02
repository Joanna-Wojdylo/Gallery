package com.example.gallery_test;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.database.Cursor;
import android.provider.MediaStore;


public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private Cursor cursor;
    int columnIndex;

    /**public int[] imageArray = {
            R.drawable.bal, R.drawable.myszojelen, R.drawable.plakat,
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
            R.drawable.pic10, R.drawable.pic10, R.drawable.pic12,
            R.drawable.bal, R.drawable.myszojelen, R.drawable.plakat,
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
            R.drawable.pic10, R.drawable.pic10, R.drawable.pic12};
*/
    public ImageAdapter(Context mContext, Cursor cursor, int columnIndex) {
        this.mContext = mContext;
        this.cursor = cursor;
        this.columnIndex = columnIndex;
    }

    @Override
    public int getCount() {
        return cursor.getCount(); //<------ TU POTRZEBUJE KURSORA Z MAIN -------------------------
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            //Move cursor to current position
            cursor.moveToPosition(position);
            //Get the current value for the requested column
            int imageID = cursor.getInt(columnIndex);
            //Set the content of the image based on the provider URI
            imageView.setImageURI(Uri.withAppendedPath(
                    MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(5, 5, 5, 5);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
        }
        else {
            imageView = (ImageView) convertView;
        }
        return imageView;
        }

        /**
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340,350));

        return imageView;
         */
    }

