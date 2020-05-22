package com.puxiansheng.www.common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class BgColorDrawable {

   // 四个角的x,y半径
    private float[] radiusArray = { 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f };
    private Paint bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Bitmap makeRoundRectFrame(int w, int h,int color) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, w, h), radiusArray, Path.Direction.CW);
        Paint bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapPaint.setColor(color); // 颜色随意，不要有透明度。
        c.drawPath(path, bitmapPaint);
        return bm;
    }
}
