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

public class ActivityAllGroups extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<Group> groupList;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_groups);

        listView = (ListView)findViewById(R.id.listViewGroups);


        databaseReference = FirebaseDatabase.getInstance().getReference("groups");
        groupList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String tempListValue =(listView.getItemAtPosition(position).toString());
                //String tempListValue = animalList.get(position).toString();
                //.get(position).toString();

                TextView groupID = (TextView) parent.findViewById(R.id.groupID);
                String groupIDText = (String) groupID.getText();

                TextView groupType = (TextView) parent.findViewById(R.id.groupType);
                String groupTypeText = (String) groupType.getText();

                TextView groupDOB = (TextView) parent.findViewById(R.id.groupDOB);
                String groupDOBText = (String) groupDOB.getText();

                TextView groupBreed = (TextView) parent.findViewById(R.id.groupBreed);
                String groupBreedText = (String) groupBreed.getText();

                TextView groupSource = (TextView) parent.findViewById(R.id.groupSource);
                String groupSourceText = (String) groupSource.getText();


                //Multiple Values
                Intent intent = new Intent(ActivityAllGroups.this, ViewGroupDetails.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_ANIMAL_TYPE",groupTypeText);
                extras.putString("EXTRA_GROUP_ID",groupIDText);
                extras.putString("EXTRA_BREED",groupBreedText);
                extras.putString("EXTRA_DOB",groupDOBText);
                extras.putString("EXTRA_SOURCE", groupSourceText);

                intent.putExtras(extras);
                startActivity(intent);


               /* //Only animal ID
                Intent intent = new Intent(DataRetrieved.this, ViewAnimalDetails.class);
                intent.putExtra("ListViewClickedValue", animalIDText);*/

                //intent.putExtra("ListViewClickedValue", tempListValue);

                startActivity(intent);


            }
        }); //List view item click ends

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
                        Intent intentAnimal = new Intent(ActivityAllGroups.this, ActivityHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityAllGroups.this, "Vaccinations", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityAllGroups.this, ActivityVaccinationHome.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityAllGroups.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityAllGroups.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityAllGroups.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityAllGroups.this, ActivityOptions.class);
                        startActivity(intentHome);
                        break;
                    default:
                        return true;
                }
                return true;

            }

        });


           }
    protected void onStart(){
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot groupSnapshot : dataSnapshot.getChildren()){
                    Group group = groupSnapshot.getValue(Group.class);
                    groupList.add(group);
                }

                GroupInfoAdapter groupInfoAdapter = new GroupInfoAdapter(ActivityAllGroups.this, groupList);
                listView.setAdapter(groupInfoAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
