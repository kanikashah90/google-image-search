package com.kanikash.gridimagesearch.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kanikash.gridimagesearch.R;
import com.kanikash.gridimagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kanikash on 1/31/15.
 */
public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {

    public ImageResultsAdapter(Context context, List<ImageResult> objects) {
        super(context, R.layout.item_image_result, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageResult imageInfo = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
        }
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);

        DisplayMetrics displayMetrics =getContext().getResources().getDisplayMetrics();

        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int width = (int) (dpWidth/3) - (2 * 5);
        //Log.i("INFO", ((Integer) width).toString());
        //ivImage.setMinimumWidth(width);
        //ivImage.setMaxWidth(width);

        // clear up the image view resource
        ivImage.setImageResource(0);
        Picasso.with(getContext()).load(imageInfo.thumbUrl).into(ivImage);
        return convertView;
    }
}
