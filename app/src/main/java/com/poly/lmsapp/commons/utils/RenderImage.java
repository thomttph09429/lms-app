package com.poly.lmsapp.commons.utils;

import android.widget.ImageView;
import com.poly.lmsapp.R;
import com.squareup.picasso.Picasso;

public class RenderImage {
   public static void loadImageNetwork(String path, ImageView imageView){
        Picasso.get().load(path).error(R.drawable.ic_404).into(imageView);
    }
}
