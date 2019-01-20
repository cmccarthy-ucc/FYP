package com.example.ciara.drugsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityVaccinationHome extends AppCompatActivity {

    Button addVaccination;
    Button viewVaccinations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewVaccinations = (Button) findViewById(R.id.btnViewGroup);
        viewVaccinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityVaccinationHome.this, ActivityAllVaccinations.class);
                startActivity(intent);
            }
        });

//        addVaccination = (Button) findViewById(R.id.btnAddVaccination);
//        addVaccination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivityVaccinationHome.this, ActivityAddVaccination.class);
//                startActivity(intent);
//            }
//        });



    }


}
