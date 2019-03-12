package com.fyp.ciara.drugsmart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static com.fyp.ciara.drugsmart.ActivityAddGroup.GROUP_NUMBER;
import static com.fyp.ciara.drugsmart.GroupActivity.VACCINATION_DATE;

public class ToDoVaccination extends AppCompatActivity {

    TextView groupID;
    TextView vaccinationGroupNumber;
    TextView vaccinationID;
    TextView vaccinationDate;
    TextView vaccinationDosage;
    TextView vaccinationAdmin;
    TextView allVaccination;
    TextView vaccinationDrug;
    TextView vaccinationNotes;
    Button btnUpdate;

    FirebaseAuth auth;

    CheckBox checkBoxComplete;
    Boolean booleanCompleted = true;

    RadioButton radioButtonYes;
    RadioButton radioButtonNo;
    RadioGroup radioGroupAllVaccinated;
    Boolean allVaccinatedRadio = true;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_vaccination);

        vaccinationGroupNumber = findViewById(R.id.vaccinationGroupNumber);
        vaccinationID = findViewById(R.id.vaccinationID);
        vaccinationDate = findViewById(R.id.treatmentDate);
        vaccinationAdmin = findViewById(R.id.vaccinationAdmin);
        vaccinationDosage = findViewById(R.id.vaccinationDosage);
        vaccinationDrug = findViewById(R.id.vaccinationDrug);
        vaccinationNotes = findViewById(R.id.vaccinationNotes);
        allVaccination = findViewById(R.id.allVaccinated);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String vaccinationGroupIDText = extras.getString("GROUP_ID");
        final String vaccinationGroupNumberText = extras.getString(GROUP_NUMBER);
        final String vaccinationIDText = extras.getString(GroupActivity.VACCINATION_ID);
        final String vaccinationDateText = extras.getString(VACCINATION_DATE);
        final String vaccinationAdminText = extras.getString(GroupActivity.VACCINATION_ADMIN);
        final String vaccinationDosageText = extras.getString(GroupActivity.VACCINATION_DOSAGE);
        final String vaccinationDrugText = extras.getString(GroupActivity.VACCINATION_DRUG);
        final String vaccinationNotesText = extras.getString(GroupActivity.VACCINATION_GROUP_NOTES);
        final String allVaccinatedText = extras.getString(GroupActivity.ALL_VACCINATED);


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
//                    case R.id.animals:
//                        Toast.makeText(ToDoVaccination.this, "Animals", Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(ToDoVaccination.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ToDoVaccination.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ToDoVaccination.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ToDoVaccination.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ToDoVaccination.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ToDoVaccination.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ToDoVaccination.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ToDoVaccination.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ToDoVaccination.this, ActivityToDoDoses.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drug:
                        Toast.makeText(ToDoVaccination.this, "Drugs Available", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(ToDoVaccination.this, DrugActivity.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(ToDoVaccination.this, Login.class));
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;

            }

        });
        btnUpdate = findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(ToDoVaccination.this, ActivityAllGroups.class);

                   startActivity(intent);

                }

            });
        }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
