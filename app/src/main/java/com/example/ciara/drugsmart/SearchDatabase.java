package com.example.ciara.drugsmart;
//https://www.youtube.com/watch?v=b_tz8kbFUsU&fbclid=IwAR1miglP_HLi82-Fu4YVt98aJsbOC9nPfVkVVD5powiXardkPxzIOwB6wHg

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchDatabase extends AppCompatActivity {

    Button buttonSearch;
    EditText editTextSearch;
    ListView resultsListView;
    private DatabaseReference mVaccinationDatabase;
    List<Vaccination> vaccinationList;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_database);

        mVaccinationDatabase = FirebaseDatabase.getInstance().getReference("vaccination");

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        resultsListView = (ListView) findViewById(R.id.resultsListView);
        vaccinationList = new ArrayList<>();


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vaccinationList.clear();//clear RecyclerView
                //get text from search field
                String vaccinationInfo = editTextSearch.getText().toString();
                firebaseVaccinationSearch(vaccinationInfo);
            }
        });


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
                        Toast.makeText(SearchDatabase.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(SearchDatabase.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(SearchDatabase.this, "Medial Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(SearchDatabase.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(SearchDatabase.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(SearchDatabase.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(SearchDatabase.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(SearchDatabase.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(SearchDatabase.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(SearchDatabase.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drug:
                        Toast.makeText(SearchDatabase.this, "Drugs Available", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(SearchDatabase.this, ActivityToDoList.class);
                        startActivity(intentDrug);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void firebaseVaccinationSearch(String vaccinationInfo) {
        //query to search database based on text in textbox - made case insensitive
        //code from https://stackoverflow.com/questions/54155576/android-firebase-search-query-not-working-properly

        //query to search database based on text in textbox - made case insensitive
        //code from https://stackoverflow.com/questions/54155576/android-firebase-search-query-not-working-properly
        Query vaccinationSearchQuery = mVaccinationDatabase.orderByChild("vaccinationAdmin").startAt(vaccinationInfo.toLowerCase()).endAt(vaccinationInfo.toLowerCase() + "\uf8ff");
        vaccinationSearchQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot vaccinationSnapshot : dataSnapshot.getChildren()) {
                    Vaccination vaccination = vaccinationSnapshot.getValue(Vaccination.class);
                    vaccinationList.add(vaccination);
                }
                VaccinationInfoAdapter vaccinationInfoAdapter = new VaccinationInfoAdapter(SearchDatabase.this, vaccinationList);
                resultsListView.setAdapter(vaccinationInfoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

        //FirebaseRecyclerAdapter provided by the Firebase UI then you pass in the model class and the viewHolder
//        FirebaseRecyclerAdapter<Vaccination, vaccinationViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Vaccination, vaccinationViewHolder>() {
//            @Override
//            protected void onBindViewHolder(@NonNull vaccinationViewHolder holder, int position, @NonNull Vaccination model) {
//
//            }
//
//            @NonNull
//            @Override
//            public vaccinationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//                return null;
//            }
//        };

//                Vaccination.class, R.layout.list_layout,
//                vaccinationViewHolder.class,
//                mVaccinationDatabase
//
//        ) {
//            @Override
//            protected void populateViewHolder(vaccinationViewHolder viewHolder, Vaccination model, int position) {
//
//            }};

//
//

    protected void onStart(){
        super.onStart();
        mVaccinationDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot vaccinationSnapshot : dataSnapshot.getChildren()){
                    Vaccination vaccination = vaccinationSnapshot.getValue(Vaccination.class);
                    vaccinationList.add(vaccination);
                }

                VaccinationInfoAdapter vaccinationInfoAdapter = new VaccinationInfoAdapter(SearchDatabase.this, vaccinationList);
                resultsListView.setAdapter(vaccinationInfoAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //View Holder Class
    //Help initialise the content from the single layout
    public class vaccinationViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public vaccinationViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
