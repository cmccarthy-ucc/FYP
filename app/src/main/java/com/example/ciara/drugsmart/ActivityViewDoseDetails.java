package com.example.ciara.drugsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityViewDoseDetails extends AppCompatActivity {
    //Declarations

    TextView doseGroupNumber;
    TextView doseID;
    TextView doseDate;
    TextView doseDrug;
    TextView doseDosage;
    TextView doseAdmin;
    TextView doseType;
    TextView allDosed;
    TextView doseNotes;

    FirebaseAuth auth;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_view_dose_details);


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

         /*//value passed from one activity to another
        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
        animalID.setText(TempHolder);
*/
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

//        /*//value passed from one activity to another
//        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
//        animalID.setText(TempHolder);
//*/
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        String doseGroupNumberText = extras.getString("EXTRA_GROUP_NUMBER");
//        String doseIDText = extras.getString("EXTRA_ID");
//        String doseDateText = extras.getString("EXTRA_DATE");
//        String doseAdminText = extras.getString("EXTRA_ADMIN");
//        String doseDrugText = extras.getString("EXTRA_DRUG");
//        String doseDosageText = extras.getString("EXTRA_DOSAGE");
//        String doseTypeText = extras.getString("EXTRA_TYPE");
//        String allDosedText = extras.getString("EXTRA_ALL_DOSED");
//        String doseNotesText = extras.getString("EXTRA_NOTES");


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
                        Toast.makeText(ActivityViewDoseDetails.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ActivityViewDoseDetails.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityViewDoseDetails.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityViewDoseDetails.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityViewDoseDetails.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityViewDoseDetails.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityViewDoseDetails.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityViewDoseDetails.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityViewDoseDetails.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityViewDoseDetails.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(ActivityViewDoseDetails.this, "Drugs", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(ActivityViewDoseDetails.this, AddDrug.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(ActivityViewDoseDetails.this, Login.class));
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
