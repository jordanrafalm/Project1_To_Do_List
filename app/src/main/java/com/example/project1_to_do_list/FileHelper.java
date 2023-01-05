package com.example.project1_to_do_list;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {

    public static final String FILENAME = "listinfo.dat";


    public static void writeData(ArrayList<String> item, Context context)
    {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,Context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(item);
            oas.close();
          //  Toast.makeText(context, "file saving",
            //        LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readData(Context context)
    {
        ArrayList<String> itemList;
        itemList = null;

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemList = (ArrayList<String>) ois.readObject();

        } catch (FileNotFoundException e) {
            itemList = new ArrayList<>();
            e.printStackTrace();
            //zadanie 2
            Toast.makeText(context, "File does not exist\n",
                    LENGTH_LONG).show();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemList;


    }
}
