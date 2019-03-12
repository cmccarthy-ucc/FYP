package com.fyp.ciara.drugsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityVaccinationHome extends AppCompatActivity {

    Button addVaccination;
    Button viewVaccinations;
    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_home);

        viewVaccinations = (Button) findViewById(R.id.btnViewVaccination);
        viewVaccinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityVaccinationHome.this, ActivityAllVaccinations.class);
                startActivity(intent);
            }
        });

        addVaccination = (Button) findViewById(R.id.btnAddGroup);
        addVaccination.setOnClickListener((new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ActivityVaccinationHome.this, DataRetrieved.class);
            startActivity(intent);
        }
    }));
            //Navigation Drawer start

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
//                        Toast.makeText(ActivityVaccinationHome.this, "Animals",Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(ActivityVaccinationHome.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityVaccinationHome.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityVaccinationHome.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityVaccinationHome.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityVaccinationHome.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityVaccinationHome.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityVaccinationHome.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityVaccinationHome.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityVaccinationHome.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(ActivityVaccinationHome.this, "Drugs", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(ActivityVaccinationHome.this, AddDrug.class);
                        startActivity(intentDrug);
                        break;
                    default:
                        return true;
                }
                return true;

            }

        });


    }

    //Method for navigation drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
