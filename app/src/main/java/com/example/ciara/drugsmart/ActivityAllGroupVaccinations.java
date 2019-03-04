package com.example.ciara.drugsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_NUMBER;
import static com.example.ciara.drugsmart.GroupActivity.ALL_VACCINATED;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_ADMIN;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_DATE;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_DOSAGE;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_DRUG;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_GROUP_NOTES;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_GROUP_NUMBER;
import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_ID;

public class ActivityAllGroupVaccinations extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<GroupVaccination> groupVaccinations;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    FirebaseAuth auth;

//    AllGroupVaccinationList groupVaccinationInfoAdapter = new AllGroupVaccinationList(ActivityAllGroupVaccinations.this, groupVaccinations);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_group_vaccinations);

        listView = (ListView)findViewById(R.id.listItemAllGroupVaccinations);
//        databaseReference = FirebaseDatabase.getInstance().getReference("groupVaccinations");
//        groupVaccinations = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("groupVaccinations");
        groupVaccinations = new ArrayList<>();
      //  listView.setAdapter(groupVaccinationInfoAdapter);


        //Transferring data from selected list view item to other activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //getting the selected artist
                        GroupVaccination groupVaccination = groupVaccinations.get(i);
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

//                String tempListValue =(listView.getItemAtPosition(position).toString());
//                //String tempListValue = animalList.get(position).toString();
//                //.get(position).toString();
//
//                TextView groupVaccinationID = (TextView) parent.findViewById(R.id.groupVaccinationID);
//                String groupVaccinationIDText = (String) groupVaccinationID.getText();
//
//                TextView vaccinationGroupNumber = (TextView) parent.findViewById(R.id.groupNumber);
//                String vaccinationAnimalTagText = (String) vaccinationGroupNumber.getText();
//
//                TextView vaccinationDate = (TextView) parent.findViewById(R.id.date);
//                String vaccinationDateText = (String) vaccinationDate.getText();
//
//                TextView vaccinationDosage = (TextView) parent.findViewById(R.id.dosage);
//                String vaccinationDosageText = (String) vaccinationDosage.getText();
//
//                TextView vaccinationDrug = (TextView) parent.findViewById(R.id.drug);
//                String vaccinationDrugText = (String) vaccinationDrug.getText();
//
//                TextView vaccinationAdmin = (TextView) parent.findViewById(R.id.administrator);
//                String vaccinationAdminText = (String) vaccinationAdmin.getText();
//
//                //Multiple Values
//                Intent intent = new Intent(ActivityAllGroupVaccinations.this, ViewVaccinationDetails.class);
//                Bundle extras = new Bundle();
//                extras.putString("EXTRA_ANIMAL_TAG",vaccinationAnimalTagText);
//                extras.putString("EXTRA_ID",groupVaccinationIDText);
//                extras.putString("EXTRA_DRUG",vaccinationDrugText);
//                extras.putString("EXTRA_DATE",vaccinationDateText);
//                extras.putString("EXTRA_DOSAGE", vaccinationDosageText);
//                extras.putString("EXTRA_ADMIN", vaccinationAdminText);
//                intent.putExtras(extras);
//                startActivity(intent);


               /* //Only animal ID
                Intent intent = new Intent(DataRetrieved.this, ViewAnimalDetails.class);
                intent.putExtra("ListViewClickedValue", animalIDText);*/

                //intent.putExtra("ListViewClickedValue", tempListValue);

                //startActivity(intent);


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
                        Toast.makeText(ActivityAllGroupVaccinations.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ActivityAllGroupVaccinations.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityAllGroupVaccinations.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityAllGroupVaccinations.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityAllGroupVaccinations.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityAllGroupVaccinations.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityAllGroupVaccinations.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityAllGroupVaccinations.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityAllGroupVaccinations.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityAllGroupVaccinations.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(ActivityAllGroupVaccinations.this, "Drugs", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(ActivityAllGroupVaccinations.this, AddDrug.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(ActivityAllGroupVaccinations.this, Login.class));
                        finish();
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
        groupVaccinations.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot vaccinationSnapshot : groupVaccinationSnapshot.getChildren()) {
                        GroupVaccination groupVaccination = vaccinationSnapshot.getValue(GroupVaccination.class);
                        groupVaccinations.add(groupVaccination);
                    }

                AllGroupVaccinationList groupVaccinationInfoAdapter = new AllGroupVaccinationList(ActivityAllGroupVaccinations.this, groupVaccinations);
                listView.setAdapter(groupVaccinationInfoAdapter);
            }}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()){
//                    for(DataSnapshot vaccinationSnapshot : groupVaccinationSnapshot.getChildren()){
//                        GroupVaccination groupVaccination = vaccinationSnapshot.getValue(GroupVaccination.class);
//                        groupVaccinations.add(groupVaccination);
//                    }
//                }
//                AllGroupVaccinationList groupVaccinationInfoAdapter = new AllGroupVaccinationList(ActivityAllGroupVaccinations.this, groupVaccinations);
//                groupVaccinationInfoAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                throw databaseError.toException(); // don't ignore errors
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


}
