package com.example.ciara.drugsmart;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class TodoDoses extends AppCompatActivity {

    TextView doseGroupNumber;
    TextView doseID;
    TextView doseDate;
    TextView doseDrug;
    TextView doseDosage;
    TextView doseAdmin;
    TextView doseType;
    TextView allDosed;
    TextView doseNotes;
    Button btnUpdate;
    Boolean boolean1 = true;
    Long timeStamp;

    FirebaseAuth auth;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_doses);



        doseGroupNumber
                = (TextView)findViewById(R.id.doseGroupNumber);
        doseID
                = (TextView)findViewById(R.id.doseID);
        doseDate = (TextView)findViewById(R.id.treatmentDate);
        doseDrug = (TextView)findViewById(R.id.doseDrug);
        doseAdmin = (TextView)findViewById(R.id.doseAdmin);
        doseDosage = (TextView)findViewById(R.id.doseDosage);
        doseType = findViewById(R.id.doseType);
        allDosed = findViewById(R.id.allDosed);
        doseNotes = findViewById(R.id.doseNotes);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String doseGroupNumberText = extras.getString(GroupActivity.DOSE_GROUP_NUMBER);
        String doseIDText = extras.getString(GroupActivity.DOSE_ID);
        String doseDateText = extras.getString(GroupActivity.DOSE_DATE);
        String doseAdminText = extras.getString(GroupActivity.DOSE_ADMIN);
        String doseDrugText = extras.getString(GroupActivity.DOSE_DRUG);
        String doseDosageText = extras.getString(GroupActivity.DOSE_DOSAGE);
        String doseTypeText = extras.getString(GroupActivity.DOSE_TYPE);
        String allDosedText = extras.getString(GroupActivity.ALL_DOSED);
        String doseNotesText = extras.getString(GroupActivity.DOSE_NOTES);
        doseGroupNumber.setText(doseGroupNumberText);
        doseID.setText(doseIDText);
        doseDate.setText(doseDateText);
        doseDrug.setText(doseDrugText);
        doseDosage.setText(doseDosageText);
        doseAdmin.setText(doseAdminText);
        doseType.setText(doseTypeText);
        doseNotes.setText(doseNotesText);
        allDosed.setText(allDosedText);

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
                        Toast.makeText(TodoDoses.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(TodoDoses.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(TodoDoses.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(TodoDoses.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(TodoDoses.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(TodoDoses.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(TodoDoses.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(TodoDoses.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(TodoDoses.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(TodoDoses.this, ActivityToDoDoses.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(TodoDoses.this, "Drugs", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(TodoDoses.this, AddDrug.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(TodoDoses.this, Login.class));
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
        timeStamp = System.currentTimeMillis()/1000;
        btnUpdate = findViewById(R.id.btnUpdate);

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent (TodoDoses.this, ActivityAllGroups.class);
                    startActivity(intent1);
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
