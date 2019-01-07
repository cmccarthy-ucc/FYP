package com.example.ciara.drugsmart;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityHome extends AppCompatActivity {


    Button addAnimal;
    Button viewAnimals;
    Button viewVaccinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addAnimal = (Button) findViewById(R.id.btnAddAnimal);
        addAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, MainActivity.class);
                startActivity(intent);
            }
        });

        viewAnimals = (Button) findViewById(R.id.btnViewAnimal);
        viewAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, DataRetrieved.class);
                startActivity(intent);
            }
        });
        viewVaccinations = (Button) findViewById(R.id.btnViewVaccinations);
        viewVaccinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, ActivityAllVaccinations.class);
                startActivity(intent);
            }
        });

    }


}
