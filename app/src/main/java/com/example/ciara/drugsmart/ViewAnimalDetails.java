package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewAnimalDetails extends AppCompatActivity {

    //Declarations

    TextView animalID;
    TextView animalGender;
    TextView animalDOB;
    TextView sourceFarmer;
    TextView animalBreed;
    TextView animalTag;
    TextView position;

    private Button btnAddVaccination;
    private Button btnViewVaccination;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

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
        position = (TextView)findViewById(R.id.position);

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
        String positionText = extras.getString("POSITION");

        animalID.setText(animalIDText);
        animalDOB.setText(animalDOBText);
        animalGender.setText(animalGenderText);
        sourceFarmer.setText(sourceText);
        animalBreed.setText(animalBreedText);
        animalTag.setText(animalTagText);
        position.setText(positionText);

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

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.animals:
                        Toast.makeText(ViewAnimalDetails.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ViewAnimalDetails.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ViewAnimalDetails.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ViewAnimalDetails.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ViewAnimalDetails.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ViewAnimalDetails.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ViewAnimalDetails.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ViewAnimalDetails.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ViewAnimalDetails.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ViewAnimalDetails.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drug:
                        Toast.makeText(ViewAnimalDetails.this, "Drugs Available", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(ViewAnimalDetails.this, ActivityToDoList.class);
                        startActivity(intentDrug);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}

