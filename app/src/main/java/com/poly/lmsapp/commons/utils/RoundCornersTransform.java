package com.poly.lmsapp.commons.utils;

import android.graphics.*;
import com.squareup.picasso.Transformation;

class RoundCornersTransform implements Transformation {
    private float radiusInPx;

    public RoundCornersTransform(float radiusInPx) {
        this.radiusInPx = radiusInPx;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap bitmap = Bitmap.createBitmap(source.getWidth(), source.getWidth(), source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint =new Paint(Paint.ANTI_ALIAS_FLAG );
        BitmapShader shader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        RectF rect = new RectF(0.0f, 0.0f, source.getWidth(), source.getWidth());
        canvas.drawRoundRect(rect, radiusInPx, radiusInPx, paint);
        source.recycle();

        return bitmap;
    }

    @Override
    public String key() {
        return "round_corners";
    }
}