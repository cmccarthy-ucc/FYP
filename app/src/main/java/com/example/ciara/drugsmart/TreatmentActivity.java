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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_ID;
import static com.example.ciara.drugsmart.GroupActivity.INDIVIDUAL_ID;
import static com.example.ciara.drugsmart.GroupActivity.INDIVIDUAL_TAG;

public class TreatmentActivity extends AppCompatActivity {

    TextView animalTag;
    TextView treatmentDate;
    EditText treatmentDosage;
    EditText treatmentNotes;
    EditText treatmentAdmin;
    Spinner treatmentDrug;
    Button addTreatment;
    TextView animalID;
    Spinner drugSpinner;

    FirebaseAuth auth;

    Long timeStamp;
    Calendar cal = Calendar.getInstance();

    List<Drug> drugList;
    DatabaseReference fDatabaseRoot;

    DatabaseReference databaseTreatment;

    //https://www.youtube.com/watch?v=hwe1abDO2Ag
    private static final String TAG = "MainActivity";
    private TextView vaccinationDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

        animalTag = findViewById(R.id.textViewAnimalTag);
        treatmentDate = findViewById(R.id.treatmentDate);
        treatmentDosage = findViewById(R.id.textViewDosage);
        treatmentDrug = findViewById(R.id.spinnerDrug);
        treatmentNotes = findViewById(R.id.editTextNotes);
        treatmentAdmin = findViewById(R.id.editAdmin);
        addTreatment = findViewById(R.id.buttonAddTreatment);
        animalID = findViewById(R.id.textViewAnimalID);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String individualIDText = extras.getString(GroupActivity.INDIVIDUAL_ID);
        final String individualTagText = extras.getString(GroupActivity.INDIVIDUAL_TAG);

        animalID.setText(individualIDText);
        animalTag.setText(individualTagText);

        databaseTreatment = FirebaseDatabase.getInstance().getReference("treatments").child(intent.getStringExtra(GroupActivity.INDIVIDUAL_ID));

        addTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calling the addVaccination method to add vaccination record to the database
                addTreatment();

                Intent intent = new Intent(TreatmentActivity.this, IndividualDetails.class);

            }
        });

        treatmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        TreatmentActivity.this,
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
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
                //Date date = new Date( dayOfMonth+ "/" + month + "/" + year);
//                dateVaccination = date;
//                vaccinationDate.setText(dateFormat.format(date));
                String date = dayOfMonth + "/" + month + "/" + year;
                treatmentDate.setText(date);
                cal.set(year, month, dayOfMonth);
                timeStamp = cal.getTimeInMillis();
            }
        };
        fDatabaseRoot = FirebaseDatabase.getInstance().getReference("drugs");
        fDatabaseRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> drugs = new ArrayList<String>();

                for (DataSnapshot drugSnapshot: dataSnapshot.getChildren()) {
                    String drugName = drugSnapshot.child("name").getValue(String.class);
                    drugs.add(drugName);
                }
                 drugSpinner = (Spinner) findViewById(R.id.spinnerDrug);
                ArrayAdapter<String> drugsAdapter = new ArrayAdapter<String>(TreatmentActivity.this, android.R.layout.simple_spinner_item, drugs);
                drugsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                drugSpinner.setAdapter(drugsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
                        Toast.makeText(TreatmentActivity.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(TreatmentActivity.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(TreatmentActivity.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(TreatmentActivity.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(TreatmentActivity.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(TreatmentActivity.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(TreatmentActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(TreatmentActivity.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(TreatmentActivity.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(TreatmentActivity.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drug:
                        Toast.makeText(TreatmentActivity.this, "Drugs Available", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(TreatmentActivity.this, ActivityToDoList.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(TreatmentActivity.this, Login.class));
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void addTreatment(){
        String tag = animalTag.getText().toString().trim();
        String drug = drugSpinner.getSelectedItem().toString();
        String date = treatmentDate.getText().toString().trim();
        String admin = treatmentAdmin.getText().toString().trim();
        String dosage = treatmentDosage.getText().toString().trim();
        String notes = treatmentNotes.getText().toString().trim();
        //String individualID = animalID.getText().toString().trim();
        Long timeStamp1 = timeStamp;

        if (TextUtils.isEmpty(date)) {
            Toast.makeText(this,"Please select a date", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(dosage)) {
            Toast.makeText(this, "Please enter a dosage amount", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(admin)) {
            Toast.makeText(this, "Please enter the treatment administrator", Toast.LENGTH_LONG).show();
        }


        else{
            String id = databaseTreatment.push().getKey();

            Treatments treatment = new Treatments(tag, id, drug, admin, dosage, date, notes, timeStamp1 );

            databaseTreatment.child(id).setValue(treatment);

            Toast.makeText(this, "Vaccination added", Toast.LENGTH_LONG).show(); }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
