package com.example.ciara.drugsmart;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class AddVaccination extends AppCompatActivity {

    //TextView declarations to correspond with XML file

    TextView animalID;
    TextView animalTag;
    Button buttonAddVaccination;
    Button buttonAddReminder;
    Spinner vaccinationDrug;
    EditText vaccinationAdmin;
    EditText vaccinationDosage;

    //https://www.youtube.com/watch?v=hwe1abDO2Ag

    private static final String TAG = "MainActivity";
    private TextView vaccinationDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    DatabaseReference databaseVaccination;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccincation);

        vaccinationDrug = (Spinner)findViewById(R.id.spinnerDrug);
        vaccinationAdmin = (EditText)findViewById(R.id.editAdmin);
        vaccinationDosage = (EditText) findViewById(R.id.textViewDosage);


        animalID = (TextView)findViewById(R.id.textViewAnimalID);
        animalTag = (TextView)findViewById(R.id.textViewAnimalTag);



        Intent vaccinationIntent = getIntent();
        Bundle vaccinationExtras = vaccinationIntent.getExtras();
        String animalIDText = vaccinationExtras.getString("VACCINATION_EXTRA_ID");
        String animalDOBText = vaccinationExtras.getString("VACCINATION_EXTRA_TAG");

        animalID.setText(animalIDText);
        animalTag.setText(animalDOBText);

        buttonAddVaccination = (Button) findViewById(R.id.buttonAddTreatment);
        buttonAddVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calling the addVaccination method to add vaccination record to the database
                addVaccination();
                //Also adding a notification when a vaccination is added




            }
        });

        buttonAddReminder = (Button) findViewById(R.id.buttonAddReminder);
        buttonAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Another way attempted
                //addNotification();

                //https://www.youtube.com/watch?v=1fV9NmvxXJo
                //Time based reminder
                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY, 10);
                calendar.set(Calendar.MINUTE, 55);

                Intent intent = new Intent(getApplicationContext(), NotificationService.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

                //getTimeInMillis - this is when the notification will be triggered (if statement for conditional reminder??
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


            }
        });

        databaseVaccination = FirebaseDatabase.getInstance().getReference("vaccination");

        /*Bundle bundle = getIntent().getExtras();
        animalID.setText(bundle.getString("AnimalID"));*/

        // Receiving value into activity using intent.
        //String TempHolder = getIntent().getStringExtra("ListViewClickedValue");

        // Setting up received value into EditText.
        //animalID.setText(TempHolder);

        //https://www.youtube.com/watch?v=hwe1abDO2Ag
        //Select administration of vaccination date
        vaccinationDate = (TextView)findViewById(R.id.treatmentDate);
        vaccinationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddVaccination.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener, year,month, day);
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1 ;
                Log.d(TAG, "OnDateSet: date: " + dayOfMonth + "/" + month + "/" + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                vaccinationDate.setText(date);


            }
        };

        //Navigation Drawer start

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
                        Toast.makeText(AddVaccination.this, "Animals",Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(AddVaccination.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(AddVaccination.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(AddVaccination.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(AddVaccination.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(AddVaccination.this, ActivityGroupHome.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(AddVaccination.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(AddVaccination.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(AddVaccination.this,"To-DO List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(AddVaccination.this, ActivityToDoList.class);
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
        String idAnimal = animalID.getText().toString().trim();
        String tag = animalTag.getText().toString().trim();
        String drug = vaccinationDrug.getSelectedItem().toString();
        String date = vaccinationDate.getText().toString().trim();
        String admin = vaccinationAdmin.getText().toString().trim();
        String dosage = vaccinationDosage.getText().toString().trim();
        String notes = "test";
        String group = "test";

        if(!TextUtils.isEmpty(tag)){

            String id = databaseVaccination.push().getKey();

            Vaccination vaccination = new Vaccination(idAnimal, id, drug, admin, dosage, date);

            databaseVaccination.child(id).setValue(vaccination);

            Toast.makeText(this, "Vaccination added", Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this, "Please select admin", Toast.LENGTH_LONG).show();
        }
    }


    //Creates and displays notification

    private void addNotification(){

        //https://www.youtube.com/watch?v=ATERxKKORbY
        //Builds notification

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Vaccination")
                .setContentText("Your animal will be out of the withdrawal period in 2 weeks");

        //intent needed to display the notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(
                contentIntent
        );

        //Adds notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

        Toast.makeText(this, "Notification Added", Toast.LENGTH_LONG).show();



    }
    //Method for navigation drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
