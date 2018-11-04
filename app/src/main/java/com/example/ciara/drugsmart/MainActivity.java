package com.example.ciara.drugsmart;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
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

public class MainActivity extends AppCompatActivity {

    EditText editTextBreed;
    EditText editTextSource;
    Button buttonAdd;
    Spinner spinnerGender;
    private Button buttonCamera;
    private Button buttonVaccination;



    //https://www.youtube.com/watch?v=hwe1abDO2Ag

    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    DatabaseReference databaseAnimal;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://www.youtube.com/watch?v=hwe1abDO2Ag
        mDisplayDate = (TextView)findViewById(R.id.animalDOB);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener, year,month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1 ;
                Log.d(TAG, "OnDateSet: date: " + dayOfMonth + "/" + month + "/" + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);


            }
        };



        databaseAnimal = FirebaseDatabase.getInstance().getReference("animals");

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, DataRetrieved.class);
                startActivity(next);
            }
        });

        editTextBreed = (EditText) findViewById(R.id.editTextBreed);
        editTextSource = (EditText) findViewById(R.id.editTextSource);
        buttonAdd = (Button) findViewById(R.id.buttonAddAnimal);
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnimal();
            }
        });


        buttonCamera = (Button)findViewById(R.id.buttonUseCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOCRCamera();
            }
        });

        buttonVaccination = (Button)findViewById(R.id.buttonVaccination);
        buttonVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddVaccination.class);

                        startActivity(intent);
            }
        });

    }

    private void addAnimal(){
        String breed = editTextBreed.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();
        String source = editTextSource.getText().toString().trim();
        String DOB = mDisplayDate.getText().toString().trim();

        if(!TextUtils.isEmpty(breed)){

            String id = databaseAnimal.push().getKey();

            Animal animal = new Animal(id, breed, gender, source, DOB);

            databaseAnimal.child(id).setValue(animal);

            Toast.makeText(this, "Animal added", Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this, "You should enter a breed", Toast.LENGTH_LONG).show();
        }
    }

    public void openOCRCamera(){
        Intent intent = new Intent(MainActivity.this, TextRecognition.class);
        startActivity(intent);

    }
}
