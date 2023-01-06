package com.example.project1_to_do_list;


import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private RecyclerView recyclerView;
    //private RecyclerAdapter recyclerAdapter;
   // private ImageView imageView;
    EditText item;
    Button add;
    ListView listView;
    private ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listView = findViewById(R.id.list2);
        itemList = FileHelper.readData(this);
     //   recyclerView = findViewById(R.id.recyclerView);
       // recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1
                , android.R.id.text1, itemList);
        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(v -> {
            String itemName = item.getText().toString();
            itemList.add(itemName);
            item.setText("");
            FileHelper.writeData(itemList, getApplicationContext());
            arrayAdapter.notifyDataSetChanged();

            //  recyclerAdapter.notifyDataSetChanged();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {

            AlertDialog.Builder AlertDialogFragment = new AlertDialog.Builder(MainActivity.this);
            AlertDialogFragment.setView(R.layout.fragment_blank);  //ustawienie fragmentu jako widok
            AlertDialogFragment.setMessage("Do you want delete?");
            AlertDialogFragment.setCancelable(false);
            AlertDialogFragment.setNegativeButton("No", (dialog, which) -> dialog.cancel());
            AlertDialogFragment.setPositiveButton("Yes", (dialog, which) -> {
                itemList.remove(position);
                arrayAdapter.notifyDataSetChanged();
                FileHelper.writeData(itemList, getApplicationContext());
            });

            AlertDialog alertDialogFragment = AlertDialogFragment.create();
            alertDialogFragment.show();

            //recyclerAdapter = new RecyclerAdapter(itemList,MainActivity.this, imageView);
            // recyclerView.setAdapter(recyclerAdapter);
        });

    }
}













