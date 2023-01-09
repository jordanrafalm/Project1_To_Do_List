package com.example.project1_to_do_list;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

public class MyService extends Service {
    private ArrayList<String> itemList;
    private int id;


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
                id = bundle.getInt("key_id");
                System.out.println("sprawdzczenie wartosci po pobraniu z intenta itemName: " + itemName);

//zadanie 8
//zapisanie json do SharedPreferences
                Date nowDate = new Date();
                Gson gson = new Gson();
                Record record = new Record(id, nowDate, itemName,true);
                String json = gson.toJson(record);
                editor.putString("key_name_json",json);
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
