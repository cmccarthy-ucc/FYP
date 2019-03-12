package com.fyp.ciara.drugsmart;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.fyp.ciara.drugsmart.ActivityAddGroup.GROUP_ID;
import static com.fyp.ciara.drugsmart.ActivityAddGroup.GROUP_NUMBER;

public class GroupActivity extends AppCompatActivity {
    //Declarations

    TextView groupID;
    TextView groupAnimalType;
    TextView groupDOB;
    TextView groupSource;
    TextView groupBreed;
    TextView groupNumber;
    TextView mTextMessage;

    Button btnAdd;
    Button btnUpdate;
    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    Long time;

    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;

    String userID;
    //https://www.youtube.com/watch?v=hwe1abDO2Ag
    private static final String TAG = "GroupActivity";
    TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    ListView listViewVaccination;
    List<GroupVaccination> groupVaccinations;
    DatabaseReference databaseVaccinations;

    List<Dosing> groupDoses;
    DatabaseReference databaseDoses;

    List<Animal> groupIndividuals;
    DatabaseReference databaseIndividuals;

    public static final String GROUP_VACCINATION_ID = "com.example.ciara.drugsmart.vaccinationGroupID";
    public static final String VACCINATION_ID = "com.example.ciara.drugsmart.vaccinationID";
    public static final String VACCINATION_DRUG = "com.example.ciara.drugsmart.vaccinationDrug";
    public static final String VACCINATION_ADMIN = "com.example.ciara.drugsmart.vaccinationAdmin";
    public static final String VACCINATION_DOSAGE = "com.example.ciara.drugsmart.vaccinationDosage";
    public static final String VACCINATION_DATE = "com.example.ciara.drugsmart.vaccinationDate";
    public static final String VACCINATION_GROUP_NUMBER = "com.example.ciara.drugsmart.vaccinationGroupNumber";
    public static final String VACCINATION_GROUP_NOTES = "com.example.ciara.drugsmart.vaccinationGroupNotes";
    public static final String ALL_VACCINATED = "com.example.ciara.drugsmart.allVaccinated";

    public static final String GROUP_DOSE_ID = "com.example.ciara.drugsmart.doseGroupID";
    public static final String DOSE_ID = "com.example.ciara.drugsmart.doseID";
    public static final String DOSE_DRUG = "com.example.ciara.drugsmart.doseDrug";
    public static final String DOSE_ADMIN = "com.example.ciara.drugsmart.doseAdmin";
    public static final String DOSE_DOSAGE = "com.example.ciara.drugsmart.doseDosage";
    public static final String DOSE_DATE = "com.example.ciara.drugsmart.doseDate";
    public static final String DOSE_GROUP_NUMBER = "com.example.ciara.drugsmart.doseGroupNumber";
    public static final String DOSE_NOTES = "com.example.ciara.drugsmart.doseGroupNotes";
    public static final String ALL_DOSED = "com.example.ciara.drugsmart.allDosed";
    public static final String DOSE_TYPE = "com.example.ciara.drugsmart.doseType";

    public static final String GROUP_INDIVIDUAL_ID = "com.example.ciara.drugsmart.individualGroupID";
    public static final String INDIVIDUAL_ID = "com.example.ciara.drugsmart.individualID";
    public static final String INDIVIDUAL_GENDER = "com.example.ciara.drugsmart.individualGender";
    public static final String INDIVIDUAL_SOURCE = "com.example.ciara.drugsmart.individualSource";
    public static final String INDIVIDUAL_TAG = "com.example.ciara.drugsmart.individualTag";
    public static final String INDIVIDUAL_BREED = "com.example.ciara.drugsmart.individualBreed";
    public static final String INDIVIDUAL_DOB = "com.example.ciara.drugsmart.individualDOB";
    public static final String INDIVIDUAL_NOTES = "com.example.ciara.drugsmart.individualNotes";
    public static final String INDIVIDUAL_GROUP_NUMBER = "com.example.ciara.drugsmart.individualGroupNumber";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        mTextMessage = findViewById(R.id.textViewTitle);
        btnAdd = findViewById(R.id.buttonAdd);
        btnUpdate = findViewById(R.id.buttonUpdate);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userID = auth.getUid();

        time = System.currentTimeMillis()/1000;

