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

public class DataRetrieved extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<Animal> animalList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_retrieved);

        listView = findViewById(R.id.list_item);

        databaseReference = FirebaseDatabase.getInstance().getReference("animals");

        animalList = new ArrayList<>();

    }

    @Override
    protected void onStart(){
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot animalSnapshot : dataSnapshot.getChildren()){
                    Animal animal = animalSnapshot.getValue(Animal.class);
                    animalList.add(animal);
                }

                AnimalInfoAdapter animalInfoAdapter = new AnimalInfoAdapter(DataRetrieved.this, animalList);
                listView.setAdapter(animalInfoAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
