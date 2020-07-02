package com.puxiansheng.www.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class  BitMapUtil {

    private Bitmap bitmap;

    public Bitmap returnBitMap(final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    options.inSampleSize = calculateInSampleSize(options,200,200);
                    bitmap = BitmapFactory.decodeStream(is,null,options);
                    Log.d("shopImg","  is = "+is.toString().length());
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }

    public int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        int width=options.outWidth;
        int height=options.outHeight;
        int inSampleSize=1;
        if(width>reqWidth||height>reqHeight){
            inSampleSize++;
            int widthRatio=reqWidth/width;
            int heightRatio=reqHeight/height;
            int ratio=Math.min(widthRatio,heightRatio);
            while(ratio>inSampleSize){
                inSampleSize*=2;
            }

        }
        return inSampleSize;
    }




}

