package com.example.ciara.drugsmart;

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

import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_ID;
import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_NUMBER;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_ID;

public class GroupVaccinationDetailsActivity extends AppCompatActivity {

    TextView groupID;
    TextView vaccinationGroupNumber;
    TextView vaccinationID;
    TextView vaccinationDate;
    TextView vaccinationDosage;
    TextView vaccinationAdmin;
    TextView allVaccination;
    TextView vaccinationDrug;
    TextView vaccinationNotes;


    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_vaccination_details);


        vaccinationGroupNumber = findViewById(R.id.vaccinationGroupNumber);
        vaccinationID = findViewById(R.id.vaccinationID);
        vaccinationDate = findViewById(R.id.vaccinationDate);
        vaccinationAdmin = findViewById(R.id.vaccinationAdmin);
        vaccinationDosage = findViewById(R.id.vaccinationDosage);
        vaccinationDrug = findViewById(R.id.vaccinationDrug);
        vaccinationNotes = findViewById(R.id.vaccinationNotes);
        allVaccination = findViewById(R.id.allVaccinated);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String vaccinationGroupIDText = extras.getString(ActivityAddGroup.GROUP_ID);
        final String vaccinationGroupNumberText = extras.getString(GROUP_NUMBER);
        String vaccinationIDText = extras.getString(GroupActivity.VACCINATION_ID);
        String vaccinationDateText = extras.getString(GroupActivity.VACCINATION_DATE);
        String vaccinationAdminText = extras.getString(GroupActivity.VACCINATION_ADMIN);
        String vaccinationDosageText = extras.getString(GroupActivity.VACCINATION_DOSAGE);
        String vaccinationDrugText = extras.getString(GroupActivity.VACCINATION_DRUG);
        String vaccinationNotesText = extras.getString(GroupActivity.VACCINATION_GROUP_NOTES);
        String allVaccinatedText = extras.getString(GroupActivity.ALL_VACCINATED);


//        groupID.setText(vaccinationGroupIDText);
        vaccinationID.setText(vaccinationIDText);
        vaccinationDate.setText(vaccinationDateText);
        vaccinationAdmin.setText(vaccinationAdminText);
        vaccinationDosage.setText(vaccinationDosageText);
        vaccinationNotes.setText(vaccinationNotesText);
        vaccinationDrug.setText(vaccinationDrugText);
        allVaccination.setText(allVaccinatedText);
        vaccinationGroupNumber.setText(vaccinationGroupNumberText);


        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.animals:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "Animals", Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(GroupVaccinationDetailsActivity.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(GroupVaccinationDetailsActivity.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(GroupVaccinationDetailsActivity.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(GroupVaccinationDetailsActivity.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(GroupVaccinationDetailsActivity.this, ActivityToDoList.class);
                        startActivity(intentToDo);
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
