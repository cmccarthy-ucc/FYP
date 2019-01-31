package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewVaccinationDetails extends AppCompatActivity {
    //Declarations

    TextView vaccinationAnimalTag;
    TextView vaccinationID;
    TextView vaccinationDate;
    TextView vaccinationDrug;
    TextView vaccinationDosage;
    TextView vaccinationAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vaccination_details);


        vaccinationAnimalTag
                = (TextView)findViewById(R.id.vaccinationAnimalTag);
        vaccinationID
                = (TextView)findViewById(R.id.vaccinationID);
        vaccinationDate = (TextView)findViewById(R.id.vaccinationDate);
        vaccinationDrug = (TextView)findViewById(R.id.vaccinationDrug);
        vaccinationAdmin = (TextView)findViewById(R.id.vaccinationAdmin);
        vaccinationDosage = (TextView)findViewById(R.id.vaccinationDosage);

        /*//value passed from one activity to another
        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
        animalID.setText(TempHolder);
*/
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String vaccinationAnimalTagText = extras.getString("EXTRA_ANIMAL_TAG");
        String vaccinationIDText = extras.getString("EXTRA_ID");
        String vaccinationDateText = extras.getString("EXTRA_DATE");
        String vaccinationAdminText = extras.getString("EXTRA_ADMIN");
        String vaccinationDrugText = extras.getString("EXTRA_DRUG");
        String vaccinationDosageText = extras.getString("EXTRA_DOSAGE");


        vaccinationAnimalTag.setText(vaccinationAnimalTagText);
        vaccinationID.setText(vaccinationIDText);
        vaccinationDate.setText(vaccinationDateText);
        vaccinationDrug.setText(vaccinationDrugText);
        vaccinationDosage.setText(vaccinationDosageText);
        vaccinationAdmin.setText(vaccinationAdminText);
    }
}
