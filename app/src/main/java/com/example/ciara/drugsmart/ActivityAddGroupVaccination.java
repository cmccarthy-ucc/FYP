//package com.example.ciara.drugsmart;
//
//import android.app.AlarmManager;
//import android.app.DatePickerDialog;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.support.v4.app.NotificationCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.Calendar;
//import java.util.Date;
//
//public class ActivityAddGroupVaccination extends AppCompatActivity {
//
//    //TextView declarations to correspond with XML file
//
//    TextView groupID;
//    TextView groupNumber;
//    Button buttonAddVaccination;
//    Button buttonAddReminder;
//    Spinner vaccinationDrug;
//    EditText vaccinationAdmin;
//    EditText vaccinationDosage;
//
//    //https://www.youtube.com/watch?v=hwe1abDO2Ag
//    private static final String TAG = "MainActivity";
//    private TextView vaccinationDate;
//    private DatePickerDialog.OnDateSetListener mDateSetListener;
//
//    DatabaseReference databaseVaccination;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_group_vaccination);
//
//        vaccinationDrug = (Spinner)findViewById(R.id.spinnerDrug);
//        vaccinationAdmin = (EditText)findViewById(R.id.editAdmin);
//        vaccinationDosage = (EditText) findViewById(R.id.editDosage);
//
//
//        groupID = (TextView)findViewById(R.id.TextViewAnimalID);
//        groupNumber = (TextView)findViewById(R.id.TextViewAnimalTag);
//
//        Intent vaccinationIntent = getIntent();
//        Bundle vaccinationExtras = vaccinationIntent.getExtras();
//        String groupIDText = vaccinationExtras.getString("VACCINATION_EXTRA_ID");
//        String animalDOBText = vaccinationExtras.getString("VACCINATION_EXTRA_TAG");
//
//        animalID.setText(animalIDText);
//        animalTag.setText(animalDOBText);
//
//        buttonAddVaccination = (Button) findViewById(R.id.buttonAddVaccination);
//        buttonAddVaccination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Calling the addVaccination method to add vaccination record to the database
//                addVaccination();
//                //Also adding a notification when a vaccination is added
//
//
//
//
//            }
//        });
//
//        buttonAddReminder = (Button) findViewById(R.id.buttonAddReminder);
//        buttonAddReminder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Another way attempted
//                //addNotification();
//
//                //https://www.youtube.com/watch?v=1fV9NmvxXJo
//                //Time based reminder
//                Calendar calendar = Calendar.getInstance();
//
//                calendar.set(Calendar.HOUR_OF_DAY, 10);
//                calendar.set(Calendar.MINUTE, 55);
//
//                Intent intent = new Intent(getApplicationContext(), NotificationService.class);
//
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100, intent,
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//
//                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//
//                //getTimeInMillis - this is when the notification will be triggered (if statement for conditional reminder??
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//
//
//            }
//        });
//
//        databaseVaccination = FirebaseDatabase.getInstance().getReference("vaccination");
//
//        /*Bundle bundle = getIntent().getExtras();
//        animalID.setText(bundle.getString("AnimalID"));*/
//
//        // Receiving value into activity using intent.
//        //String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
//
//        // Setting up received value into EditText.
//        //animalID.setText(TempHolder);
//
//        //https://www.youtube.com/watch?v=hwe1abDO2Ag
//        //Select administration of vaccination date
//        vaccinationDate = (TextView)findViewById(R.id.vaccinationDate);
//        vaccinationDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(
//                        AddVaccination.this,
//                        android.R.style.Theme_Holo_Dialog_MinWidth,
//                        mDateSetListener, year,month, day);
//                dialog.getDatePicker().setMaxDate(new Date().getTime());
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//
//
//            }
//        });
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month = month +1 ;
//                Log.d(TAG, "OnDateSet: date: " + dayOfMonth + "/" + month + "/" + year);
//                String date = dayOfMonth + "/" + month + "/" + year;
//                vaccinationDate.setText(date);
//
//
//            }
//        };
//
//    }
//    private void addVaccination(){
//        String idAnimal = animalID.getText().toString().trim();
//        String tag = animalTag.getText().toString().trim();
//        String drug = vaccinationDrug.getSelectedItem().toString();
//        String date = vaccinationDate.getText().toString().trim();
//        String admin = vaccinationAdmin.getText().toString().trim();
//        String dosage = vaccinationDosage.getText().toString().trim();
//
//        if(!TextUtils.isEmpty(tag)){
//
//            String id = databaseVaccination.push().getKey();
//
//            Vaccination vaccination = new Vaccination(idAnimal, tag, id, drug, admin, dosage, date);
//
//            databaseVaccination.child(id).setValue(vaccination);
//
//            Toast.makeText(this, "Vaccination added", Toast.LENGTH_LONG).show();
//
//        } else{
//            Toast.makeText(this, "Please select admin", Toast.LENGTH_LONG).show();
//        }
//    }
//
//
//    //Creates and displays notification
//
//    private void addNotification(){
//
//        //https://www.youtube.com/watch?v=ATERxKKORbY
//        //Builds notification
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle("Vaccination")
//                .setContentText("Your animal will be out of the withdrawal period in 2 weeks");
//
//        //intent needed to display the notification
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(
//                contentIntent
//        );
//
//        //Adds notification
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());
//
//        Toast.makeText(this, "Notification Added", Toast.LENGTH_LONG).show();
//
//
//
//
//    }