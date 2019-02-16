package com.example.ciara.drugsmart;

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

public class ActivityIndividualHome extends AppCompatActivity {


    Button addAnimal;
    Button viewAnimals;
    Button addVaccination;
    Button viewVaccination;
    Button search;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_home);

        addAnimal = (Button) findViewById(R.id.btnAddAnimal);
        addAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityIndividualHome.this, MainActivity.class);
                startActivity(intent);
            }
        });

        viewAnimals = (Button) findViewById(R.id.btnViewAnimals);
        viewAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityIndividualHome.this, DataRetrieved.class);
                startActivity(intent);
            }
        });
        viewVaccination = (Button) findViewById(R.id.btnViewTreatments);
        viewVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityIndividualHome.this, ActivityAllVaccinations.class);
                startActivity(intent);
            }
        });
        addVaccination = (Button) findViewById(R.id.btnAddTreatment);
        addVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityIndividualHome.this, DataRetrieved.class);
                startActivity(intent);
            }
        });

        search = (Button) findViewById(R.id.buttonSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityIndividualHome.this, SearchDatabase.class);
                startActivity(intent);
            }
        });


        //Navigation Drawer
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
                        //Toast.makeText(ActivityOptions.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ActivityIndividualHome.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityIndividualHome.this, "Vaccinations", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityIndividualHome.this, ActivityVaccinationHome.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityIndividualHome.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityIndividualHome.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityIndividualHome.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityIndividualHome.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
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
