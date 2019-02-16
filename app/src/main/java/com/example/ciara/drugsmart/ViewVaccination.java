package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewVaccination extends AppCompatActivity {
    private static final String TAG = "ViewVaccination";

    //https://www.youtube.com/watch?v=2duc77R4Hqw
    //Youtube tutorial

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    TextView animalID;
    private ListView mListview;
    String animalIDText;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__vaccination);

        mListview = (ListView)findViewById(R.id.listViewVaccination);


        //declare the database reference object
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        animalID = (TextView)findViewById(R.id.TextViewAnimalID);
        Intent intent = getIntent();
        animalIDText = intent.getExtras().getString("VIEW_VACCINATION_ANIMAL_ID");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //This method is called initially when the activity is made and again if the data is changed or updated
                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
                        Toast.makeText(ViewVaccination.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(ViewVaccination.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ViewVaccination.this, "Vaccinations", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ViewVaccination.this, ActivityVaccinationHome.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ViewVaccination.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ViewVaccination.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ViewVaccination.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ViewVaccination.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void showData(DataSnapshot dataSnapshot){
        //dataSnapshot will create a snapshot of the database to allow for the extraction of certain data
        for ( DataSnapshot ds : dataSnapshot.getChildren()){

            VaccinationInformation vInfo = new VaccinationInformation();
            vInfo.setVaccinationAnimalID(ds.child(animalIDText)
                    .getValue(VaccinationInformation.class).getVaccinationAnimalID());
            vInfo.setAnimalTag(ds.child(animalIDText)
                    .getValue(VaccinationInformation.class).getAnimalTag());
            vInfo.setVaccinationAdmin(ds.child(animalIDText)
                    .getValue(VaccinationInformation.class).getVaccinationAdmin());
            vInfo.setVaccinationDate(ds.child(animalIDText)
                    .getValue(VaccinationInformation.class).getVaccinationDate());
            vInfo.setDrugType(ds.child(animalIDText)
                    .getValue(VaccinationInformation.class).getDrugType());
            vInfo.setDosage(ds.child(animalIDText)
                    .getValue(VaccinationInformation.class).getDosage());
            vInfo.setVaccinationID(ds.child(animalIDText)
                    .getValue(VaccinationInformation.class).getVaccinationID());


            Log.d(TAG, "showData: AnimalID " +  vInfo.getVaccinationAnimalID());
            Log.d(TAG, "showData: Tag " +  vInfo.getAnimalTag());
            Log.d(TAG, "showData: Admin " +  vInfo.getVaccinationAdmin());
            Log.d(TAG, "showData: Date " +  vInfo.getVaccinationDate());
            Log.d(TAG, "showData: Drug " +  vInfo.getDrugType());
            Log.d(TAG, "showData: Dosage " +  vInfo.getDosage());
            Log.d(TAG, "showData: VaccinationID " +  vInfo.getVaccinationID());

            ArrayList<String> array = new ArrayList<>();
            array.add(vInfo.getVaccinationAnimalID());
            array.add(vInfo.getAnimalTag());
            array.add(vInfo.getVaccinationAdmin());
            array.add(vInfo.getDrugType());
            array.add(vInfo.getVaccinationDate());
            array.add(vInfo.getDosage());
            array.add(vInfo.getVaccinationID());

            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListview.setAdapter(adapter);

        }



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
