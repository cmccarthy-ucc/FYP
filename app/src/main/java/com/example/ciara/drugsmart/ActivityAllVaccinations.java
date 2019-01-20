package com.example.ciara.drugsmart;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.events.Event;

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
