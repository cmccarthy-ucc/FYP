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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityAllDoses extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<Dosing> dosingList;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_doses);

        listView = (ListView)findViewById(R.id.listItemAllGroupDoses);
        databaseReference = FirebaseDatabase.getInstance().getReference("groupDose");
        dosingList = new ArrayList<>();

    //Transferring data from selected list view item to other activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String tempListValue =(listView.getItemAtPosition(position).toString());
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
                Intent intent = new Intent(ActivityAllDoses.this, ActivityViewDoseDetails.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_GROUP_NUMBER",doseGroupNumberText);
                extras.putString("EXTRA_ID",groupDoseIDText);
                extras.putString("EXTRA_DRUG",doseDrugText);
                extras.putString("EXTRA_DATE",doseDateText);
                extras.putString("EXTRA_DOSAGE", doseDosageText);
                extras.putString("EXTRA_TYPE", doseTypeText);
                extras.putString("EXTRA_NOTES", doseNotesText);
                extras.putString("EXTRA_ALL_DOSED", allDosedText);
                extras.putString("EXTRA_ADMIN", doseAdminText);
                intent.putExtras(extras);
                startActivity(intent);


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
                        Toast.makeText(ActivityAllDoses.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ActivityAllDoses.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityAllDoses.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityAllDoses.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityAllDoses.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityAllDoses.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityAllDoses.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityAllDoses.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityAllDoses.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityAllDoses.this, ActivityToDoList.class);
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
                for(DataSnapshot dosingSnapshot : dataSnapshot.getChildren()){
                    Dosing dosing = dosingSnapshot.getValue(Dosing.class);
                    dosingList.add(dosing);
                }

               DosingInfoAdapter dosingInfoAdapter = new DosingInfoAdapter(ActivityAllDoses.this, dosingList);
                listView.setAdapter(dosingInfoAdapter);

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
