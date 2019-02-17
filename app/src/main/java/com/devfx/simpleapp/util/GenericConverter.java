package com.devfx.simpleapp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class GenericConverter {

    public static String getBase64(Bitmap image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encoded = Base64.encodeToString(b, Base64.NO_WRAP);
        return encoded;
    }

    public static Bitmap getBitmap( String base){
        byte[] decodeString = Base64.decode(base, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
    }
}
