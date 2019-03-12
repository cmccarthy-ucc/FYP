//package com.example.ciara.drugsmart;
//
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.design.widget.NavigationView;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class
//
//ViewGroupDetails extends AppCompatActivity {
//    //Declarations
//
//    TextView groupID;
//    TextView groupAnimalType;
//    TextView groupDOB;
//    TextView groupSource;
//    TextView groupBreed;
//    TextView groupNumber;
//
//
//    private Button buttonAddVaccination;
//    private Button btnViewVaccination;
//    private Button buttonAddDose;
//
//    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
//    private DrawerLayout dl;
//    private ActionBarDrawerToggle t;
//    private NavigationView nv;
//
//    ListView listViewVaccination;
//    List<GroupVaccination> groupVaccinations;
//    DatabaseReference databaseVaccinations;
//
//    public static final String GROUP_VACCINATION_ID = "com.example.ciara.drugsmart.vaccinationGroupID";
//    public static final String VACCINATION_ID = "com.example.ciara.drugsmart.vaccinationID";
//    public static final String VACCINATION_DRUG = "com.example.ciara.drugsmart.vaccinationDrug";
//    public static final String VACCINATION_ADMIN = "com.example.ciara.drugsmart.vaccinationAdmin";
//    public static final String VACCINATION_DOSAGE = "com.example.ciara.drugsmart.vaccinationDosage";
//    public static final String VACCINATION_DATE = "com.example.ciara.drugsmart.vaccinationDate";
//    public static final String VACCINATION_GROUP_NUMBER = "com.example.ciara.drugsmart.vaccinationGroupNumber";
//    public static final String VACCINATION_GROUP_NOTES = "com.example.ciara.drugsmart.vaccinationGroupNotes";
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_group_details);
//
//        groupID
//                = (TextView)findViewById(R.id.textViewGroupID);
//        groupAnimalType
//                = (TextView)findViewById(R.id.textViewAnimalType);
//        groupDOB = (TextView)findViewById(R.id.textViewGroupDOB);
//        groupSource = (TextView)findViewById(R.id.textViewGroupSource);
//        groupBreed = (TextView)findViewById(R.id.textViewGroupBreed);
//        groupNumber = (TextView)findViewById(R.id.textViewVaccinationDate);
//
//        /*//value passed from one activity to another
//        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
//        animalID.setText(TempHolder);
//*/
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        String groupIDText = extras.getString("EXTRA_GROUP_ID");
//        String groupNumberText = extras.getString("EXTRA_GROUP_NUMBER");
//        String groupAnimalTypeText = extras.getString("EXTRA_ANIMAL_TYPE");
//        String groupDOBText = extras.getString("EXTRA_DOB");
//        String groupSourceText = extras.getString("EXTRA_SOURCE");
//        String groupBreedText = extras.getString("EXTRA_BREED");
//
//
//
//
//
//        groupID.setText(groupIDText);
//        groupAnimalType.setText(groupAnimalTypeText);
//        groupDOB.setText(groupDOBText);
//        groupSource.setText(groupSourceText);
//        groupBreed.setText(groupBreedText);
//        groupNumber.setText(groupNumberText);
//
//        buttonAddVaccination = (Button)findViewById(R.id.buttonAddVaccination);
//        buttonAddVaccination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //Getting text to transfer to next activity
//
//                TextView groupID = (TextView) findViewById(R.id.textViewGroupID);
//                String groupIDText = (String) groupID.getText();
//
//                TextView groupNumber = (TextView) findViewById(R.id.textViewVaccinationDate);
//                String groupNumberText = (String) groupNumber.getText();
//
//                Intent vaccinationIntent = new Intent(ViewGroupDetails.this, ActivityAddGroupVaccination.class);
//                Bundle vaccinationExtras = new Bundle();
//                vaccinationExtras.putString("VACCINATION_EXTRA_ID",groupIDText);
//                vaccinationExtras.putString("VACCINATION_EXTRA_NUMBER",groupNumberText);
//
//                //Placing the string values into the intent for transfer
//                vaccinationIntent.putExtras(vaccinationExtras);
//                startActivity(vaccinationIntent);
//
//            }
//        });
//
//        buttonAddDose = (Button)findViewById(R.id.buttonAddDose);
//        buttonAddDose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Getting text to transfer to next activity
//
//                TextView groupID = (TextView) findViewById(R.id.textViewGroupID);
//                String groupIDText = (String) groupID.getText();
//
//                TextView groupNumber = (TextView) findViewById(R.id.textViewVaccinationDate);
//                String groupNumberText = (String) groupNumber.getText();
//
//                Intent doseIntent = new Intent(ViewGroupDetails.this, ActivityAddGroupDose.class);
//                Bundle doseExtras = new Bundle();
//                doseExtras.putString("DOSE_EXTRA_ID",groupIDText);
//                doseExtras.putString("DOSE_EXTRA_NUMBER",groupNumberText);
//
//                //Placing the string values into the intent for transfer
//                doseIntent.putExtras(doseExtras);
//                startActivity(doseIntent);
//
//            }
//        });
//
//        dl = (DrawerLayout)findViewById(R.id.activity_main);
//        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
//
//        dl.addDrawerListener(t);
//        t.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        nv = (NavigationView)findViewById(R.id.nv);
//        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                switch (id) {
//                    case R.id.animals:
//                        Toast.makeText(ViewGroupDetails.this, "Animals",Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(ViewGroupDetails.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
//                    case R.id.vaccinations:
//                        Toast.makeText(ViewGroupDetails.this, "Medical Records", Toast.LENGTH_SHORT).show();
//                        Intent intentVaccination = new Intent(ViewGroupDetails.this, ActivityMedicalRecords2.class);
//                        startActivity(intentVaccination);
//                        break;
//                    case R.id.groups:
//                        Toast.makeText(ViewGroupDetails.this, "Groups", Toast.LENGTH_SHORT).show();
//                        Intent intentGroups = new Intent(ViewGroupDetails.this, ActivityGroupHome.class);
//                        startActivity(intentGroups);
//                        break;
//                    case R.id.home:
//                        Toast.makeText(ViewGroupDetails.this, "Home", Toast.LENGTH_SHORT).show();
//                        Intent intentHome = new Intent(ViewGroupDetails.this, ActivityOptionsTwo.class);
//                        startActivity(intentHome);
//                        break;
//                    case R.id.todo:
//                        Toast.makeText(ViewGroupDetails.this, "To-Do List", Toast.LENGTH_SHORT).show();
//                        Intent intentToDo = new Intent(ViewGroupDetails.this, ActivityToDoList.class);
//                        startActivity(intentToDo);
//                        break;
//                    default:
//                        return true;
//                }
//                return true;
//            }
//        });
//        databaseVaccinations = FirebaseDatabase.getInstance().getReference("groupVaccinations");
//
//
//
//        //list to store artists
//        groupVaccinations = new ArrayList<>();
//
//        listViewVaccination = findViewById(R.id.listViewVaccinations);
//        listViewVaccination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //getting the selected artist
//                GroupVaccination groupVaccination = groupVaccinations.get(i);
//
//                //creating an intent
//                Intent intent = new Intent(getApplicationContext(), ViewVaccinationDetails.class);
//
//                //putting artist name and id to intent
//                intent.putExtra(GROUP_VACCINATION_ID, groupVaccination.getVaccinationID());
//                intent.putExtra(VACCINATION_ID, groupVaccination.getVaccinationID());
//                intent.putExtra(VACCINATION_DRUG, groupVaccination.getVaccinationDrug());
//                intent.putExtra(VACCINATION_DOSAGE, groupVaccination.getVaccinationDosage());
//                intent.putExtra(VACCINATION_ADMIN, groupVaccination.getVaccinationAdmin());
//                intent.putExtra(VACCINATION_DATE, groupVaccination.getVaccinationDate());
//                intent.putExtra(VACCINATION_GROUP_NUMBER, groupVaccination.getVaccinationGroupNumber());
//                intent.putExtra(VACCINATION_GROUP_NOTES, groupVaccination.getVaccinationNotes());
//
//                //starting the activity with intent
//                startActivity(intent);
//            }
//        });
//
//
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        //attaching value event listener
//        databaseVaccinations.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                //clearing the previous artist list
//                groupVaccinations.clear();
//
//                //iterating through all the nodes
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    //getting artist
//                    GroupVaccination groupVaccination = postSnapshot.getValue(GroupVaccination.class);
//                    //adding artist to the list
//                    groupVaccinations.add(groupVaccination);
//                }
//
//                //creating adapter
//                GroupVaccinationList groupAdapter = new GroupVaccinationList(ViewGroupDetails.this, groupVaccinations);
//                //attaching adapter to the listview
//                listViewVaccination.setAdapter(groupAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(t.onOptionsItemSelected(item))
//            return true;
//
//        return super.onOptionsItemSelected(item);
//    }
//}
