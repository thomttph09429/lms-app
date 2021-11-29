package com.poly.lmsapp.commons.utils;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.poly.lmsapp.R;
import com.squareup.picasso.Picasso;

public class RenderImage {
   @SuppressLint("ResourceType")
   public static void loadImageNetwork(String path, ImageView imageView){
       CircularProgressDrawable progress = new CircularProgressDrawable(imageView.getContext());
       progress.setColorSchemeColors(R.color.primary_color, R.color.colorPrimaryDark, R.color.colorAccent);
       progress.setCenterRadius(30f);
       progress.setStrokeWidth(4f);
       progress.start();
       if(path == null || path.isEmpty()) path = "https://abc.com";
        Picasso.get().load(Utils.concatPath(path)).placeholder(progress).error(R.drawable.ic_404).into(imageView);
    }
}
