package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityAllIndividuals extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<Animal> animalList;

    FirebaseAuth auth;


    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_individuals);


        listView = findViewById(R.id.list_item);

////https://www.youtube.com/watch?v=wSCIuIbS-nk
        databaseReference = FirebaseDatabase.getInstance().getReference("animal");
        animalList = new ArrayList<>();

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
                    case R.id.animals:
                        Toast.makeText(ActivityAllIndividuals.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ActivityAllIndividuals.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityAllIndividuals.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityAllIndividuals.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityAllIndividuals.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityAllIndividuals.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityAllIndividuals.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityAllIndividuals.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityAllIndividuals.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityAllIndividuals.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(ActivityAllIndividuals.this, "Drugs", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(ActivityAllIndividuals.this, AddDrug.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(ActivityAllIndividuals.this, Login.class));
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
    protected void onStart(){
        super.onStart();
        animalList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot animalGroupSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot animalSnapshot : animalGroupSnapshot.getChildren()) {
                        Animal animal = animalSnapshot.getValue(Animal.class);
                        animalList.add(animal);
                    }
                    AnimalInfoAdapter animalInfoAdapter = new AnimalInfoAdapter(ActivityAllIndividuals.this, animalList);
                    listView.setAdapter(animalInfoAdapter);
                }
            }

//             groupVaccinations.clear();
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()) {
//                    for (DataSnapshot vaccinationSnapshot : groupVaccinationSnapshot.getChildren()) {
//                        GroupVaccination groupVaccination = vaccinationSnapshot.getValue(GroupVaccination.class);
//                        groupVaccinations.add(groupVaccination);
//                    }
//
//                AllGroupVaccinationList groupVaccinationInfoAdapter = new AllGroupVaccinationList(ActivityAllGroupVaccinations.this, groupVaccinations);
//                listView.setAdapter(groupVaccinationInfoAdapter);
//            }}
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
