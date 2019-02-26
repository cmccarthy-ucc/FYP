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

        databaseReferenceGV = FirebaseDatabase.getInstance().getReference("groupVaccination");
        groupVaccinationList = new ArrayList<>();

        databaseReferenceDose = FirebaseDatabase.getInstance().getReference("groupDose");
        dosingList = new ArrayList<>();


        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_vaccinations:
                        listView.setAdapter(null);
                        groupVaccinationList.clear();
                        Query toDoVaccinationQuery = databaseReferenceGV.orderByChild("allVaccinated").equalTo(false);
                        toDoVaccinationQuery.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()) {
                                    GroupVaccination groupVaccination = groupVaccinationSnapshot.getValue(GroupVaccination.class);
                                    groupVaccinationList.add(groupVaccination);
                                }
                                GroupVaccinationInfoAdapter groupVaccinationInfoAdapter = new GroupVaccinationInfoAdapter(ActivityToDoDoses.this, groupVaccinationList);
                                listView.setAdapter(groupVaccinationInfoAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                String tempListValue = (listView.getItemAtPosition(position).toString());
                                //String tempListValue = animalList.get(position).toString();
                                //.get(position).toString();

                                TextView groupVaccinationID = (TextView) view.findViewById(R.id.groupVaccinationID);
                                String groupVaccinationIDText = (String) groupVaccinationID.getText();

                                TextView vaccinationGroupNumber = (TextView) view.findViewById(R.id.groupNumber);
                                String vaccinationGroupNumberText = (String) vaccinationGroupNumber.getText();

                                TextView vaccinationDate = (TextView) view.findViewById(R.id.date);
                                String vaccinationDateText = (String) vaccinationDate.getText();

                                TextView vaccinationDosage = (TextView) view.findViewById(R.id.dosage);
                                String vaccinationDosageText = (String) vaccinationDosage.getText();


                                TextView vaccinationDrug = (TextView) view.findViewById(R.id.drug);
                                String vaccinationDrugText = (String) vaccinationDrug.getText();

                                TextView vaccinationAdmin = (TextView) view.findViewById(R.id.administrator);
                                String vaccinationAdminText = (String) vaccinationAdmin.getText();

                                TextView allVaccinated = (TextView) view.findViewById(R.id.allVaccinatedTrueFalse);
                                String allVaccinatedText = (String) allVaccinated.getText();

                                TextView vaccinationNotes = (TextView) view.findViewById(R.id.notes);
                                String vaccinationNotesText = (String) vaccinationNotes.getText();

                                TextView vaccinationID = (TextView) view.findViewById(R.id.vaccinationID);
                                String vaccinationIDText = (String) vaccinationID.getText();


                                //Multiple Values
                                Intent intent = new Intent(ActivityToDoDoses.this, ActivityToDoDetails.class);
                                Bundle extras = new Bundle();
                                extras.putString("EXTRA_GROUP_NUMBER", vaccinationGroupNumberText);
                                extras.putString("EXTRA_GROUP_ID", groupVaccinationIDText);
                                extras.putString("EXTRA_DRUG", vaccinationDrugText);
                                extras.putString("EXTRA_DATE", vaccinationDateText);
                                extras.putString("EXTRA_DOSAGE", vaccinationDosageText);
                                extras.putString("EXTRA_ADMIN", vaccinationAdminText);
                                extras.putString("EXTRA_ALL_VACCINATED", allVaccinatedText);
                                extras.putString("EXTRA_NOTES", vaccinationNotesText);
                                extras.putString("EXTRA_VACCINATION_ID", vaccinationIDText);
                                intent.putExtras(extras);
                                startActivity(intent);
                            }

                        });
                        mTextMessage.setText("Group Vaccinations to be completed");

                        return true;
                    case R.id.navigation_doses:
                        listView.setAdapter(null);
                        dosingList.clear();
                        Query toDoDosesQuery = databaseReferenceDose.orderByChild("allDosed").equalTo(false);
                        toDoDosesQuery.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dosingSnapshot : dataSnapshot.getChildren()) {
                                    Dosing dosing = dosingSnapshot.getValue(Dosing.class);
                                    dosingList.add(dosing);
                                }
                                DosingInfoAdapter dosingInfoAdapter = new DosingInfoAdapter(ActivityToDoDoses.this, dosingList);
                                listView.setAdapter(dosingInfoAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        mTextMessage.setText("Group Doses to be completed");


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                String tempListValue = (listView.getItemAtPosition(position).toString());
                                //String tempListValue = animalList.get(position).toString();
                                //.get(position).toString();

                                TextView groupDoseID = (TextView) view.findViewById(R.id.groupDoseID);
                                String groupDoseIDText = (String) groupDoseID.getText();

                                TextView doseGroupNumber = (TextView) view.findViewById(R.id.groupNumber);
                                String doseGroupNumberText = (String) doseGroupNumber.getText();

                                TextView doseDate = (TextView) view.findViewById(R.id.date);
                                String doseDateText = (String) doseDate.getText();

                                TextView doseDosage = (TextView) view.findViewById(R.id.dosage);
                                String doseDosageText = (String) doseDosage.getText();

                                TextView doseDrug = (TextView) view.findViewById(R.id.drug);
                                String doseDrugText = (String) doseDrug.getText();

                                TextView doseAdmin = (TextView) view.findViewById(R.id.administrator);
                                String doseAdminText = (String) doseAdmin.getText();

                                TextView doseType = view.findViewById(R.id.doseType);
                                String doseTypeText = (String) doseType.getText();

                                TextView doseNotes = view.findViewById(R.id.notes);
                                String doseNotesText = (String) doseNotes.getText();

                                TextView allDosed = view.findViewById(R.id.allDosedTrueFalse);
                                String allDosedText = (String) allDosed.getText();

                                //Multiple Values
                                Intent intent = new Intent(ActivityToDoDoses.this, ActivityToDoDoseDetails.class);
                                Bundle extras = new Bundle();
                                extras.putString("EXTRA_GROUP_NUMBER", doseGroupNumberText);
                                extras.putString("EXTRA_ID", groupDoseIDText);
                                extras.putString("EXTRA_DRUG", doseDrugText);
                                extras.putString("EXTRA_DATE", doseDateText);
                                extras.putString("EXTRA_DOSAGE", doseDosageText);
                                extras.putString("EXTRA_TYPE", doseTypeText);
                                extras.putString("EXTRA_NOTES", doseNotesText);
                                extras.putString("EXTRA_ALL_DOSED", allDosedText);
                                extras.putString("EXTRA_ADMIN", doseAdminText);
                                intent.putExtras(extras);
                                startActivity(intent);

                                mTextMessage.setText("Group Vaccinations to be Completed");
                            }
                            });
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
        Query toDoVaccinationQuery = databaseReferenceGV.orderByChild("allVaccinated").equalTo(false);
        toDoVaccinationQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()){
                    GroupVaccination groupVaccination = groupVaccinationSnapshot.getValue(GroupVaccination.class);
                                   groupVaccinationList.add(groupVaccination);
                }
                GroupVaccinationInfoAdapter groupVaccinationInfoAdapter = new GroupVaccinationInfoAdapter(ActivityToDoDoses.this, groupVaccinationList);
                               listView.setAdapter(groupVaccinationInfoAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String tempListValue = (listView.getItemAtPosition(position).toString());
                //String tempListValue = animalList.get(position).toString();
                //.get(position).toString();

                TextView groupVaccinationID = (TextView) view.findViewById(R.id.groupVaccinationID);
                String groupVaccinationIDText = (String) groupVaccinationID.getText();

                TextView vaccinationGroupNumber = (TextView) view.findViewById(R.id.groupNumber);
                String vaccinationGroupNumberText = (String) vaccinationGroupNumber.getText();

                TextView vaccinationDate = (TextView) view.findViewById(R.id.date);
                String vaccinationDateText = (String) vaccinationDate.getText();

                TextView vaccinationDosage = (TextView) view.findViewById(R.id.dosage);
                String vaccinationDosageText = (String) vaccinationDosage.getText();


                TextView vaccinationDrug = (TextView) view.findViewById(R.id.drug);
                String vaccinationDrugText = (String) vaccinationDrug.getText();

                TextView vaccinationAdmin = (TextView) view.findViewById(R.id.administrator);
                String vaccinationAdminText = (String) vaccinationAdmin.getText();

                TextView allVaccinated = (TextView) view.findViewById(R.id.allVaccinatedTrueFalse);
                String allVaccinatedText = (String) allVaccinated.getText();

                TextView vaccinationNotes = (TextView) view.findViewById(R.id.notes);
                String vaccinationNotesText = (String) vaccinationNotes.getText();

                TextView vaccinationID = (TextView) view.findViewById(R.id.vaccinationID);
                String vaccinationIDText = (String) vaccinationID.getText();


                //Multiple Values
                Intent intent = new Intent(ActivityToDoDoses.this, ActivityToDoDetails.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_GROUP_NUMBER", vaccinationGroupNumberText);
                extras.putString("EXTRA_GROUP_ID", groupVaccinationIDText);
                extras.putString("EXTRA_DRUG", vaccinationDrugText);
                extras.putString("EXTRA_DATE", vaccinationDateText);
                extras.putString("EXTRA_DOSAGE", vaccinationDosageText);
                extras.putString("EXTRA_ADMIN", vaccinationAdminText);
                extras.putString("EXTRA_ALL_VACCINATED", allVaccinatedText);
                extras.putString("EXTRA_NOTES", vaccinationNotesText);
                extras.putString("EXTRA_VACCINATION_ID", vaccinationIDText);
                intent.putExtras(extras);
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

