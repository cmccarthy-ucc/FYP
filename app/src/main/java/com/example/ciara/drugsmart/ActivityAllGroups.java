package com.example.ciara.drugsmart;

import android.app.ActivityGroup;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.ciara.drugsmart.ActivityAddGroup.ANIMAL_TYPE;
import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_BREED;
import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_DOB;
import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_ID;
import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_NOTES;
import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_NUMBER;
import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_SOURCE;
import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class ActivityAllGroups extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<Group> groupList;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;

    String userID;

    List<Group> groups;
    DatabaseReference databaseGroups;
    ListView listViewGroups;

    Button buttonSearch;
    EditText editTextGroupNo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_groups);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userID = auth.getUid();

//        listView = (ListView)findViewById(R.id.listViewGroups);

        databaseGroups = FirebaseDatabase.getInstance().getReference("groups");

        //list to store artists
        groups = new ArrayList<>();

        editTextGroupNo = findViewById(R.id.editTextSearchNo);
        buttonSearch = findViewById(R.id.buttonSearchNo);


        listViewGroups = findViewById(R.id.listViewGroups);
        listViewGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                Group group = groups.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), GroupActivity.class);

                //putting artist name and id to intent
                intent.putExtra(GROUP_ID, group.getGroupID());
                intent.putExtra(GROUP_DOB, group.getGroupDOB());
                intent.putExtra(GROUP_BREED, group.getGroupBreed());
                intent.putExtra(GROUP_SOURCE, group.getGroupSource());
                intent.putExtra(GROUP_NOTES, group.getGroupNo());
                intent.putExtra(ANIMAL_TYPE, group.getGroupAnimalType());
                intent.putExtra(GROUP_NUMBER, group.getGroupNo());

                //starting the activity with intent
                startActivity(intent);
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
                        Toast.makeText(ActivityAllGroups.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ActivityAllGroups.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityAllGroups.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityAllGroups.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityAllGroups.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityAllGroups.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityAllGroups.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityAllGroups.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityAllGroups.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityAllGroups.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(ActivityAllGroups.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(ActivityAllGroups.this, AddDrug.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(ActivityAllGroups.this, Login.class));
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
                Intent intent = new Intent(ActivityAllGroups.this, ActivityAddGroup.class);
                startActivity(intent);

            }});

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextGroupNo.getText().toString().trim().length() > 0){
                    groups.clear();//clear RecyclerView
                //get text from search field
                String vaccinationInfo = editTextGroupNo.getText().toString();
                firebaseVaccinationSearch(vaccinationInfo);}
                else {
                    onStart();
                }
            }
        });


           }
    protected void onStart(){
        super.onStart();
        Query orderQuery = databaseGroups.orderByChild("userID").equalTo(user.getUid());
        orderQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                groups.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    //getting artist
                    Group group = postSnapshot.getValue(Group.class);

                    //adding artist to the list
                    groups.add(group);

                }

                GroupList groupAdapter = new GroupList(ActivityAllGroups.this, groups);
                //attaching adapter to the listview
                listViewGroups.setAdapter(groupAdapter);

                //creating adapter

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

    private void firebaseVaccinationSearch(final String vaccinationInfo) {
        final String groupNo = editTextGroupNo.getText().toString().trim();
      //query to search database based on text in textbox - made case insensitive
        //code from https://stackoverflow.com/questions/54155576/android-firebase-search-query-not-working-properly
        //Query vaccinationSearchQuery = databaseGroups.orderByChild("groupNo").startAt(vaccinationInfo.toLowerCase()).endAt(vaccinationInfo.toLowerCase() + "\uf8ff");

        Query orderQuery = databaseGroups.orderByChild("userID").equalTo(user.getUid());

        orderQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot groupSnapshot : dataSnapshot.getChildren()) {
                    Group group = groupSnapshot.getValue(Group.class);

                    if(group.getGroupNo().equals(groupNo)) {
                        groups.add(group);

                    }
                   //groups.add(group);
                    GroupList groupList = new GroupList(ActivityAllGroups.this, groups);
                    listViewGroups.setAdapter(groupList);
                }
//                GroupList groupList = new GroupList(ActivityAllGroups.this, groups);
//                listViewGroups.setAdapter(groupList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        databaseGroups.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //iterating through all the nodes
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    //getting artist
//                    Group group = postSnapshot.getValue(Group.class);
//                    if (vaccinationInfo == group.getGroupNo()){
//                        groups.add(group);
//                    }
//                    else {
//                        Toast.makeText(ActivityAllGroups.this,"No Results", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//                GroupList groupList = new GroupList(ActivityAllGroups.this, groups);
//                listViewGroups.setAdapter(groupList);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }
}
