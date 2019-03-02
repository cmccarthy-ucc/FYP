package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_NUMBER;
import static com.example.ciara.drugsmart.GroupActivity.ALL_DOSED;
import static com.example.ciara.drugsmart.GroupActivity.ALL_VACCINATED;
import static com.example.ciara.drugsmart.GroupActivity.DOSE_ADMIN;
import static com.example.ciara.drugsmart.GroupActivity.DOSE_DATE;
import static com.example.ciara.drugsmart.GroupActivity.DOSE_DOSAGE;
import static com.example.ciara.drugsmart.GroupActivity.DOSE_DRUG;
import static com.example.ciara.drugsmart.GroupActivity.DOSE_GROUP_NUMBER;
import static com.example.ciara.drugsmart.GroupActivity.DOSE_ID;
import static com.example.ciara.drugsmart.GroupActivity.DOSE_NOTES;
import static com.example.ciara.drugsmart.GroupActivity.DOSE_TYPE;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_ADMIN;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_DATE;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_DOSAGE;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_DRUG;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_GROUP_NOTES;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_GROUP_NUMBER;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_ID;

public class ActivityToDoDoses extends AppCompatActivity {

    private ListView listView;

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
        setContentView(R.layout.activity_to_do_doses);

        listView = (ListView) findViewById(R.id.listViewToDoDoses);

        databaseReferenceGV = FirebaseDatabase.getInstance().getReference("groupVaccinations");
        groupVaccinationList = new ArrayList<>();

        databaseReferenceDose = FirebaseDatabase.getInstance().getReference("groupDoses");
        dosingList = new ArrayList<>();


        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_vaccinations:
                        listView.setAdapter(null);
                        groupVaccinationList.clear();
                        databaseReferenceGV.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()) {
                                    for (DataSnapshot vaccinationSnapshot : groupVaccinationSnapshot.getChildren()) {
                                        GroupVaccination groupVaccination = vaccinationSnapshot.getValue(GroupVaccination.class);
                                        if(!groupVaccination.getAllVaccinated()) {
                                            groupVaccinationList.add(groupVaccination);
                                        }
                                    }
                                }
                                AllGroupVaccinationList groupVaccinationInfoAdapter = new AllGroupVaccinationList(ActivityToDoDoses.this, groupVaccinationList);
                                listView.setAdapter(groupVaccinationInfoAdapter);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                throw databaseError.toException();

                            }
                        });

                           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        //getting the selected artist
                                        GroupVaccination groupVaccination = groupVaccinationList.get(i);
                                        //creating an intent
                                        Intent intent = new Intent(getApplicationContext(), GroupVaccinationDetailsActivity.class);
                                        //putting artist name and id to intent
                                        intent.putExtra(GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
                                        intent.putExtra(VACCINATION_ID, groupVaccination.getVaccinationID());
                                        intent.putExtra(VACCINATION_DRUG, groupVaccination.getVaccinationDrug());
                                        intent.putExtra(VACCINATION_DOSAGE, groupVaccination.getVaccinationDosage());
                                        intent.putExtra(VACCINATION_ADMIN, groupVaccination.getVaccinationAdmin());
                                        intent.putExtra(VACCINATION_DATE, groupVaccination.getVaccinationDate());
                                        intent.putExtra(VACCINATION_GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
                                        intent.putExtra(VACCINATION_GROUP_NOTES, groupVaccination.getVaccinationNotes());
                                        intent.putExtra(ALL_VACCINATED, groupVaccination.getAllVaccinated());
                                        //starting the activity with intent
                                        startActivity(intent);
                                    }

                                });
//
                        mTextMessage.setText("Group Vaccinations to be completed");

                        return true;
                    case R.id.navigation_doses:
                        listView.setAdapter(null);
                        dosingList.clear();
//                        Query toDoDosesQuery = databaseReferenceDose.orderByChild("allDosed").equalTo(false);
//                        toDoDosesQuery.addValueEventListener(new ValueEventListener() {
                        databaseReferenceDose.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dosingSnapshot : dataSnapshot.getChildren()) {
                                    for (DataSnapshot doseSnapshot : dosingSnapshot.getChildren()) {
                                        Dosing dosing = doseSnapshot.getValue(Dosing.class);
                                        if (!dosing.getAllDosed()) {
                                            dosingList.add(dosing);
                                        }
                                    }
                                }
