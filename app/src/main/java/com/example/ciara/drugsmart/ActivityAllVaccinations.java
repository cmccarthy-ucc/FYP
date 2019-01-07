package com.example.ciara.drugsmart;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityAllVaccinations extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<Vaccination> vaccinationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vaccinations);

        listView = (ListView)findViewById(R.id.listItemAllVaccinations);


        databaseReference = FirebaseDatabase.getInstance().getReference("vaccination");
        vaccinationList = new ArrayList<>();



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

                VaccinationInfoAdapter vaccinationInfoAdapter = new VaccinationInfoAdapter(ActivityAllVaccinations.this, vaccinationList);
                listView.setAdapter(vaccinationInfoAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
