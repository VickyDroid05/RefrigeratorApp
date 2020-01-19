package com.cooler.refconnect.refrigeconnect.utils;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;

/**
 * Created by Vigneshwaran G on 19/01/20.
 */
public class FileUtils {

    public static String readJsonfromAssets(Context context, int rawResourse) {
        //1 Takes your JSON file from the raw folder
        InputStream XmlFileInputStream = context.getResources().openRawResource(rawResourse);
        //2 This reads your JSON file
        return readTextFile(XmlFileInputStream);
    }

    //3 Converts the input stream into a String
    private static String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Log.e(TAG, "readTextFile: " + e.getMessage());
        }
        return outputStream.toString();
    }

}