//                                for (DataSnapshot dosingSnapshot : dataSnapshot.getChildren()) {
//                                    Dosing dosing = dosingSnapshot.getValue(Dosing.class);
//                                    dosingList.add(dosing);
                                AllGroupDoseList dosingInfoAdapter = new AllGroupDoseList(ActivityToDoDoses.this, dosingList);
                                listView.setAdapter(dosingInfoAdapter);

                            }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            throw databaseError.toException();
                        }
                        });
                        mTextMessage.setText("Group Doses to be completed");


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                //getting the selected artist
                                Dosing doses = dosingList.get(i);
                                //creating an intent
                                Intent intent = new Intent(getApplicationContext(), ActivityViewDoseDetails.class);
                                //putting artist name and id to intent
                                intent.putExtra(GROUP_NUMBER, doses.getDoseGroupNumber());
                                intent.putExtra(DOSE_ID, doses.getDoseID());
                                intent.putExtra(DOSE_DRUG, doses.getDoseDrug());
                                intent.putExtra(DOSE_DOSAGE, doses.getDoseDosage());
                                intent.putExtra(DOSE_ADMIN, doses.getDoseAdmin());
                                intent.putExtra(DOSE_DATE, doses.getDoseDate());
                                intent.putExtra(DOSE_GROUP_NUMBER, doses.getDoseGroupNumber());
                                intent.putExtra(DOSE_NOTES, doses.getDoseNotes());
                                intent.putExtra(ALL_DOSED, doses.getAllDosed());
                                intent.putExtra(DOSE_TYPE, doses.getDoseType());
                                //starting the activity with intent
                                startActivity(intent);
                            }
                            });
                        mTextMessage.setText("Group Vaccinations to be Completed");
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
                        Toast.makeText(ActivityToDoDoses.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ActivityToDoDoses.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityToDoDoses.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityToDoDoses.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityToDoDoses.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityToDoDoses.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityToDoDoses.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityToDoDoses.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityToDoDoses.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityToDoDoses.this, ActivityToDoList.class);
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
        listView.setAdapter(null);
        groupVaccinationList.clear();
        databaseReferenceGV.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot vaccinationSnapshot : groupVaccinationSnapshot.getChildren()) {
                        GroupVaccination groupVaccination = vaccinationSnapshot.getValue(GroupVaccination.class);
                        if(!groupVaccination.getAllVaccinated()) {
                            groupVaccinationList.add(groupVaccination);
                        }
                    }
                }
                AllGroupVaccinationList groupVaccinationInfoAdapter = new AllGroupVaccinationList(ActivityToDoDoses.this, groupVaccinationList);
                listView.setAdapter(groupVaccinationInfoAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                GroupVaccination groupVaccination = groupVaccinationList.get(i);
                //creating an intent
                Intent intent = new Intent(getApplicationContext(), GroupVaccinationDetailsActivity.class);
                //putting artist name and id to intent
                intent.putExtra(GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
                intent.putExtra(VACCINATION_ID, groupVaccination.getVaccinationID());
                intent.putExtra(VACCINATION_DRUG, groupVaccination.getVaccinationDrug());
                intent.putExtra(VACCINATION_DOSAGE, groupVaccination.getVaccinationDosage());
                intent.putExtra(VACCINATION_ADMIN, groupVaccination.getVaccinationAdmin());
                intent.putExtra(VACCINATION_DATE, groupVaccination.getVaccinationDate());
                intent.putExtra(VACCINATION_GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
                intent.putExtra(VACCINATION_GROUP_NOTES, groupVaccination.getVaccinationNotes());
                intent.putExtra(ALL_VACCINATED, groupVaccination.getAllVaccinated());
                //starting the activity with intent
                startActivity(intent);
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

    //addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                for (DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()) {
//                                    GroupVaccination groupVaccination = groupVaccinationSnapshot.getValue(GroupVaccination.class);
//                                    groupVaccinationList.add(groupVaccination);
//                                }
//                                GroupVaccinationInfoAdapter groupVaccinationInfoAdapter = new GroupVaccinationInfoAdapter(ActivityToDoDoses.this, groupVaccinationList);
//                                listView.setAdapter(groupVaccinationInfoAdapter);
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });

