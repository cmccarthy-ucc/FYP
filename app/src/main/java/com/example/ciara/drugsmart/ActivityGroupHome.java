//package com.example.ciara.drugsmart;
//
//import android.app.Activity;
//import android.app.ActivityGroup;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.NavigationView;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//public class ActivityGroupHome extends AppCompatActivity {
//
//
//    Button addGroup;
//    Button viewGroups;
//    Button addGroupVaccination;
//    Button viewGroupVaccination;
//    Button searchGroups;
//    Button addGroupDose;
//    Button viewDoses;
//
//    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
//    private DrawerLayout dl;
//    private ActionBarDrawerToggle t;
//    private NavigationView nv;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_group_home);
//
//        addGroup = (Button) findViewById(R.id.btnAddGroup);
//        addGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivityGroupHome.this, ActivityAddGroup.class);
//                startActivity(intent);
//            }
//        });
//
//        viewGroups = (Button) findViewById(R.id.btnViewGroups);
//        viewGroups.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivityGroupHome.this, ActivityAllGroups.class);
//                startActivity(intent);
//            }
//        });
//        viewGroupVaccination = (Button) findViewById(R.id.btnViewGroupVaccinations);
//        viewGroupVaccination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        addGroupVaccination = (Button) findViewById(R.id.btnAddGroupVaccinations);
//        addGroupVaccination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivityGroupHome.this, ActivityAllGroups.class);
//                startActivity(intent);
//            }
//        });
//
//        searchGroups = (Button) findViewById(R.id.buttonSearchGroups);
//        searchGroups.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivityGroupHome.this, SearchDatabase.class);
//                startActivity(intent);
//            }
//        });
//
//        addGroupDose = (Button) findViewById(R.id.buttonAddGroupDose);
//        addGroupDose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivityGroupHome.this, ActivityAllGroups.class);
//                startActivity(intent);
//            }
//        });
//
//        viewDoses = (Button) findViewById(R.id.buttonViewDoses);
//        viewDoses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//
//        //Navigation Drawer
//        dl = (DrawerLayout)findViewById(R.id.activity_main);
//        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
//
//        dl.addDrawerListener(t);
//        t.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        nv = (NavigationView)findViewById(R.id.nv);
//        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//
//                switch (id) {
//                    case R.id.animals:
//                        //Toast.makeText(ActivityOptions.this, "Animals",Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(ActivityGroupHome.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
//                    case R.id.vaccinations:
//                        Toast.makeText(ActivityGroupHome.this, "Medical Records", Toast.LENGTH_SHORT).show();
//                        Intent intentVaccination = new Intent(ActivityGroupHome.this, ActivityMedicalRecords2.class);
//                        startActivity(intentVaccination);
//                        break;
//                    case R.id.groups:
//                        Toast.makeText(ActivityGroupHome.this, "Groups", Toast.LENGTH_SHORT).show();
//                        Intent intentGroups = new Intent(ActivityGroupHome.this, ActivityAllGroups.class);
//                        startActivity(intentGroups);
//                        break;
//                    case R.id.home:
//                        Toast.makeText(ActivityGroupHome.this,"Home", Toast.LENGTH_SHORT).show();
//                        Intent intentHome = new Intent(ActivityGroupHome.this, WelcomeActivity.class);
//                        startActivity(intentHome);
//                        break;
//                    case R.id.todo:
//                    Toast.makeText(ActivityGroupHome.this,"To-Do List", Toast.LENGTH_SHORT).show();
//                    Intent intentToDo = new Intent(ActivityGroupHome.this, ActivityToDoList.class);
//                    startActivity(intentToDo);
//                    break;
//                    case R.id.drugs:
//                        Toast.makeText(ActivityGroupHome.this, "Drugs", Toast.LENGTH_SHORT).show();
//                        Intent intentDrug = new Intent(ActivityGroupHome.this, AddDrug.class);
//                        startActivity(intentDrug);
//                        break;
//                    default:
//                        return true;
//                }
//                return true;
//
//            }
//
//        });
//
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(t.onOptionsItemSelected(item))
//            return true;
//
//        return super.onOptionsItemSelected(item);
//    }
//}
