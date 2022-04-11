package com.example.currency;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Modules {
    @SuppressLint("DefaultLocale")
    public static String myFormat(Double val){
        return String.format("%.2f", val);
    }

    public static String calculateDifference(Double previous, Double value){ return myFormat(value - previous); }

    public static void textColoring(TextView holder, String text){
        double d_text = Double.parseDouble(text);
        holder.setText(text);
        if(d_text > 0.0){

            holder.setTextColor(Color.GREEN);
        }
        else if(d_text < 0.0){
            holder.setTextColor(Color.RED);
        }
    }


}