        groupID
                = (TextView)findViewById(R.id.textViewGroupID);
        groupAnimalType
                = (TextView)findViewById(R.id.textViewGender);
        groupDOB = (TextView)findViewById(R.id.textViewDate);
        groupSource = (TextView)findViewById(R.id.textViewSourceFarmer);
        groupBreed = (TextView)findViewById(R.id.textViewBreed);
        groupNumber = (TextView)findViewById(R.id.textViewGroupNumber);
        groupID = findViewById(R.id.textView55);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String groupIDText = extras.getString(GROUP_ID);
        final String groupNumberText = extras.getString(ActivityAddGroup.GROUP_NUMBER);
        final String groupAnimalTypeText = extras.getString(ActivityAddGroup.ANIMAL_TYPE);
        final String groupDOBText = extras.getString(ActivityAddGroup.GROUP_DOB);
        final String groupSourceText = extras.getString(ActivityAddGroup.GROUP_SOURCE);
        final String groupBreedText = extras.getString(ActivityAddGroup.GROUP_BREED);
        final String groupNotesText = extras.getString(ActivityAddGroup.GROUP_NOTES);
        final String userIDText = userID;

        groupID.setText(groupIDText);
        groupAnimalType.setText(groupAnimalTypeText);
        groupDOB.setText(groupDOBText);
        groupSource.setText(groupSourceText);
        groupBreed.setText(groupBreedText);
        groupNumber.setText(groupNumberText);
        groupID.setText(groupIDText);

        databaseDoses = FirebaseDatabase.getInstance().getReference("groupDoses").child(intent.getStringExtra(ActivityAddGroup.GROUP_ID));
        groupDoses = new ArrayList<>();

        databaseVaccinations = FirebaseDatabase.getInstance().getReference("groupVaccinations").child(intent.getStringExtra(ActivityAddGroup.GROUP_ID));
        groupVaccinations = new ArrayList<>();

        databaseIndividuals = FirebaseDatabase.getInstance().getReference("animal").child(intent.getStringExtra(ActivityAddGroup.GROUP_ID));
        groupIndividuals = new ArrayList<>();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDeleteDialog(groupNumberText, groupAnimalTypeText, groupBreedText, groupSourceText, groupDOBText, groupIDText, groupNotesText, userIDText);
            }

        });

       listViewVaccination = findViewById(R.id.listViewVaccinations);
//        listViewVaccination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //getting the selected artist
//                GroupVaccination groupVaccination = groupVaccinations.get(i);
//
//                //creating an intent
//                Intent intent = new Intent(getApplicationContext(), GroupVaccinationDetailsActivity.class);
//
//                //putting artist name and id to intent
//                intent.putExtra(GROUP_VACCINATION_ID, groupVaccination.getVaccinationID());
//                intent.putExtra(GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
//                intent.putExtra(VACCINATION_ID, groupVaccination.getVaccinationID());
//                intent.putExtra(VACCINATION_DRUG, groupVaccination.getVaccinationDrug());
//                intent.putExtra(VACCINATION_DOSAGE, groupVaccination.getVaccinationDosage());
//                intent.putExtra(VACCINATION_ADMIN, groupVaccination.getVaccinationAdmin());
//                intent.putExtra(VACCINATION_DATE, groupVaccination.getVaccinationDate());
//                intent.putExtra(VACCINATION_GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
//                intent.putExtra(VACCINATION_GROUP_NOTES, groupVaccination.getVaccinationNotes());
//                intent.putExtra(ALL_VACCINATED, groupVaccination.getAllVaccinated());
//
//                //starting the activity with intent
//                startActivity(intent);
//            }
//        });

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.group_navigation_vaccination:
                    listViewVaccination.setAdapter(null);
                       groupVaccinations.clear();
