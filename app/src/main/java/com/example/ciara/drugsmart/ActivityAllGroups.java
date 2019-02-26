package com.example.ciara.drugsmart;

import android.app.ActivityGroup;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

public class ActivityAllGroups extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<Group> groupList;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    List<Group> groups;
    DatabaseReference databaseGroups;
    ListView listViewGroups;

    Button buttonAddGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_groups);

//        listView = (ListView)findViewById(R.id.listViewGroups);

        databaseGroups = FirebaseDatabase.getInstance().getReference("groups");

        //list to store artists
        groups = new ArrayList<>();

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
                    default:
                        return true;
                }
                return true;

            }

        });

        buttonAddGroup = findViewById(R.id.buttonAddGroup);
        buttonAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAllGroups.this, ActivityAddGroup.class);
                startActivity(intent);
            }
        });


           }
    protected void onStart(){
        super.onStart();
        databaseGroups.addValueEventListener(new ValueEventListener() {
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

                //creating adapter
                GroupList groupAdapter = new GroupList(ActivityAllGroups.this, groups);
                //attaching adapter to the listview
                listViewGroups.setAdapter(groupAdapter);
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
