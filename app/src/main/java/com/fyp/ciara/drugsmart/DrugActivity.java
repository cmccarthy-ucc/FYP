package com.fyp.ciara.drugsmart;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DrugActivity extends AppCompatActivity {

    ListView listView;
     DatabaseReference databaseReference;
     List<Drug> drugsList;

    FirebaseAuth auth;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);

        listView = findViewById(R.id.listViewDrugs);

        databaseReference = FirebaseDatabase.getInstance().getReference("drugs");

        drugsList = new ArrayList<>();

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
//                        Toast.makeText(DrugActivity.this, "Animals", Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(DrugActivity.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
                    case R.id.vaccinations:
                        Toast.makeText(DrugActivity.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(DrugActivity.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(DrugActivity.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(DrugActivity.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(DrugActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(DrugActivity.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(DrugActivity.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(DrugActivity.this, ActivityToDoDoses.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(DrugActivity.this, "Drugs", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(DrugActivity.this, AddDrug.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(DrugActivity.this, Login.class));
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;

            }

        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addDrugDialog();
              Intent intent = new Intent(DrugActivity.this, AddDrug.class);
              startActivity(intent);

            }
        });


    }
        protected void onStart(){
            super.onStart();
////            databaseReference.addValueEventListener(new ValueEventListener() {
////                @Override
////                public void onDataChange(DataSnapshot dataSnapshot) {
////
////                    //clearing the previous artist list
////                    drugsList.clear();
////
////                    //iterating through all the nodes
////                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
////                        //getting artist
////                        Drugs drugs = postSnapshot.getValue(Drugs.class);
////                        //adding artist to the list
////                        drugsList.add(drugs);
////
////                    //creating adapter
////                    DrugsList drugAdapter = new DrugsList(DrugActivity.this, drugsList);
////                    //attaching adapter to the listview
////                    listView.setAdapter(drugAdapter);
////                }}
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
        }

        public void addDrugDialog(){

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.popup_add_drug, null);
            dialogBuilder.setView(dialogView);

            dialogBuilder.setTitle("Add New Drug");
            final AlertDialog b = dialogBuilder.create();
            b.show();

            final EditText name = dialogView.findViewById(R.id.editTextName);
            final Button add;
            final String nameText = name.getText().toString().trim();

            add = dialogView.findViewById(R.id.buttonAdd);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   String name = nameText;

                   if (!TextUtils.isEmpty(name)){

                       String id = databaseReference.push().getKey();
                       Drug drug = new Drug(name);

                       databaseReference.child(id).setValue(drug);



                       b.dismiss();
                   }
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
