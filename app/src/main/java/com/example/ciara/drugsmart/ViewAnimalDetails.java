package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ViewAnimalDetails extends AppCompatActivity {

    TextView animalID;
    /*TextView animalDOB;
    TextView animalGender;
    TextView sourceFarmer;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animal);

        animalID = (TextView)findViewById(R.id.animalID);
        /*animalDOB = (TextView)findViewById(R.id.animalDOB);
        animalGender = (TextView)findViewById(R.id.animalGender);
        sourceFarmer = (TextView)findViewById(R.id.sourceFarmer);*/

        //value passed from one activity to another
        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
        animalID.setText(TempHolder);

        /*Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String animalIDText = extras.getString("EXTRA_ID");
        String animalDOBText = extras.getString("EXTRA_DOB");
        String animalGenderText = extras.getString("EXTRA_Gender");
        String sourceText = extras.getString("EXTRA_SOURCE");*/


       /* animalID.setText(animalIDText);
        animalDOB.setText(animalDOBText);
        animalGender.setText(animalGenderText);
        sourceFarmer.setText(sourceText);*/

    }
}
