package com.example.ciara.drugsmart;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_ID;
import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_NUMBER;
import static com.example.ciara.drugsmart.GroupActivity.GROUP_VACCINATION_ID;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_ADMIN;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_DATE;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_DOSAGE;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_DRUG;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_GROUP_NOTES;

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
    Button btnUpdate;
    Long timeStamp = 2147483649L;
    Boolean boolean1;

    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;
    String userID;

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
        setContentView(R.layout.activity_group_vaccination_details);


        timeStamp = System.currentTimeMillis()/1000;

        vaccinationGroupNumber = findViewById(R.id.vaccinationGroupNumber);
        vaccinationID = findViewById(R.id.vaccinationID);
        vaccinationDate = findViewById(R.id.treatmentDate);
        vaccinationAdmin = findViewById(R.id.vaccinationAdmin);
        vaccinationDosage = findViewById(R.id.vaccinationDosage);
        vaccinationDrug = findViewById(R.id.vaccinationDrug);
        vaccinationNotes = findViewById(R.id.vaccinationNotes);
        allVaccination = findViewById(R.id.allVaccinated);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userID = auth.getUid();

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


        boolean1 = Boolean.valueOf(allVaccinatedText);

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
                        Intent intentHome = new Intent(GroupVaccinationDetailsActivity.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(GroupVaccinationDetailsActivity.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drug:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "Drugs Available", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(GroupVaccinationDetailsActivity.this, ActivityToDoList.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(GroupVaccinationDetailsActivity.this, Login.class));
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;

            }

        });
        btnUpdate = findViewById(R.id.btnUpdate);
        if (boolean1.equals(true)){
            btnUpdate.setVisibility(View.INVISIBLE);
        }
        else if (boolean1.equals(false)){
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean1 = true;
//                    DatabaseReference dR = FirebaseDatabase.getInstance().getReference("groupVaccinations");
//                    DatabaseReference drGroup = dR.child("groupID");
//                    DatabaseReference drVaccination = drGroup.child("vaccinationID");

                    updateGroupVaccination();
                    //drVaccination.child("allVaccinated").setValue(boolean1);
                }

            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
//    String vaccinationGroupNumber, String vaccinationID, String vaccinationDrug,
//    String vaccinationAdmin, String vaccinationDosage, String vaccinationDate,
//    String vaccinationNotes,      Long timeStamp
//

    private boolean updateGroupVaccination( ) {

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
        String userID1 = userID;



        String vaccinationID = GroupActivity.VACCINATION_ID;
        String vaccinationDrug = GroupActivity.VACCINATION_DRUG;
        String vaccinationAdmin = GroupActivity.VACCINATION_ADMIN;
        String vaccinationDate = GroupActivity.VACCINATION_DATE;
        String vaccinationDosage = GroupActivity.VACCINATION_DOSAGE;
        String vaccinationNote = GroupActivity.VACCINATION_GROUP_NOTES;
        String vaccinationGroupNumber = GroupActivity.VACCINATION_GROUP_NUMBER;

        Long time = timeStamp;
        Boolean allDone = true;



        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(todayDate);

        String updateNote = "Marked as completed on " + todayString + ",  Previous Note: " + vaccinationNotesText;

        //getting the specified vaccination reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("groupVaccinations");
        DatabaseReference drGroup = dR.child(vaccinationGroupIDText);
        DatabaseReference drVaccination = drGroup.child(vaccinationIDText);

        //drVaccination.child("allVaccinated").setValue(allVaccinated);
        //updating vaccination
        GroupVaccination group = new GroupVaccination(vaccinationGroupNumberText, vaccinationIDText, vaccinationDrugText, vaccinationAdminText, vaccinationDosageText,
                                vaccinationDateText, updateNote, allDone,time, userID1);
        drVaccination.setValue(group);
        Toast.makeText(getApplicationContext(), "Vaccination Updated", Toast.LENGTH_LONG).show();


        return true;
        //updating artist


        //drVaccination.child("allVaccinated").setValue(true);
    }

}