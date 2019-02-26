package com.example.ciara.drugsmart;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {

    EditText editTextBreed;
    EditText editTextSource;
    EditText editTextTagNumber;
    Button buttonAdd;
    Spinner spinnerGender;
    private Button buttonCamera;

    //https://www.youtube.com/watch?v=hwe1abDO2Ag
    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //Variables for retrieving data from database
    DatabaseReference databaseAnimal;
    private FloatingActionButton floatingActionButton;


    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //Select date from a calender
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
                dialog.getDatePicker().setMaxDate(new Date().getTime());
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

        //Retrieving data from database
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
        editTextTagNumber = (EditText) findViewById(R.id.editTextTagNumber);
        editTextTagNumber.requestFocus();
        buttonAdd = (Button) findViewById(R.id.buttonAddAnimal);
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);

        //Calling the method to add animal to the database
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


        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.animals:
                        Toast.makeText(MainActivity.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(MainActivity.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(MainActivity.this, "Vaccinations", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(MainActivity.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(MainActivity.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(MainActivity.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(MainActivity.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(MainActivity.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(MainActivity.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });


    }

    private void addAnimal(){
        String breed = editTextBreed.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();
        String source = editTextSource.getText().toString().trim();
        String DOB = mDisplayDate.getText().toString().trim();
        String tag = editTextTagNumber.getText().toString().trim();

        if(TextUtils.isEmpty(breed)){
            Toast.makeText(this, "You should enter a breed", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(source)){
            Toast.makeText(this, "You should enter a source", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(DOB)){
            Toast.makeText(this, "You should enter a Date of Birth", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(tag)){
            Toast.makeText(this, "You should enter a Tag Number", Toast.LENGTH_LONG).show();
        }
        else if (tag.length() < 6){
            Toast.makeText(this, "Please enter valid tag number", Toast.LENGTH_LONG).show();

        }
        else{
            String id = databaseAnimal.push().getKey();

            Animal animal = new Animal(id, breed, gender, source, DOB, tag);

            databaseAnimal.child(id).setValue(animal);

            Toast.makeText(this, "Animal added", Toast.LENGTH_LONG).show();
        }

        /*if(!TextUtils.isEmpty(breed)){

            String id = databaseAnimal.push().getKey();

            Animal animal = new Animal(id, breed, gender, source, DOB, tag);

            databaseAnimal.child(id).setValue(animal);

            Toast.makeText(this, "Animal added", Toast.LENGTH_LONG).show();

        }

        else{
            Toast.makeText(this, "You should enter a breed", Toast.LENGTH_LONG).show();
        }*/
    }

    public void openOCRCamera(){
        Intent intent = new Intent(MainActivity.this, TextRecognition.class);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
