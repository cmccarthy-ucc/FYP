package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityOptions extends AppCompatActivity {

    ImageView imageViewAnimal;
    ImageView imageViewVaccination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        imageViewAnimal = (ImageView) findViewById(R.id.imageViewAnimal);
        imageViewAnimal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ActivityOptions.this, ActivityHome.class);
               startActivity(intent);
           }
    });

        imageViewVaccination = (ImageView) findViewById(R.id.imageViewVaccination);
        imageViewVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityOptions.this, ActivityHome.class);
                startActivity(intent);
            }
        });
}}
