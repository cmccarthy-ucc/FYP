package com.example.ciara.drugsmart;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActivityAddGroup extends AppCompatActivity {

    EditText editTextGroupNo;
    EditText editTextBreed;
    EditText editTextSource;
    Button btnAddGroup;
    Spinner spinnerAnimalType;
    EditText editTextNotes;
    String userID;
//    RadioButton radioButtonYes;
//    RadioButton radioButtonNo;
    Boolean allVaccinated = false;

    ListView listViewGroups;


    //Variables for retrieving data from database
    DatabaseReference databaseAnimal;

    //https://www.youtube.com/watch?v=hwe1abDO2Ag
    private static final String TAG = "ActivityAddGroup";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    public static final String GROUP_ID = "com.example.ciara.drugsmart.groupid";
    public static final String GROUP_BREED = "com.example.ciara.drugsmart.groupbreed";
    public static final String GROUP_SOURCE = "com.example.ciara.drugsmart.groupsource";
    public static final String GROUP_DOB = "com.example.ciara.drugsmart.groupdob";
    public static final String GROUP_NOTES = "com.example.ciara.drugsmart.groupnotes";
    public static final String ANIMAL_TYPE = "com.example.ciara.drugsmart.animaltype";
    public static final String GROUP_NUMBER = "com.example.ciara.drugsmart.groupnumber";
    public static final String USER_ID = "com.example.ciara.drugsmart.userid";

    List<Group> groups;
    DatabaseReference databaseGroups;

    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userID = auth.getUid();



        databaseGroups = FirebaseDatabase.getInstance().getReference("groups");
        setContentView(R.layout.activity_add_group);

        mDisplayDate = (TextView) findViewById(R.id.textViewVaccinationDrug);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ActivityAddGroup.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "OnDateSet: date: " + dayOfMonth + "/" + month + "/" + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };
        //Retrieving data from database
        databaseAnimal = FirebaseDatabase.getInstance().getReference("groups");

        editTextBreed = findViewById(R.id.editTextBreed);
        editTextSource = (EditText) findViewById(R.id.editTextSource);
        editTextGroupNo = (EditText) findViewById(R.id.editTextGroupNo);
        editTextGroupNo.requestFocus();
        editTextNotes = (EditText) findViewById(R.id.textViewNotes);
        btnAddGroup = (Button) findViewById(R.id.btnAddGroup);
        spinnerAnimalType = (Spinner) findViewById(R.id.spinnerAnimalType);
//        radioButtonYes = (RadioButton) findViewById(R.id.radioButtonYes);
//        radioButtonNo = (RadioButton) findViewById(R.id.radioButtonNo);

        //Calling the method to add animal to the database
        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroup();
            }
        });

        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
