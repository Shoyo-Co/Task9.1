package com.example.task71update;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCreateNewAd = findViewById(R.id.btnCreateNewAd);
        btnCreateNewAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        Button btnShowAllItems = findViewById(R.id.btnShowAllItems);
        btnShowAllItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowAllItemsActivity.class);
                startActivity(intent);
            }
        });

        Button btnShowItemsOnMap = findViewById(R.id.btnShowItemsOnMap);
        btnShowItemsOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowAllItemsOnMapActivity.class);
                startActivity(intent);
            }
        });
    }
}

