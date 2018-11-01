package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText editTextBreed;
    Button buttonAdd;
    Spinner spinnerGender;

    DatabaseReference databaseAnimal;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseAnimal = FirebaseDatabase.getInstance().getReference("animals");

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, DataRetrieved.class);
                startActivity(next);
            }
        });

        editTextBreed = (EditText) findViewById(R.id.editTextBreed);
        buttonAdd = (Button) findViewById(R.id.buttonAddAnimal);
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnimal();
            }
        });
    }

    private void addAnimal(){
        String breed = editTextBreed.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();

        if(!TextUtils.isEmpty(breed)){

            String id = databaseAnimal.push().getKey();

            Animal animal = new Animal(id, breed, gender);

            databaseAnimal.child(id).setValue(animal);

            Toast.makeText(this, "Animal added", Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this, "You should enter a breed", Toast.LENGTH_LONG).show();
        }
    }
}
