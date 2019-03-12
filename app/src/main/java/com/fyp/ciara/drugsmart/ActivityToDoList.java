package com.fyp.ciara.drugsmart;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityToDoList extends AppCompatActivity {

    private ListView listViewResults;
    DatabaseReference databaseReference;
    List<GroupVaccination> groupVaccinationList;


    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        listViewResults = (ListView) findViewById(R.id.listViewToDo);
        databaseReference = FirebaseDatabase.getInstance().getReference("groupVaccination");
        groupVaccinationList = new ArrayList<>();

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
//                        Toast.makeText(ActivityToDoList.this, "Animals", Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(ActivityToDoList.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityToDoList.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityToDoList.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityToDoList.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityToDoList.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityToDoList.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityToDoList.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityToDoList.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityToDoList.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

        listViewResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String tempListValue = (listViewResults.getItemAtPosition(position).toString());
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
                Intent intent = new Intent(ActivityToDoList.this, ActivityToDoDoseDetails.class);
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
    protected void onStart() {
        super.onStart();
        groupVaccinationList.clear();
        Query toDoListQuery = databaseReference.orderByChild("allVaccinated").equalTo(false);
        toDoListQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()) {
                    GroupVaccination groupVaccination = groupVaccinationSnapshot.getValue(GroupVaccination.class);
                    groupVaccinationList.add(groupVaccination);
                }

                GroupVaccinationInfoAdapter groupVaccinationInfoAdapter = new GroupVaccinationInfoAdapter(ActivityToDoList.this, groupVaccinationList);
                listViewResults.setAdapter(groupVaccinationInfoAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        Query toDoListQuery = databaseReference.child("groupVaccination").orderByChild("allVaccinated").equalTo(false);
//        toDoListQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()) {
//                    GroupVaccination groupVaccination = groupVaccinationSnapshot.getValue(GroupVaccination.class);
//                    groupVaccinationList.add(groupVaccination);
//                }
//                GroupVaccinationInfoAdapter groupVaccinationInfoAdapter = new GroupVaccinationInfoAdapter(ActivityToDoList.this, groupVaccinationList);
//                listViewResults.setAdapter(groupVaccinationInfoAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
//var query = firebaseDb.child("chats").orderByChild("newQuestion").equalTo(true);
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
