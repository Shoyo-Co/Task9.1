package com.example.task71update;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class CreateActivity extends AppCompatActivity {

    private static final int PLACE_PICKER_REQUEST = 1;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        databaseHelper = new DatabaseHelper(this);

        final RadioGroup radioGroupPostType = findViewById(R.id.radioGroupPostType);
        final EditText name = findViewById(R.id.name);
        final EditText phone = findViewById(R.id.phone);
        final EditText description = findViewById(R.id.description);
        final EditText date = findViewById(R.id.date);
        final EditText location = findViewById(R.id.location);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postType = ((RadioButton) findViewById(radioGroupPostType.getCheckedRadioButtonId())).getText().toString();
                String nameStr = name.getText().toString();
                String phoneStr = phone.getText().toString();
                String descriptionStr = description.getText().toString();
                String dateStr = date.getText().toString();
                String locationStr = location.getText().toString();

                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.COL_2, postType);
                contentValues.put(DatabaseHelper.COL_3, nameStr);
                contentValues.put(DatabaseHelper.COL_4, phoneStr);
                contentValues.put(DatabaseHelper.COL_5, descriptionStr);
                contentValues.put(DatabaseHelper.COL_6, dateStr);
                contentValues.put(DatabaseHelper.COL_7, locationStr);

                long result = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

                if (result == -1) {
                    Toast.makeText(CreateActivity.this, "Error: item not saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateActivity.this, "Item saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        Button btnPickPlace = findViewById(R.id.btnPickPlace);
        btnPickPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(CreateActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException |
                         GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                EditText location = findViewById(R.id.location);
                location.setText(place.getName());
            }
        }
    }
}

