package com.example.ciara.drugsmart;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class ActivityAddGroup extends AppCompatActivity {

    EditText editTextGroupNo;
    EditText editTextBreed;
    EditText editTextSource;
    Button btnAddGroup;
    Spinner spinnerAnimalType;
    EditText editTextNotes;
    RadioButton radioButtonYes;
    RadioButton radioButtonNo;
    Boolean allVaccinated = false;



    //Variables for retrieving data from database
    DatabaseReference databaseAnimal;

    //https://www.youtube.com/watch?v=hwe1abDO2Ag
    private static final String TAG = "ActivityAddGroup";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_group);

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
        editTextGroupNo = (EditText) findViewById(R.id.editTextGroupNo);
        editTextGroupNo.requestFocus();
        editTextNotes = (EditText) findViewById(R.id.textViewNotes);
        btnAddGroup = (Button) findViewById(R.id.btnAddGroup);
        spinnerAnimalType = (Spinner) findViewById(R.id.spinnerAnimalType);
        radioButtonYes = (RadioButton) findViewById(R.id.radioButtonYes);
        radioButtonNo = (RadioButton) findViewById(R.id.radioButtonNo);

        //Calling the method to add animal to the database
        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroup();
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
                        Toast.makeText(ActivityAddGroup.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ActivityAddGroup.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityAddGroup.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityAddGroup.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityAddGroup.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityAddGroup.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityAddGroup.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityAddGroup.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityAddGroup.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityAddGroup.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    default:
                        return true;
                }
            return true;
            }
        });

        //https://stackoverflow.com/questions/8323778/how-to-set-onclicklistener-on-a-radiobutton-in-android
        // **Origional source but updated code**

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.AllVaccinated);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               if (radioButtonNo.isChecked()){
                   allVaccinated = false;
                }
                else if (radioButtonYes.isChecked()){
                   allVaccinated = true;
               }
            }
        });


    }


//    public void onRadioButtonClicked(View view) {
////            // Is the button now checked?
//            boolean checked = ((RadioButton) view).isChecked();
//
//            // Check which radio button was clicked
//            switch(view.getId()) {
//                case R.id.radioButtonNo:
//                    if (checked)
//                        allVaccinated = true;
//                    break;
//                case R.id.radioButtonYes:
//                    if (checked)
//                       allVaccinated = false;
//                        break;
//            }
//        }

    private void addGroup() {
        String breed = editTextBreed.getText().toString().trim();
        String animalType = spinnerAnimalType.getSelectedItem().toString();
        String source = editTextSource.getText().toString().trim();
        String DOB = mDisplayDate.getText().toString().trim();
        String notes = editTextNotes.getText().toString().trim();
        Boolean vaccinated = allVaccinated;

//

        //String DOB = mDisplayDate.getText();
        String groupNo = editTextGroupNo.getText().toString().trim();

        if (TextUtils.isEmpty(breed)) {
            Toast.makeText(this,"You should enter a breed", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(animalType)) {
            Toast.makeText(this, "You select an Animal Type", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(source)) {
            Toast.makeText(this, "You should enter a Source", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(groupNo)) {
            Toast.makeText(this, "You should enter a Group Number", Toast.LENGTH_LONG).show();
        } else if (groupNo.length() < 2) {
            Toast.makeText(this, "A Group Number must be greater than 2 digits", Toast.LENGTH_LONG).show();
            }
// else if (allVaccinated = null){
//            Toast.makeText(this, "You should enter a Group Number", Toast.LENGTH_LONG).show();
//
//        }
        else {
            String id = databaseAnimal.push().getKey();

            Group group = new Group(groupNo, animalType, breed, source, DOB, id, notes, vaccinated);

            databaseAnimal.child(groupNo).setValue(group);

            Toast.makeText(this, "Group added", Toast.LENGTH_LONG).show();

            //stackoverflow.com/questions/3053761/reload-activity-in-android
            finish();
            startActivity(getIntent());
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
