package com.example.project1_to_do_list;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyService extends Service {
    private ArrayList<String> itemList;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onCreate() {
                        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Bundle bundle = intent.getExtras();

            String itemName = "";
            System.out.println("sprawdzczenie wartosci itemName: " + itemName);

            if(bundle != null){
                itemName = bundle.getString("key_name");
                System.out.println("sprawdzczenie wartosci po pobraniu z intenta itemName: " + itemName);
//zapisanie itemName do SharedPreferences
                editor.putString("key_name",itemName);
                editor.apply();
            }

        FileHelper.writeData(itemList, getApplicationContext());
        return super.onStartCommand(intent, flags, startId);

        }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