//                        Query dateQuery = databaseVaccinations.orderByChild("timeStamp").startAt(time);
//                        dateQuery.addValueEventListener(new ValueEventListener() {

                                                    databaseVaccinations.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()){
                                    GroupVaccination groupVaccination = groupVaccinationSnapshot.getValue(GroupVaccination.class);
                                    groupVaccinations.add(groupVaccination);
                                }
                                Collections.reverse(groupVaccinations);
                                GroupVaccinationList groupVaccinationInfoAdapter = new GroupVaccinationList(GroupActivity.this, groupVaccinations);
                                listViewVaccination.setAdapter(groupVaccinationInfoAdapter);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        mTextMessage.setText("Vaccinations");
                        btnAdd.setText("Add Vaccination");
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), GroupVaccinationActivity.class);

                                //putting artist name and id to intent
                                intent.putExtra(GROUP_ID, groupIDText);
                                intent.putExtra(GROUP_NUMBER, groupNumberText);

                                //starting the activity with intent
                                startActivity(intent);
                            }
                        });

                        listViewVaccination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                //getting the selected artist
                                GroupVaccination groupVaccination = groupVaccinations.get(i);
                                //creating an intent
                                Intent intent = new Intent(getApplicationContext(), GroupVaccinationDetailsActivity.class);
                                //putting artist name and id to intent
                                intent.putExtra(GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
                                intent.putExtra(VACCINATION_ID, groupVaccination.getVaccinationID());
                                intent.putExtra(VACCINATION_DRUG, groupVaccination.getVaccinationDrug());
                                intent.putExtra(VACCINATION_DOSAGE, groupVaccination.getVaccinationDosage());
                                intent.putExtra(VACCINATION_ADMIN, groupVaccination.getVaccinationAdmin());
                                intent.putExtra(VACCINATION_DATE, groupVaccination.getVaccinationDate());
                                intent.putExtra(VACCINATION_GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
                                intent.putExtra(VACCINATION_GROUP_NOTES, groupVaccination.getVaccinationNotes());
                                intent.putExtra(ALL_VACCINATED, groupVaccination.getAllVaccinated().toString());
                                //intent.putExtra(GROUP_VACCINATION_ID, groupVaccination.getVaccinationGroupID());
                                intent.putExtra("GROUP_ID", groupIDText);

                                        //starting the activity with intent
                                        startActivity(intent);
                                    }
                                });
                        return true;

                    case R.id.group_navigation_doses:
                        listViewVaccination.setAdapter(null);
                        groupDoses.clear();
                        //Query doseQuery = databaseDoses.orderByChild("timeStamp").startAt(time);
                        //doseQuery.addValueEventListener(new ValueEventListener() {

                       databaseDoses.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot dosingSnapshot : dataSnapshot.getChildren()){
                                    Dosing groupDose = dosingSnapshot.getValue(Dosing.class);
                                    groupDoses.add(groupDose);
                                }
                                Collections.reverse(groupDoses);
                                GroupDoseList groupDoseInfoAdapter = new GroupDoseList(GroupActivity.this, groupDoses);
                                listViewVaccination.setAdapter(groupDoseInfoAdapter);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        mTextMessage.setText("Doses");
                        btnAdd.setText("Add Dose");
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), GroupDoseActivity.class);

                                //putting artist name and id to intent
                                intent.putExtra(GROUP_ID, groupIDText);
                                intent.putExtra(GROUP_NUMBER, groupNumberText);

                                //starting the activity with intent
                                startActivity(intent);
                            }
                        });
                        listViewVaccination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                //getting the selected artist
                                Dosing doses = groupDoses.get(i);
                                //creating an intent
                                Intent intent = new Intent(getApplicationContext(), ActivityViewDoseDetails.class);
                                //putting artist name and id to intent
                                intent.putExtra(GROUP_NUMBER, doses.getDoseGroupNumber());
                                intent.putExtra(DOSE_ID, doses.getDoseID());
                                intent.putExtra(DOSE_DRUG, doses.getDoseDrug());
                                intent.putExtra(DOSE_DOSAGE, doses.getDoseDosage());
                                intent.putExtra(DOSE_ADMIN, doses.getDoseAdmin());
                                intent.putExtra(DOSE_DATE, doses.getDoseDate());
                                intent.putExtra(DOSE_GROUP_NUMBER, doses.getDoseGroupNumber());
                                intent.putExtra(DOSE_NOTES, doses.getDoseNotes());
                                intent.putExtra(DOSE_TYPE, doses.getDoseType());
                                intent.putExtra(ALL_DOSED, doses.getAllDosed().toString());
                                intent.putExtra("GROUP_ID", groupIDText);
                                //starting the activity with intent
                                startActivity(intent);
                            }
                        });

                        return true;
                    case R.id.group_navigation_singles:
                       listViewVaccination.setAdapter(null);
                       groupIndividuals.clear();
                        databaseIndividuals.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot groupIndividualSnapshot : dataSnapshot.getChildren()){
                                    Animal animal = groupIndividualSnapshot.getValue(Animal.class);
                                    groupIndividuals.add(animal);
                                }
                                GroupIndividualList groupIndividualInfoAdapter = new GroupIndividualList(GroupActivity.this, groupIndividuals);
                                listViewVaccination.setAdapter(groupIndividualInfoAdapter);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                       mTextMessage.setText("Treated Singles");
                        btnAdd.setText("Add Single");
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), AnimalActivity.class);

                                //putting artist name and id to intent
                                intent.putExtra(GROUP_ID, groupIDText);
                                intent.putExtra(GROUP_NUMBER, groupNumberText);

                                //starting the activity with intent
                                startActivity(intent);
                            }
                        });

                        listViewVaccination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                //getting the selected artist
                                Animal animals = groupIndividuals.get(i);
                                //creating an intent
                                Intent intent = new Intent(getApplicationContext(), IndividualDetails.class);
                                //putting artist name and id to intent
                                intent.putExtra(INDIVIDUAL_GROUP_NUMBER, animals.getGroupNumber());
                                intent.putExtra(INDIVIDUAL_ID, animals.getAnimalID());
                                intent.putExtra(INDIVIDUAL_BREED, animals.getAnimalBreed());
                                intent.putExtra(INDIVIDUAL_DOB, animals.getAnimalDOB());
                                intent.putExtra(INDIVIDUAL_GENDER, animals.getAnimalGender());
                                intent.putExtra(INDIVIDUAL_SOURCE, animals.getAnimalSource());
                                intent.putExtra(INDIVIDUAL_TAG, animals.getAnimalTag());
                                intent.putExtra(INDIVIDUAL_NOTES, animals.getNotes());
                                //starting the activity with intent
                                startActivity(intent);
                            }
                        });

                        return true;
                }
                return false;
            }
        };
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
//                    case R.id.animals:
//                        Toast.makeText(GroupActivity.this, "Animals",Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(GroupActivity.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
                    case R.id.vaccinations:
                        Toast.makeText(GroupActivity.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(GroupActivity.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(GroupActivity.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(GroupActivity.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(GroupActivity.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(GroupActivity.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(GroupActivity.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(GroupActivity.this, ActivityToDoDoses.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(GroupActivity.this, "Drugs", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(GroupActivity.this, AddDrug.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(GroupActivity.this, Login.class));
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;

            }

        });

        btnAdd.setText("Add Vaccination");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GroupVaccinationActivity.class);

                //putting artist name and id to intent
                intent.putExtra(GROUP_ID, groupIDText);
                intent.putExtra(GROUP_NUMBER, groupNumberText);

                //starting the activity with intent
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String groupIDText = extras.getString(GROUP_ID);
        final String groupNumberText = extras.getString(ActivityAddGroup.GROUP_NUMBER);
        listViewVaccination.setAdapter(null);
        groupVaccinations.clear();
       //Query dateQuery = databaseVaccinations.orderByChild("timeStamp").startAt(time);
//        dateQuery.addValueEventListener(new ValueEventListener() {
            databaseVaccinations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot groupVaccinationSnapshot : dataSnapshot.getChildren()){
                    GroupVaccination groupVaccination = groupVaccinationSnapshot.getValue(GroupVaccination.class);
                    groupVaccinations.add(groupVaccination);
                }
                Collections.reverse(groupVaccinations);
                GroupVaccinationList groupVaccinationInfoAdapter = new GroupVaccinationList(GroupActivity.this, groupVaccinations);
                listViewVaccination.setAdapter(groupVaccinationInfoAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mTextMessage.setText("Vaccinations");
        btnAdd.setText("Add Vaccination");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GroupVaccinationActivity.class);

                //putting artist name and id to intent
                intent.putExtra(GROUP_ID, groupIDText);
                intent.putExtra(GROUP_NUMBER, groupNumberText);

                //starting the activity with intent
                startActivity(intent);
            }
        });

        listViewVaccination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist

                GroupVaccination groupVaccination = groupVaccinations.get(i);
                //creating an intent
                Intent intent = new Intent(getApplicationContext(), GroupVaccinationDetailsActivity.class);
                //putting artist name and id to intent
                intent.putExtra("GROUP_ID", groupIDText);
                intent.putExtra(GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
                intent.putExtra(VACCINATION_ID, groupVaccination.getVaccinationID());
                intent.putExtra(VACCINATION_DRUG, groupVaccination.getVaccinationDrug());
                intent.putExtra(VACCINATION_DOSAGE, groupVaccination.getVaccinationDosage());
                intent.putExtra(VACCINATION_ADMIN, groupVaccination.getVaccinationAdmin());
                intent.putExtra(VACCINATION_DATE, groupVaccination.getVaccinationDate());
                intent.putExtra(VACCINATION_GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
                intent.putExtra(VACCINATION_GROUP_NOTES, groupVaccination.getVaccinationNotes());
                intent.putExtra(ALL_VACCINATED, groupVaccination.getAllVaccinated().toString());
                //starting the activity with intent
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }



    private void showUpdateDeleteDialog(String groupNo, String groupAnimalType, String groupBreed, String groupSource, String groupDOB, String groupID, String groupNotes, String userID) {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String groupIDText = extras.getString(GROUP_ID);
        final String groupNumberText = extras.getString(ActivityAddGroup.GROUP_NUMBER);
        String groupAnimalTypeText = extras.getString(ActivityAddGroup.ANIMAL_TYPE);
        String groupDOBText = extras.getString(ActivityAddGroup.GROUP_DOB);
        String groupSourceText = extras.getString(ActivityAddGroup.GROUP_SOURCE);
        String groupBreedText = extras.getString(ActivityAddGroup.GROUP_BREED);
        String groupNotesText = extras.getString(ActivityAddGroup.GROUP_NOTES);
        final String userIDText = userID;


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_group, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextSource = dialogView.findViewById(R.id.editTextSource);
        editTextSource.setText(groupSourceText);
        final EditText editTextBreed = dialogView.findViewById(R.id.editTextBreed);
        editTextBreed.setText(groupBreedText);
        final EditText editTextNotes = dialogView.findViewById(R.id.editTextNotes);
        editTextNotes.setText(groupNotesText);
        final TextView textViewDOB = dialogView.findViewById(R.id.textViewDate);
        textViewDOB.setText(groupDOB);
        final Spinner spinnerTypes = (Spinner) dialogView.findViewById(R.id.spinnerType);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateArtist);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteArtist);

        dialogBuilder.setTitle("GroupNumber:   " + groupNumberText);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groupNo = groupNumberText;
                String groupID = groupIDText;
                String source = editTextSource.getText().toString().trim();
                String breed = editTextBreed.getText().toString().trim();
                String dob = textViewDOB.getText().toString().trim();
                String notes = editTextNotes.getText().toString().trim();
                String type = spinnerTypes.getSelectedItem().toString();
                String userID = userIDText;
                if (!TextUtils.isEmpty(breed)) {
                    updateGroup(groupNo, type, breed, source, dob, groupID, notes, userID);
                    b.dismiss();
                }
                finish();
                startActivity(getIntent());
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groupNo = groupNumberText;
                String groupID = groupIDText;
                String source = editTextSource.getText().toString().trim();
                String breed = editTextBreed.getText().toString().trim();
                String dob = textViewDOB.getText().toString().trim();
                String notes = editTextNotes.getText().toString().trim();
                String type = spinnerTypes.getSelectedItem().toString();

                deleteGroup(groupNo, type, breed, source, dob, groupID, notes);
                b.dismiss();
            }
        });
    }
    private boolean updateGroup(String groupNo, String groupAnimalType, String groupBreed,
                                String groupSource, String groupDOB,
                                String groupID, String groupNotes, String userID) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("groups").child(groupNo);

        //updating artist
        Group group = new Group(groupNo, groupAnimalType, groupBreed, groupSource, groupDOB, groupID, groupNotes,userID );
        dR.setValue(group);
        Toast.makeText(getApplicationContext(), "Animal Updated", Toast.LENGTH_LONG).show();


        return true;


    }

    private boolean deleteGroup(String groupNo, String groupAnimalType, String groupBreed, String groupSource, String groupDOB, String groupID, String groupNotes) {

        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("groups").child(groupNo);

        //removing artist
        dR.removeValue();

        //getting the tracks reference for the specified artist
        DatabaseReference drGroupVaccinations = FirebaseDatabase.getInstance().getReference("groupVaccinations").child(groupID);

        //removing all tracks
        drGroupVaccinations.removeValue();
        Toast.makeText(getApplicationContext(), "Group Deleted", Toast.LENGTH_LONG).show();

        return true;
    }
//https://stackoverflow.com/questions/41744219/how-to-highlight-the-item-when-pressed-using-bottomnavigationview-between-activi

}

