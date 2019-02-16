package com.example.ciara.drugsmart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

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
    private ListView listViewGroup;
    DatabaseReference databaseReferenceGroup;
    List<GroupVaccination> groupVaccinationList;


    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    mTextMessage.setText(R.string.title_home);
                    changeVaccinationList();
                    return true;

                case R.id.navigation_dashboard:
//                    databaseReferenceGroup = FirebaseDatabase.getInstance().getReference("vaccinationGroup");
//                    groupVaccinationList = new ArrayList<>();
//                    listView = (ListView)findViewById(R.id.listViewMedicalRecords);
//                    databaseReferenceGroup.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            for(DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()){
//                                GroupVaccination groupVaccination = groupVaccinationSnapshot.getValue(GroupVaccination.class);
//                                groupVaccinationList.add(groupVaccination);
//                            }
//
//                            GroupVaccinationInfoAdapter groupVaccinationInfoAdapter = new GroupVaccinationInfoAdapter(ActivityMedicalRecords2.this, groupVaccinationList);
//                            listView.setAdapter(groupVaccinationInfoAdapter);
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });

                    mTextMessage.setText(R.string.title_dashboard);
                   changeGroupVaccinationList();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records2);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listView = (ListView)findViewById(R.id.listViewMedicalRecords);
        databaseReference = FirebaseDatabase.getInstance().getReference("vaccination");
        vaccinationList = new ArrayList<>();
        databaseReferenceGroup = FirebaseDatabase.getInstance().getReference("vaccinationGroup");
        groupVaccinationList = new ArrayList<>();

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

private void changeVaccinationList(){

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

private void changeGroupVaccinationList(){

    databaseReferenceGroup.addValueEventListener(new ValueEventListener() {
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
}
}
