package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityToDoDetails extends AppCompatActivity {

    //Declarations

    TextView vaccinationGroupNumber;
    TextView vaccinationGroupID;
    TextView vaccinationDate;
    TextView vaccinationDrug;
    TextView vaccinationDosage;
    TextView vaccinationAdmin;
    TextView vaccinationNotes;
    TextView allVaccinatedTV;
    TextView vaccinationID;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_details);

        vaccinationGroupNumber
                = (TextView)findViewById(R.id.vaccinationGroupNumber);
        vaccinationGroupID = (TextView) findViewById(R.id.vaccinationGroupID);
        vaccinationDate = (TextView)findViewById(R.id.vaccinationDate);
        vaccinationDrug = (TextView)findViewById(R.id.vaccinationDrug);
        vaccinationAdmin = (TextView)findViewById(R.id.vaccinationAdmin);
        vaccinationDosage = (TextView)findViewById(R.id.vaccinationDosage);
        vaccinationNotes = (TextView)findViewById(R.id.vaccinationNotes);
        allVaccinatedTV = (TextView)findViewById(R.id.allVaccinated);
        vaccinationID
                = (TextView)findViewById(R.id.vaccinationID);
        /*//value passed from one activity to another
        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
        animalID.setText(TempHolder);
*/
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String vaccinationGroupNumberText = extras.getString("EXTRA_GROUP_NUMBER");
        String vaccinationGroupIDText = extras.getString("EXTRA_GROUP_ID");
        String vaccinationDateText = extras.getString("EXTRA_DATE");
        String vaccinationAdminText = extras.getString("EXTRA_ADMIN");
        String vaccinationDrugText = extras.getString("EXTRA_DRUG");
        String vaccinationDosageText = extras.getString("EXTRA_DOSAGE");
        String vaccinationNotesText = extras.getString("EXTRA_NOTES");
        String allVaccinatedText = extras.getString("EXTRA_ALL_VACCINATED");
        String vaccinationIDText = extras.getString("EXTRA_VACCINATION_ID");



        vaccinationGroupNumber.setText(vaccinationGroupNumberText);
        vaccinationGroupID.setText(vaccinationGroupIDText);
        vaccinationDate.setText(vaccinationDateText);
        vaccinationDrug.setText(vaccinationDrugText);
        vaccinationDosage.setText(vaccinationDosageText);
        vaccinationAdmin.setText(vaccinationAdminText);
        vaccinationNotes.setText(vaccinationNotesText);
        allVaccinatedTV.setText(allVaccinatedText);
        vaccinationID.setText(vaccinationIDText);

        vaccinationNotes.setMovementMethod(new ScrollingMovementMethod());


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
                        Toast.makeText(ActivityToDoDetails.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ActivityToDoDetails.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityToDoDetails.this, "Vaccinations", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityToDoDetails.this, ActivityVaccinationHome.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityToDoDetails.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityToDoDetails.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityToDoDetails.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityToDoDetails.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityToDoDetails.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityToDoDetails.this, ActivityToDoList.class);
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