//                    case R.id.animals:
//                        Toast.makeText(ActivityAddGroup.this, "Animals", Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(ActivityAddGroup.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityAddGroup.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityAddGroup.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityAddGroup.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityAddGroup.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityAddGroup.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityAddGroup.this, WelcomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityAddGroup.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityAddGroup.this, ActivityToDoDoses.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(ActivityAddGroup.this, "Drugs Available", Toast.LENGTH_SHORT).show();
                        Intent intentDrug = new Intent(ActivityAddGroup.this, AddDrug.class);
                        startActivity(intentDrug);
                        break;
                    case R.id.signOut:
                       auth.signOut();
                        startActivity(new Intent(ActivityAddGroup.this, Login.class));
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

        //list to store artists
        groups = new ArrayList<>();

        listViewGroups = findViewById(R.id.listViewGroups);
        listViewGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                Group group = groups.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), GroupActivity.class);

                //putting artist name and id to intent
                intent.putExtra(GROUP_ID, group.getGroupID());
                intent.putExtra(GROUP_DOB, group.getGroupDOB());
                intent.putExtra(GROUP_BREED, group.getGroupBreed());
                intent.putExtra(GROUP_SOURCE, group.getGroupSource());
                intent.putExtra(GROUP_NOTES, group.getGroupNotes());
                intent.putExtra(ANIMAL_TYPE, group.getGroupAnimalType());
                intent.putExtra(GROUP_NUMBER, group.getGroupNo());

                //starting the activity with intent
                startActivity(intent);
            }
        });

        listViewGroups.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Group group = groups.get(i);
                showUpdateDeleteDialog(group.getGroupID(), group.getGroupNo());
                return true;
            }
        });

    }
        @Override
        protected void onStart() {
            super.onStart();
            //attaching value event listener
            Query orderQuery = databaseGroups.orderByChild("userID").equalTo(user.getUid());

            orderQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //clearing the previous artist list
                    groups.clear();

                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        //getting artist
                        Group group = postSnapshot.getValue(Group.class);
                        //adding artist to the list
                        groups.add(group);
                    }

                    //creating adapter
                    GroupList groupAdapter = new GroupList(ActivityAddGroup.this, groups);
                    //attaching adapter to the listview
                    listViewGroups.setAdapter(groupAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        private void showUpdateDeleteDialog(final String artistId, String artistName) {

//            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//            LayoutInflater inflater = getLayoutInflater();
//            final View dialogView = inflater.inflate(R.layout.update_dialog, null);
//            dialogBuilder.setView(dialogView);
//
//            final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
//            final Spinner spinnerGenre = (Spinner) dialogView.findViewById(R.id.spinnerGenres);
//            final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateArtist);
//            final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteArtist);
//
//            dialogBuilder.setTitle(artistName);
//            final AlertDialog b = dialogBuilder.create();
//            b.show();


//        buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = editTextName.getText().toString().trim();
//                String genre = spinnerGenre.getSelectedItem().toString();
//                if (!TextUtils.isEmpty(name)) {
//                    updateGroup(artistId, name, genre);
//                    b.dismiss();
//                }
//            }
//        });


//        buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                deleteArtist(artistId);
//                b.dismiss();
//            }
//        });
        }




//    public void onRadioButtonClicked(View view) {
////            // Is the button now checked?
//            boolean checked = ((RadioButton) view).isChecked();
//
//            // Check which radio button was clicked
//            switch(view.getId()) {
//                case R.id.radioButtonNo:
//                    if (checked)
//                        allVaccinated = true;
//                    break;
//                case R.id.radioButtonYes:
//                    if (checked)
//                       allVaccinated = false;
//                        break;
//            }
//        }

    private void addGroup() {
        String breed = editTextBreed.getText().toString().trim();
        String animalType = spinnerAnimalType.getSelectedItem().toString();
        String source = editTextSource.getText().toString().trim();
        String DOB = mDisplayDate.getText().toString().trim();
        String notes = editTextNotes.getText().toString().trim();
        String userID1 = userID;
       // Boolean vaccinated = allVaccinated;

//

        //String DOB = mDisplayDate.getText();
        String groupNo = editTextGroupNo.getText().toString().trim();

        if (TextUtils.isEmpty(breed)) {
            Toast.makeText(this,"You should enter a breed", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(animalType)) {
            Toast.makeText(this, "You select an Animal Type", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(source)) {
            Toast.makeText(this, "You should enter a Source", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(groupNo)) {
            Toast.makeText(this, "You should enter a Group Number", Toast.LENGTH_LONG).show();
        } else if (groupNo.length() < 2) {
            Toast.makeText(this, "A Group Number must be greater than 2 digits", Toast.LENGTH_LONG).show();
            }
// else if (allVaccinated = null){
//            Toast.makeText(this, "You should enter a Group Number", Toast.LENGTH_LONG).show();
//
//        }
        else {
            String id = databaseAnimal.push().getKey();

            Group group = new Group(groupNo, animalType, breed, source, DOB, id, notes, userID1);

            databaseAnimal.child(groupNo).setValue(group);

            Toast.makeText(this, "Group added", Toast.LENGTH_LONG).show();

            //stackoverflow.com/questions/3053761/reload-activity-in-android
            finish();
            startActivity(getIntent());
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
