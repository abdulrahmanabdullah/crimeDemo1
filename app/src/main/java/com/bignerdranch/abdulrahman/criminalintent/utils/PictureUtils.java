package com.bignerdranch.abdulrahman.criminalintent.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by abdulrahman on 4/25/18.
 */

public class PictureUtils {

    public static Bitmap getScaleBitMap(String path , int destWidth,int destHeight){
       // Read in the dimensions of the image on disk ..
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        // Figure out how much to scale down by
        int inSamplesize = 1 ;
        if (srcHeight > destHeight || srcWidth>destWidth){
            float heightScale = srcHeight / destHeight;
            float widthScale = srcWidth / destWidth ;
            inSamplesize = Math.round(heightScale>widthScale ? heightScale:widthScale);
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSamplesize;
        // Read in and create final bitmap
        return BitmapFactory.decodeFile(path,options);
    }

    // this method to select a device size ..
    public static Bitmap getScaleBitmap(String path , Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return  getScaleBitMap(path,size.x,size.y);
    }
}
