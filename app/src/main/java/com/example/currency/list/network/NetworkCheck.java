package com.example.currency.list.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class NetworkCheck {
    public static Boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(Build.VERSION.SDK_INT >= 23){
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if(activeNetwork == null) return false;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if(networkCapabilities == null) return false;

            if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) return true;
            else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) return true;
            else return false;

        }
        else{
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo == null) return false;
            return networkInfo.isConnected();
        }
    }

    public static void connectCheck(Context context){
        if(!NetworkCheck.isNetworkAvailable(context))
            Toast.makeText(context, "Ошибка соединения", Toast.LENGTH_SHORT).show();
    }
}


