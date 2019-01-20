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
    Button addGroup;
    Button viewGroups;
    Button search;

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
        viewGroups = (Button) findViewById(R.id.btnViewGroup);
        viewGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, ActivityAllGroups.class);
                startActivity(intent);
            }
        });
        addGroup = (Button) findViewById(R.id.btnAddGroup);
        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, ActivityAddGroup.class);
                startActivity(intent);
            }
        });

        search = (Button) findViewById(R.id.buttonSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, SearchDatabase.class);
                startActivity(intent);
            }
        });

    }


}
