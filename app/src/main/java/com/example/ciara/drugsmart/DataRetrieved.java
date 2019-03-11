package com.example.ciara.drugsmart;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataRetrieved extends AppCompatActivity {

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
        setContentView(R.layout.activity_data_retrieved);

        listView = findViewById(R.id.list_item);

////https://www.youtube.com/watch?v=wSCIuIbS-nk
        databaseReference = FirebaseDatabase.getInstance().getReference("animals");
        animalList = new ArrayList<>();

//Transferring data from selected list view item to other activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String tempListValue =(listView.getItemAtPosition(position).toString());
                        //.get(position).toString();
                TextView animalID = view.findViewById(R.id.animalID);
                String animalIDText = (String) animalID.getText();

                TextView animalGender = (TextView) view.findViewById(R.id.gender);
                String animalGenderText = (String) animalGender.getText();


                TextView animalDOB = (TextView) view.findViewById(R.id.DOB);
                String animalDOBText = (String) animalDOB.getText();

                TextView source = (TextView) view.findViewById(R.id.source);
                String sourceText = (String) source.getText();

                TextView animalBreed = (TextView) view.findViewById(R.id.breed);
                String animalBreedText = (String) animalBreed.getText();

                TextView animalTag = (TextView) view.findViewById(R.id.tag);
                String animalTagText = (String) animalTag.getText();

                //Multiple Values
                Intent intent = new Intent(DataRetrieved.this, ViewAnimalDetails.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_ID",animalIDText);
                extras.putString("EXTRA_DOB",animalDOBText);
                extras.putString("EXTRA_GENDER",animalGenderText);
                extras.putString("EXTRA_SOURCE",sourceText);
                extras.putString("EXTRA_BREED", animalBreedText);
                extras.putString("EXTRA_TAG", animalTagText);
                intent.putExtras(extras);
                startActivity(intent);


            }
        }); //List view item click ends

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
//                        Toast.makeText(DataRetrieved.this, "Animals",Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(DataRetrieved.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
                    case R.id.vaccinations:
                        Toast.makeText(DataRetrieved.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(DataRetrieved.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(DataRetrieved.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(DataRetrieved.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(DataRetrieved.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(DataRetrieved.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(DataRetrieved.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(DataRetrieved.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(DataRetrieved.this, "Drugs", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(DataRetrieved.this, AddDrug.class);
                        startActivity(intentDrug);

                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(DataRetrieved.this, Login.class));
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
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot animalSnapshot : dataSnapshot.getChildren()){
                    Animal animal = animalSnapshot.getValue(Animal.class);
                    animalList.add(animal);
                }

                AnimalInfoAdapter animalInfoAdapter = new AnimalInfoAdapter(DataRetrieved.this, animalList);
                listView.setAdapter(animalInfoAdapter);

            }

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
