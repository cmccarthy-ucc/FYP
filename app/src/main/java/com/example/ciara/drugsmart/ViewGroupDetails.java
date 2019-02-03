package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class

ViewGroupDetails extends AppCompatActivity {
    //Declarations

    TextView groupID;
    TextView groupAnimalType;
    TextView groupDOB;
    TextView groupSource;
    TextView groupBreed;
    TextView groupNumber;


    private Button buttonAddVaccination;
    private Button btnViewVaccination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_details);

        groupID
                = (TextView)findViewById(R.id.textViewGroupID);
        groupAnimalType
                = (TextView)findViewById(R.id.textViewAnimalType);
        groupDOB = (TextView)findViewById(R.id.textViewGroupDOB);
        groupSource = (TextView)findViewById(R.id.textViewGroupSource);
        groupBreed = (TextView)findViewById(R.id.textViewGroupBreed);
        groupNumber = (TextView)findViewById(R.id.textViewGroupNumber);

        /*//value passed from one activity to another
        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
        animalID.setText(TempHolder);
*/
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String groupIDText = extras.getString("EXTRA_GROUP_ID");
        String groupNumberText = extras.getString("EXTRA_GROUP_NUMBER");
        String groupAnimalTypeText = extras.getString("EXTRA_ANIMAL_TYPE");
        String groupDOBText = extras.getString("EXTRA_DOB");
        String groupSourceText = extras.getString("EXTRA_SOURCE");
        String groupBreedText = extras.getString("EXTRA_BREED");



        groupID.setText(groupIDText);
        groupAnimalType.setText(groupAnimalTypeText);
        groupDOB.setText(groupDOBText);
        groupSource.setText(groupSourceText);
        groupBreed.setText(groupBreedText);
        groupNumber.setText(groupNumberText);

        buttonAddVaccination = (Button)findViewById(R.id.buttonAddVaccination);
        buttonAddVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting text to transfer to next activity

                TextView groupID = (TextView) findViewById(R.id.textViewGroupID);
                String groupIDText = (String) groupID.getText();

                TextView groupNumber = (TextView) findViewById(R.id.textViewGroupNumber);
                String groupNumberText = (String) groupNumber.getText();

                Intent vaccinationIntent = new Intent(ViewGroupDetails.this, ActivityAddGroupVaccination.class);
                Bundle vaccinationExtras = new Bundle();
                vaccinationExtras.putString("VACCINATION_EXTRA_ID",groupIDText);
                vaccinationExtras.putString("VACCINATION_EXTRA_NUMBER",groupNumberText);

                //Placing the string values into the intent for transfer
                vaccinationIntent.putExtras(vaccinationExtras);
                startActivity(vaccinationIntent);

            }
        });


    }
}
