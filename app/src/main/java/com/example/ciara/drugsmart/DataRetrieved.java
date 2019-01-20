package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

////https://www.youtube.com/watch?v=wSCIuIbS-nk
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent = new Intent(DataRetrieved.this, AddVaccination.class);
//                intent.putExtra("AnimalID", listView.getItemIdAtPosition(position));
//                startActivity(intent);
//
//            }
//        });


        databaseReference = FirebaseDatabase.getInstance().getReference("animals");

        animalList = new ArrayList<>();


//Transferring data from selected list view item to other activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String tempListValue =(listView.getItemAtPosition(position).toString());
                //String tempListValue = animalList.get(position).toString();
                        //.get(position).toString();

                TextView animalID = (TextView) parent.findViewById(R.id.animalID);
                String animalIDText = (String) animalID.getText();

                TextView animalGender = (TextView) parent.findViewById(R.id.gender);
                String animalGenderText = (String) animalGender.getText();


                TextView animalDOB = (TextView) parent.findViewById(R.id.DOB);
                String animalDOBText = (String) animalDOB.getText();

                TextView source = (TextView) parent.findViewById(R.id.source);
                String sourceText = (String) source.getText();

                TextView animalBreed = (TextView) parent.findViewById(R.id.breed);
                String animalBreedText = (String) animalBreed.getText();

                TextView animalTag = (TextView) parent.findViewById(R.id.tag);
                String animalTagText = (String) animalTag.getText();

                //Multiple Values
                Intent intent = new Intent(DataRetrieved.this, ViewAnimalDetails.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_ID",animalIDText);
                extras.putString("EXTRA_DOB",animalDOBText);
                extras.putString("EXTRA_GENDER",animalGenderText);
                extras.putString("EXTRA_SOURCE",sourceText);
                extras.putString("EXTRA_BREED", animalBreedText);
                extras.putString("EXTRA_TAG", animalTagText);
                intent.putExtras(extras);
                startActivity(intent);


               /* //Only animal ID
                Intent intent = new Intent(DataRetrieved.this, ViewAnimalDetails.class);
                intent.putExtra("ListViewClickedValue", animalIDText);*/

                //intent.putExtra("ListViewClickedValue", tempListValue);

                startActivity(intent);


            }
        }); //List view item click ends



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
