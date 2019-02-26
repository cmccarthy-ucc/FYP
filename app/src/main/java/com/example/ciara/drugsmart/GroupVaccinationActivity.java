package com.example.ciara.drugsmart;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_ID;

public class GroupVaccinationActivity extends AppCompatActivity {

    TextView groupID;
    TextView groupNumber;
    Button buttonAddVaccination;
    Button buttonAddReminder;
    Spinner vaccinationDrug;
    EditText vaccinationAdmin;
    EditText vaccinationDosage;
    EditText vaccinationNotes;
    RadioButton radioButtonYes;
    RadioButton radioButtonNo;
    Boolean allVaccinated = true;


    DatabaseReference databaseGroupVaccination;

    ListView listViewGroupVaccination;
    List<GroupVaccination> groupVaccinations;

    //https://www.youtube.com/watch?v=hwe1abDO2Ag
    private static final String TAG = "MainActivity";
    private TextView vaccinationDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_vaccination);


        vaccinationDrug = (Spinner) findViewById(R.id.spinnerDrug);
        vaccinationAdmin = (EditText) findViewById(R.id.editAdmin);
        vaccinationDosage = (EditText) findViewById(R.id.textViewDosage);
        vaccinationNotes = (EditText) findViewById(R.id.textViewNotes);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String groupIDText = extras.getString(GROUP_ID);
        final String groupNumberText = extras.getString(ActivityAddGroup.GROUP_NUMBER);


        groupID = (TextView) findViewById(R.id.textViewVaccinationID);
        groupNumber = (TextView) findViewById(R.id.TextViewGroupNumber);

        groupID.setText(groupIDText);
        groupNumber.setText(groupNumberText);

        databaseGroupVaccination = FirebaseDatabase.getInstance().getReference("groupVaccinations").child(intent.getStringExtra(ActivityAddGroup.GROUP_ID));
        groupVaccinations = new ArrayList<>();




        buttonAddVaccination = (Button) findViewById(R.id.buttonAddVaccination);
        buttonAddVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calling the addVaccination method to add vaccination record to the database
                addVaccination();

                Intent intent = new Intent(GroupVaccinationActivity.this, GroupActivity.class);


            }
        });


        vaccinationDate = (TextView) findViewById(R.id.vaccinationDate);
        vaccinationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        GroupVaccinationActivity.this,
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
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
                //Date date = new Date( dayOfMonth+ "/" + month + "/" + year);
//                dateVaccination = date;
//                vaccinationDate.setText(dateFormat.format(date));
                String date = dayOfMonth + "/" + month + "/" + year;
                vaccinationDate.setText(date);




            }
        };
        radioButtonYes = (RadioButton) findViewById(R.id.radioButtonYes);
        radioButtonNo = (RadioButton) findViewById(R.id.radioButtonNo);

        //https://stackoverflow.com/questions/8323778/how-to-set-onclicklistener-on-a-radiobutton-in-android
        // **Origional source but updated code**

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupAllVaccinated);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonNo.isChecked()){
                    allVaccinated = false;

                }
                else if (radioButtonYes.isChecked()){
                    allVaccinated = true;
                }
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
                        Toast.makeText(GroupVaccinationActivity.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(GroupVaccinationActivity.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(GroupVaccinationActivity.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(GroupVaccinationActivity.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(GroupVaccinationActivity.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(GroupVaccinationActivity.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(GroupVaccinationActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(GroupVaccinationActivity.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(GroupVaccinationActivity.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(GroupVaccinationActivity.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

    }
    private void addVaccination(){
        String number = groupNumber.getText().toString().trim();
        String drug = vaccinationDrug.getSelectedItem().toString();
        String date = vaccinationDate.getText().toString().trim();
        String admin = vaccinationAdmin.getText().toString().trim();
        String dosage = vaccinationDosage.getText().toString().trim();
        String notes = vaccinationNotes.getText().toString().trim();

//        if (TextUtils.isEmpty(date)) {
//            Toast.makeText(this,"Please select a date", Toast.LENGTH_LONG).show();
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(this,"Please select a date", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(dosage)) {
            Toast.makeText(this, "Please enter a dosage amount", Toast.LENGTH_LONG).show();
        }
        else if (radioButtonNo.isChecked() & (TextUtils.isEmpty(notes))){
            Toast.makeText(this, "Please note which animals were not vaccinated", Toast.LENGTH_LONG).show();


        }
        else{
            String id = databaseGroupVaccination.push().getKey();

            GroupVaccination groupVaccination = new GroupVaccination(number, id, drug, admin, dosage, date, notes,allVaccinated );

            databaseGroupVaccination.child(id).setValue(groupVaccination);

            Toast.makeText(this, "Vaccination added", Toast.LENGTH_LONG).show(); }

        finish();
        startActivity(getIntent());



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


}
