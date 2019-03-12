package com.fyp.ciara.drugsmart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class TreatmentDetails extends AppCompatActivity {



    TextView textViewTagNumber;
    TextView textViewDate;
    TextView textViewAdmin;
    TextView textViewDosage;
    TextView textViewDrug;
    TextView textViewNotes;

    FirebaseAuth auth;
    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);

        textViewTagNumber = findViewById(R.id.textViewTagNumber);
        textViewDate = findViewById(R.id.treatmentDate);
        textViewAdmin =findViewById(R.id.textViewTreatmentAdmin);
        textViewDrug = findViewById(R.id.textViewTreatmentDrug);
        textViewDosage = findViewById(R.id.textViewTreatmentDosage);
        textViewNotes = findViewById(R.id.textViewTreatmentNotes);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String tagText = extras.getString(IndividualDetails.TREATMENT_ANIMAL_TAG);
        String treatmentDateText = extras.getString(IndividualDetails.TREATMENT_DATE);
        String treatmentAdminText = extras.getString(IndividualDetails.TREATMENT_ADMIN);
        String treatmentDosageText = extras.getString(IndividualDetails.TREATMENT_DOSAGE);
        String treatmentDrugText = extras.getString(IndividualDetails.TREATMENT_DRUG);
        String treatmentNotesText = extras.getString(IndividualDetails.TREATMENT_NOTES);

        textViewTagNumber.setText(tagText);
        textViewNotes.setText(treatmentNotesText);
        textViewDosage.setText(treatmentDosageText);
        textViewDrug.setText(treatmentDrugText);
        textViewAdmin.setText(treatmentAdminText);
        textViewDate.setText(treatmentDateText);

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
//                    case R.id.animals:
//                        Toast.makeText(TreatmentDetails.this, "Animals",Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(TreatmentDetails.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
                    case R.id.vaccinations:
                        Toast.makeText(TreatmentDetails.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(TreatmentDetails.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(TreatmentDetails.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(TreatmentDetails.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(TreatmentDetails.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(TreatmentDetails.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(TreatmentDetails.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(TreatmentDetails.this, ActivityToDoDoses.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drug:
                        Toast.makeText(TreatmentDetails.this, "Drugs Available", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(TreatmentDetails.this, DrugActivity.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(TreatmentDetails.this, Login.class));
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;

            }

        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
