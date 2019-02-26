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

public class ViewVaccinationDetails extends AppCompatActivity {
    //Declarations

    TextView vaccinationAnimalTag;
    TextView vaccinationID;
    TextView vaccinationDate;
    TextView vaccinationDrug;
    TextView vaccinationDosage;
    TextView vaccinationAdmin;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vaccination_details);


        vaccinationAnimalTag
                = (TextView)findViewById(R.id.vaccinationGroupNumber);
        vaccinationID
                = (TextView)findViewById(R.id.vaccinationID);
        vaccinationDate = (TextView)findViewById(R.id.doseDate);
        vaccinationDrug = (TextView)findViewById(R.id.vaccinationDrug);
        vaccinationAdmin = (TextView)findViewById(R.id.vaccinationAdmin);
        vaccinationDosage = (TextView)findViewById(R.id.vaccinationDosage);

        /*//value passed from one activity to another
        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
        animalID.setText(TempHolder);
*/
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String vaccinationAnimalTagText = extras.getString("EXTRA_ANIMAL_TAG");
        String vaccinationIDText = extras.getString("EXTRA_ID");
        String vaccinationDateText = extras.getString("EXTRA_DATE");
        String vaccinationAdminText = extras.getString("EXTRA_ADMIN");
        String vaccinationDrugText = extras.getString("EXTRA_DRUG");
        String vaccinationDosageText = extras.getString("EXTRA_DOSAGE");


        vaccinationAnimalTag.setText(vaccinationAnimalTagText);
        vaccinationID.setText(vaccinationIDText);
        vaccinationDate.setText(vaccinationDateText);
        vaccinationDrug.setText(vaccinationDrugText);
        vaccinationDosage.setText(vaccinationDosageText);
        vaccinationAdmin.setText(vaccinationAdminText);

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
                        Toast.makeText(ViewVaccinationDetails.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ViewVaccinationDetails.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ViewVaccinationDetails.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ViewVaccinationDetails.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ViewVaccinationDetails.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ViewVaccinationDetails.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ViewVaccinationDetails.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ViewVaccinationDetails.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ViewVaccinationDetails.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ViewVaccinationDetails.this, ActivityToDoList.class);
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
