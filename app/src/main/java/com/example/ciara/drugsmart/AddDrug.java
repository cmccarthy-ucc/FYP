package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddDrug extends AppCompatActivity {

    EditText editTextName;
    Button add;
    DatabaseReference fDatabaseRoot;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    FirebaseAuth auth;

    ListView listView;
    List<Drug> drugList;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        databaseReference = FirebaseDatabase.getInstance().getReference("drugs");
        listView = findViewById(R.id.listViewDrugsAvailable);
        drugList = new ArrayList<>();

        editTextName = findViewById(R.id.editTextName);
        add = findViewById(R.id.buttonAdd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrug();
            }
        });
        fDatabaseRoot = FirebaseDatabase.getInstance().getReference("drugs");
        fDatabaseRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> drugs = new ArrayList<String>();

                for (DataSnapshot drugSnapshot: dataSnapshot.getChildren()) {
                    String drugName = drugSnapshot.child("name").getValue(String.class);
                    drugs.add(drugName);
                }
                Spinner drugSpinner = (Spinner) findViewById(R.id.spinnerDrugs);
                ArrayAdapter<String> drugsAdapter = new ArrayAdapter<String>(AddDrug.this, android.R.layout.simple_spinner_item, drugs);
                drugsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                drugSpinner.setAdapter(drugsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
//                        Toast.makeText(AddDrug.this, "Animals", Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(AddDrug.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
                    case R.id.vaccinations:
                        Toast.makeText(AddDrug.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(AddDrug.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(AddDrug.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(AddDrug.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(AddDrug.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(AddDrug.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(AddDrug.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(AddDrug.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(AddDrug.this, "Drugs", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(AddDrug.this, AddDrug.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(AddDrug.this, Login.class));
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

    }

    private void addDrug(){
        String name = editTextName.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this,"Please enter name", Toast.LENGTH_LONG).show();
        }
        else {

            String id = databaseReference.push().getKey();
            Drug drug = new Drug(name);

            databaseReference.child(id).setValue(drug);

            Toast.makeText(this, "Drug added", Toast.LENGTH_LONG).show();

            finish();
            startActivity(getIntent());
        }
    }

    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Drug drugs = postSnapshot.getValue(Drug.class);
                    //adding artist to the list
                    drugList.add(drugs);

                    //creating adapter
                    DrugsList drugAdapter = new DrugsList(AddDrug.this, drugList);
                    //attaching adapter to the listview
                    listView.setAdapter(drugAdapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

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
