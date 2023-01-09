package com.example.project1_to_do_list;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemListener{

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ImageView imageView;
    private WebView webView;
    private List<String> mUrlList = Arrays.asList("https://wallbeing.com/2272-thickbox_default/grafika-joker.jpg", "https://safetyheads.com/wp-content/themes/safetyheads/images/logo.svg", "https://upload.wikimedia.org/wikipedia/commons/3/31/Most_Solidarno%C5%9Bci_w_P%C5%82ocku_4040.jpg");
    EditText item;
    Button add;
    ListView listView;
    private ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listView = findViewById(R.id.list2);
        itemList = FileHelper.readData(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1
                , android.R.id.text1, itemList);
        listView.setAdapter(arrayAdapter);
        FileHelper.writeData(itemList, getApplicationContext());

        add.setOnClickListener(v -> {
            String itemName = item.getText().toString();
            itemList.add(itemName);
            itemList.add(""); //dodanie Itemu aby wyświetlić na nim pożniej WebView, bez tego webView zasłaniało zadanie
            item.setText("");
            // FileHelper.writeData(itemList, getApplicationContext());
            // service to wykonuje


//zadanie 6
//wysyła się intent który uruchamia service przekając do niego itemname, nastepnie service odapala Filehelper do zapisania w pliku
        Intent i = new Intent(getApplicationContext(),MyService.class);
        i.putExtra("key_name",itemName);
        startService(i);

            arrayAdapter.notifyDataSetChanged();
            recyclerAdapter.notifyDataSetChanged();
        });



// Zadanie 4.
//Do wyświetlania elementów proszę użyć RecyclerView zamiat ListView.
        recyclerAdapter = new RecyclerAdapter(itemList, MainActivity.this, imageView, this, webView);
        recyclerView.setAdapter(recyclerAdapter);
    }


//Zadanie 4
//Po kliknięciu w ikonę dopiero wtedy
//powinien pojawić się komunikat o usunięciu danego elementu.
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onItemClick(int position) {
        itemList.get(position);
        AlertDialog.Builder AlertDialogFragment = new AlertDialog.Builder(MainActivity.this);
        AlertDialogFragment.setView(R.layout.fragment_blank);  //ustawienie fragmentu jako widok
        AlertDialogFragment.setMessage("Do you want delete?");
        AlertDialogFragment.setCancelable(false);
        AlertDialogFragment.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        AlertDialogFragment.setPositiveButton("Yes", (dialog, which) -> {


            itemList.remove(position);

            arrayAdapter.notifyDataSetChanged();
            FileHelper.writeData(itemList, getApplicationContext());
            recyclerAdapter.notifyDataSetChanged();
        });

        AlertDialog alertDialogFragment = AlertDialogFragment.create();
        alertDialogFragment.show();


    }
}













