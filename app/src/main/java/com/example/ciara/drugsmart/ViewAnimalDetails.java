package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewAnimalDetails extends AppCompatActivity {

    //Declarations

    TextView animalID;
    TextView animalGender;
    TextView animalDOB;
    TextView sourceFarmer;
    TextView animalBreed;
    TextView animalTag;

    private Button btnAddVaccination;
    private Button btnViewVaccination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animal);

        animalID = (TextView)findViewById(R.id.animalID);
        animalGender = (TextView)findViewById(R.id.animalGender);
        animalDOB = (TextView)findViewById(R.id.animalDOB);
        animalGender = (TextView)findViewById(R.id.animalGender);
        sourceFarmer = (TextView)findViewById(R.id.sourceFarmer);
        animalBreed = (TextView)findViewById(R.id.animalBreed);
        animalTag = (TextView)findViewById(R.id.animalTag);

        /*//value passed from one activity to another
        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
        animalID.setText(TempHolder);
*/
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String animalIDText = extras.getString("EXTRA_ID");
        String animalDOBText = extras.getString("EXTRA_DOB");
        String animalGenderText = extras.getString("EXTRA_GENDER");
        String sourceText = extras.getString("EXTRA_SOURCE");
        String animalBreedText = extras.getString("EXTRA_BREED");
        String animalTagText = extras.getString("EXTRA_TAG");


        animalID.setText(animalIDText);
        animalDOB.setText(animalDOBText);
        animalGender.setText(animalGenderText);
        sourceFarmer.setText(sourceText);
        animalBreed.setText(animalBreedText);
        animalTag.setText(animalTagText);

        //Add Vaccination Button

        btnAddVaccination = (Button)findViewById(R.id.addVaccination);
        btnAddVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting text to transfer to next activity

                TextView animalID = (TextView) findViewById(R.id.animalID);
                String IDText = (String) animalID.getText();

                TextView animalTag = (TextView) findViewById(R.id.animalTag);
                String tagText = (String) animalTag.getText();

                Intent vaccinationIntent = new Intent(ViewAnimalDetails.this, AddVaccination.class);
                Bundle vaccinationExtras = new Bundle();
                vaccinationExtras.putString("VACCINATION_EXTRA_ID",IDText);
                vaccinationExtras.putString("VACCINATION_EXTRA_TAG",tagText);

                //Placing the string values into the intent for transfer
                vaccinationIntent.putExtras(vaccinationExtras);
                startActivity(vaccinationIntent);

                    }
                  });

        btnViewVaccination = (Button)findViewById(R.id.buttonViewVaccination);
        btnViewVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView animalID = (TextView) findViewById(R.id.animalID);
                String viewIDText = (String) animalID.getText();
                Intent viewVaccinationIntent = new Intent(ViewAnimalDetails.this, ViewVaccination.class);
                viewVaccinationIntent.putExtra("VIEW_VACCINATION_ANIMAL_ID", viewIDText );
                startActivity(viewVaccinationIntent);
            }
        });


}
}

