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
    private Button buttonAddDose;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


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

        buttonAddVaccination = (Button)findViewById(R.id.buttonUpdateVaccination);
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

        buttonAddDose = (Button)findViewById(R.id.buttonAddDose);
        buttonAddDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting text to transfer to next activity

                TextView groupID = (TextView) findViewById(R.id.textViewGroupID);
                String groupIDText = (String) groupID.getText();

                TextView groupNumber = (TextView) findViewById(R.id.textViewGroupNumber);
                String groupNumberText = (String) groupNumber.getText();

                Intent doseIntent = new Intent(ViewGroupDetails.this, ActivityAddGroupDose.class);
                Bundle doseExtras = new Bundle();
                doseExtras.putString("DOSE_EXTRA_ID",groupIDText);
                doseExtras.putString("DOSE_EXTRA_NUMBER",groupNumberText);

                //Placing the string values into the intent for transfer
                doseIntent.putExtras(doseExtras);
                startActivity(doseIntent);

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
                        Toast.makeText(ViewGroupDetails.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ViewGroupDetails.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ViewGroupDetails.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ViewGroupDetails.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ViewGroupDetails.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ViewGroupDetails.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ViewGroupDetails.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ViewGroupDetails.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ViewGroupDetails.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ViewGroupDetails.this, ActivityToDoList.class);
                        startActivity(intentToDo);
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
