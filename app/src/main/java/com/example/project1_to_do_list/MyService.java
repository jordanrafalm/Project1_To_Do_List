package com.example.project1_to_do_list;

import static android.app.PendingIntent.getActivity;
import static android.content.Intent.getIntent;
import static com.example.project1_to_do_list.FileHelper.FILENAME;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import androidx.annotation.Nullable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
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
        try {
            String itemName;
            itemName = Intent.getIntent("itemName").toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        FileHelper.writeData(itemList, getApplicationContext());
        return super.onStartCommand(intent, flags, startId);

        }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
