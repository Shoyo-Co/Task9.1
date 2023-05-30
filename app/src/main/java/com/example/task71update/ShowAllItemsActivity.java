package com.example.task71update;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowAllItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Item> itemList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_items);

        recyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this);
        itemList = new ArrayList<>();

        loadDataFromDatabase();

        adapter = new ItemAdapter(itemList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void loadDataFromDatabase() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String postType = cursor.getString(1);
                String name = cursor.getString(2);
                String phone = cursor.getString(3);
                String description = cursor.getString(4);
                String date = cursor.getString(5);
                String location = cursor.getString(6);

                itemList.add(new Item(id, postType, name, phone, description, date, location));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }
}
