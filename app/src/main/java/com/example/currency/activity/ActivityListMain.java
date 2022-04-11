package com.example.currency.activity;

import static com.example.currency.list.network.NetworkCheck.connectCheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.currency.R;
import com.example.currency.list.network.GetData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActivityListMain extends AppCompatActivity {

    private static final String URL_LINK = "https://www.cbr-xml-daily.ru/daily_json.js";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycle_view);


        GetData getData = new GetData(URL_LINK, this, recyclerView);
        getData.execute();

        connectCheck(this);
    }



}
