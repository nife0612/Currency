package com.example.currency.list.network;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Data {
    private static final String DATA_FILE_NAME = "data_file";
    private static final String DEFAULT_JSON_FILE_NAME = "Default.json";

    public static String getData(Context context, String urlLink) {

        try{
            URL url = new URL(urlLink);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            String data;
            if (NetworkCheck.isNetworkAvailable(context))
                data = readDataFile(urlConnection.getInputStream());
            else if (isFileExist(context, DATA_FILE_NAME)) {
                urlConnection.disconnect();
                return readSavedDataFile(context, DATA_FILE_NAME);
            } else
                data = readDataFile(context.getAssets().open(DEFAULT_JSON_FILE_NAME));

            write(context, data);
            urlConnection.disconnect();

            return  data;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void write(Context context, String data){
        try(FileOutputStream fos = context.openFileOutput(DATA_FILE_NAME, Context.MODE_PRIVATE)){
            fos.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readDataFile(InputStream inputStream){
        try {
            StringBuilder stringBuilder = new StringBuilder();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int tmp = inputStreamReader.read();
            while (tmp != -1) {
                stringBuilder.append((char) tmp);
                tmp = inputStreamReader.read();
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readSavedDataFile(Context context, String filename){
        try {
            StringBuilder stringBuilder = new StringBuilder();

            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isFileExist(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }
}
