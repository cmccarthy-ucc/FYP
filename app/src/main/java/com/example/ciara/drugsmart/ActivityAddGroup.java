package com.example.ciara.drugsmart;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class ActivityAddGroup extends AppCompatActivity {

    EditText editTextGroupID;
    EditText editTextBreed;
    EditText editTextSource;
    Button btnAddGroup;
    Spinner spinnerAnimalType;


    //Variables for retrieving data from database
    DatabaseReference databaseAnimal;

    //https://www.youtube.com/watch?v=hwe1abDO2Ag
    private static final String TAG = "ActivityAddGroup";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        //Select date from a calender
        //https://www.youtube.com/watch?v=hwe1abDO2Ag
        mDisplayDate = (TextView) findViewById(R.id.textViewDOB);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ActivityAddGroup.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "OnDateSet: date: " + dayOfMonth + "/" + month + "/" + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        //Retrieving data from database
        databaseAnimal = FirebaseDatabase.getInstance().getReference("groups");

        editTextBreed = findViewById(R.id.editTextBreed);
        editTextSource = (EditText) findViewById(R.id.editTextSource);
        editTextGroupID = (EditText) findViewById(R.id.editTextGroupNo);
        editTextGroupID.requestFocus();
        btnAddGroup = (Button) findViewById(R.id.btnAddGroup);
        spinnerAnimalType = (Spinner) findViewById(R.id.spinnerAnimalType);

        //Calling the method to add animal to the database
        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroup();
            }
        });
    }

    private void addGroup() {
        String breed = editTextBreed.getText().toString().trim();
        String animalType = spinnerAnimalType.getSelectedItem().toString();
        String source = editTextSource.getText().toString().trim();
        String DOB = mDisplayDate.getText().toString().trim();
        String groupId = editTextGroupID.getText().toString().trim();

        if (TextUtils.isEmpty(breed)) {
            Toast.makeText(this, "You should enter a breed", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(animalType)) {
            Toast.makeText(this, "You select an Animal Type", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(source)) {
            Toast.makeText(this, "You should enter a Source", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(groupId)) {
            Toast.makeText(this, "You should enter a Group Number", Toast.LENGTH_LONG).show();
        } else if (groupId.length() < 2) {
            Toast.makeText(this, "A Group Number must be greater than 2 digits", Toast.LENGTH_LONG).show();

        } else {
            //String id = databaseAnimal.push().getKey();

            Group group = new Group(groupId, animalType, breed, source, DOB);

            databaseAnimal.child(groupId).setValue(group);

            Toast.makeText(this, "Group added", Toast.LENGTH_LONG).show();
        }
    }
}
