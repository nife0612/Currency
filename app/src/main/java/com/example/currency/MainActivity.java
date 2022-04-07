package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String URL_LINK = "https://www.cbr-xml-daily.ru/daily_json.js";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_view);


        GetData getData = new GetData(URL_LINK, this, recyclerView);
        getData.execute();
    }
}