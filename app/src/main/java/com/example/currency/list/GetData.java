package com.example.currency.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.NetworkCheck;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetData extends AsyncTask<String, String, String> {

    @SuppressLint("StaticFieldLeak")
    private final RecyclerView view;
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private String URL_LINK;
    private final List<ValuteData> dataList = new ArrayList<>();

    public GetData(String url_link, Context context, RecyclerView view) {
        URL_LINK = url_link;
        this.context = context;
        this.view = view;
    }

    public String getURL_LINK() {
        return URL_LINK;
    }

    public void setURL_LINK(String URL_LINK) {
        this.URL_LINK = URL_LINK;
    }

    @Override
    protected String doInBackground(String... strings) {

        StringBuilder current = new StringBuilder();

        try {
            URL url = new URL(URL_LINK) ;
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream;
            if(NetworkCheck.isNetworkAvailable(context))
                 inputStream = urlConnection.getInputStream();
            else{
                inputStream = context.getAssets().open("Default.json");
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int tmp = inputStreamReader.read();
            while (tmp != -1){
                current.append((char) tmp);
                tmp = inputStreamReader.read();
            }

            urlConnection.disconnect();

            return current.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject headObject = new JSONObject(s);
            JSONObject valuteObject = headObject.getJSONObject("Valute");


            Iterator<String> keys = valuteObject.keys();
            while(keys.hasNext()){
                String key = keys.next();

                JSONObject tmp = valuteObject.getJSONObject(key);

                ValuteData data = new ValuteData(
                        tmp.getString("ID"),
                        tmp.getString("NumCode"),
                        tmp.getString("CharCode"),
                        tmp.getString("Nominal"),
                        tmp.getString("Name"),
                        tmp.getString("Value"),
                        tmp.getString("Previous")
                );
                dataList.add(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PutDataIntoRecycleView();
    }

    private void PutDataIntoRecycleView() {
        MyAdapter adapter = new MyAdapter(context, dataList);
        view.setLayoutManager(new LinearLayoutManager(context));
        view.setAdapter(adapter);
    }
}
