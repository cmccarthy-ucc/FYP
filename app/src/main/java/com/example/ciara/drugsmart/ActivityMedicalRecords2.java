package com.example.ciara.drugsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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

public class ActivityMedicalRecords2 extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<Vaccination> vaccinationList;

    DatabaseReference databaseReferenceGV;
    List<GroupVaccination> groupVaccinationList;

    DatabaseReference databaseReferenceDose;
    List<Dosing> dosingList;

    private TextView mTextMessage;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records2);

        listView = (ListView)findViewById(R.id.listViewMedicalRecords);
        databaseReference = FirebaseDatabase.getInstance().getReference("vaccination");
        vaccinationList = new ArrayList<>();

        databaseReferenceGV = FirebaseDatabase.getInstance().getReference("groupVaccination");
        groupVaccinationList = new ArrayList<>();

        databaseReferenceDose = FirebaseDatabase.getInstance().getReference("groupDose");
        dosingList = new ArrayList<>();

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        listView.setAdapter(null);
                        vaccinationList.clear();
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot vaccinationSnapshot : dataSnapshot.getChildren()){
                                    Vaccination vaccination = vaccinationSnapshot.getValue(Vaccination.class);
                                    vaccinationList.add(vaccination);
                                }
                                VaccinationInfoAdapter vaccinationInfoAdapter = new VaccinationInfoAdapter(ActivityMedicalRecords2.this, vaccinationList);
                                listView.setAdapter(vaccinationInfoAdapter);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        mTextMessage.setText("All Individual Treatments");
                        return true;

                    case R.id.navigation_dashboard:
                        listView.setAdapter(null);
                        groupVaccinationList.clear();
                        databaseReferenceGV.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()){
                                    GroupVaccination groupVaccination = groupVaccinationSnapshot.getValue(GroupVaccination.class);
                                    groupVaccinationList.add(groupVaccination);
                                }
                                GroupVaccinationInfoAdapter groupVaccinationInfoAdapter = new GroupVaccinationInfoAdapter(ActivityMedicalRecords2.this, groupVaccinationList);
                                listView.setAdapter(groupVaccinationInfoAdapter);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        mTextMessage.setText("All Group Vaccinations");

                        return true;
                    case R.id.navigation_notifications:
                        listView.setAdapter(null);
                        dosingList.clear();
                        databaseReferenceDose.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot dosingSnapshot : dataSnapshot.getChildren()){
                                    Dosing dosing = dosingSnapshot.getValue(Dosing.class);
                                    dosingList.add(dosing);
                                }
                                DosingInfoAdapter dosingInfoAdapter = new DosingInfoAdapter(ActivityMedicalRecords2.this, dosingList);
                                listView.setAdapter(dosingInfoAdapter);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        mTextMessage.setText("All Group Doses");
                        return true;
                }
                return false;
            }
        };

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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
                        Toast.makeText(ActivityMedicalRecords2.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ActivityMedicalRecords2.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityMedicalRecords2.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityMedicalRecords2.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityMedicalRecords2.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityMedicalRecords2.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityMedicalRecords2.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityMedicalRecords2.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityMedicalRecords2.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityMedicalRecords2.this, ActivityToDoList.class);
                        startActivity(intentToDo);
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
                for(DataSnapshot vaccinationSnapshot : dataSnapshot.getChildren()){
                    Vaccination vaccination = vaccinationSnapshot.getValue(Vaccination.class);
                    vaccinationList.add(vaccination);
                }
                VaccinationInfoAdapter vaccinationInfoAdapter = new VaccinationInfoAdapter(ActivityMedicalRecords2.this, vaccinationList);
                listView.setAdapter(vaccinationInfoAdapter);
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
