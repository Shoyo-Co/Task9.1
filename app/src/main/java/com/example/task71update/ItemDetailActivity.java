package com.example.task71update;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        databaseHelper = new DatabaseHelper(this);

        itemId = getIntent().getIntExtra("itemId", -1);
        if (itemId == -1) {
            Toast.makeText(this, "Error: no item ID provided", Toast.LENGTH_SHORT).show();
            finish();
        }

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_1 + " = " + itemId, null);

        if (cursor.moveToFirst()) {
            String postType = cursor.getString(1);
            String name = cursor.getString(2);
            String phone = cursor.getString(3);
            String description = cursor.getString(4);
            String date = cursor.getString(5);
            String location = cursor.getString(6);

            ((TextView) findViewById(R.id.postType)).setText(postType);
            ((TextView) findViewById(R.id.name)).setText(name);
            ((TextView) findViewById(R.id.phone)).setText(phone);
            ((TextView) findViewById(R.id.description)).setText(description);
            ((TextView) findViewById(R.id.date)).setText(date);
            ((TextView) findViewById(R.id.location)).setText(location);
        } else {
            Toast.makeText(this, "Error: item not found", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();

        Button btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_1 + " = ?", new String[]{String.valueOf(itemId)});
                db.close();

                Toast.makeText(ItemDetailActivity.this, "Item removed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
