//package com.example.ciara.drugsmart;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class ActivityUpdateGroupVaccination extends AppCompatActivity {
//
//    TextView vaccinationGroupNumber;
//    TextView vaccinationID;
//    TextView vaccinationDosage;
//    TextView vaccinationDate;
//    TextView vaccinationDrug;
//    TextView vaccinationNotes;
//    TextView vaccinationAdmin;
//    TextView vaccinationGroupID;
//
//    Button updateVaccination;
//    Boolean allVaccinated = false;
//
//    RadioButton radioButtonYes;
//    RadioButton radioButtonNo;
//
//    DatabaseReference databaseVaccination;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update_group_vaccination);
//
//        vaccinationGroupNumber
//                = (TextView)findViewById(R.id.TextViewGroupNumber);
//
//        vaccinationDate = (TextView)findViewById(R.id.vaccinationDate);
//        vaccinationDrug = (TextView)findViewById(R.id.textViewDrug);
//        vaccinationAdmin = (TextView)findViewById(R.id.editAdmin);
//        vaccinationDosage = (TextView)findViewById(R.id.textViewDosage);
//        vaccinationNotes = (TextView)findViewById(R.id.textViewNotes);
//        vaccinationID
//                = (TextView)findViewById(R.id.textViewVaccinationID);
//        vaccinationGroupID = findViewById(R.id.textViewGroupID);
//
//
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        String vaccinationGroupNumberText = extras.getString("EXTRA_GROUP_NUMBER");
//        String vaccinationDateText = extras.getString("EXTRA_DATE");
//        String vaccinationAdminText = extras.getString("EXTRA_ADMIN");
//        String vaccinationDrugText = extras.getString("EXTRA_DRUG");
//        String vaccinationDosageText = extras.getString("EXTRA_DOSAGE");
//        String vaccinationNotesText = extras.getString("EXTRA_NOTES");
//        String vaccinationIDText = extras.getString("EXTRA_VACCINATION_ID");
//        String vaccinationGroupIDText = extras.getString("EXTRA_GROUP_ID");
//
//        vaccinationGroupNumber.setText(vaccinationGroupNumberText);
//        vaccinationDate.setText(vaccinationDateText);
//        vaccinationDrug.setText(vaccinationDrugText);
//        vaccinationDosage.setText(vaccinationDosageText);
//        vaccinationAdmin.setText(vaccinationAdminText);
//        vaccinationNotes.setText(vaccinationNotesText);
//        vaccinationID.setText(vaccinationIDText);
//        vaccinationGroupID.setText(vaccinationGroupIDText);
//
//        updateVaccination = findViewById(R.id.buttonUpdateVaccination);
//        updateVaccination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                update();
//            }
//        });
//
//        radioButtonYes = (RadioButton) findViewById(R.id.radioButtonYes);
//        radioButtonNo = (RadioButton) findViewById(R.id.radioButtonNo);
//
//        //https://stackoverflow.com/questions/8323778/how-to-set-onclicklistener-on-a-radiobutton-in-android
//        // **Origional source but updated code**
//
//        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupAllVaccinated);
//
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (radioButtonNo.isChecked()){
//                    allVaccinated = false;
//
//                }
//                else if (radioButtonYes.isChecked()){
//                    allVaccinated = true;
//                }
//            }
//        });
//
//        databaseVaccination = FirebaseDatabase.getInstance().getReference("groupVaccination");
//
//
//
//    }
//
//    private void update(){
//        String id = vaccinationID.getText().toString().trim();
//        String number = vaccinationGroupNumber.getText().toString().trim();
//        String drug = vaccinationDrug.getText().toString().trim();
//        //Date date = dateVaccination;
//        String date = vaccinationDate.getText().toString().trim();
//        String admin = vaccinationAdmin.getText().toString().trim();
//        String dosage = vaccinationDosage.getText().toString().trim();
//        String notes = vaccinationNotes.getText().toString().trim();
//        String groupID = vaccinationGroupID.getText().toString().trim();
//
////        if (TextUtils.isEmpty(date)) {
////            Toast.makeText(this,"Please select a date", Toast.LENGTH_LONG).show();
////        if (TextUtils.isEmpty(number)) {
////            Toast.makeText(this,"Please select a date", Toast.LENGTH_LONG).show();
////
////        } else if (TextUtils.isEmpty(dosage)) {
////            Toast.makeText(this, "Please enter a dosage amount", Toast.LENGTH_LONG).show();
////        }
//        //else
//            if (radioButtonNo.isChecked() & (TextUtils.isEmpty(notes))){
//            Toast.makeText(this, "Please note which animals were not vaccinated", Toast.LENGTH_LONG).show();
//
//
//        }
//        else{
//            //String id = databaseVaccination.push().getKey();
//
//            GroupVaccination groupVaccination = new GroupVaccination(groupID, number, id, drug, admin, dosage, date, notes,allVaccinated );
//
//            databaseVaccination.child(id).setValue(groupVaccination);
//
//            Toast.makeText(this, "Vaccination Updated", Toast.LENGTH_LONG).show(); }
//}}
