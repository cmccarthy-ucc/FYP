//package com.example.ciara.drugsmart;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Spinner;
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
//import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_ID;
//import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_NUMBER;
//import static com.example.ciara.drugsmart.GroupActivity.VACCINATION_DATE;
//
//public class ActivityUpdateGroupVaccination extends AppCompatActivity {
//
//    TextView vaccinationGroupNumber;
//    EditText vaccinationDosage;
//    TextView vaccinationDate;
//    TextView vaccinationDrug;
//    EditText vaccinationNotes;
//    EditText vaccinationAdmin;
//
//
//    List<Drug> drugList;
//    DatabaseReference fDatabaseRoot;
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
//                = (TextView)findViewById(R.id.textViewGroupNumber);
//        vaccinationDate = (TextView)findViewById(R.id.textViewVaccinationDate);
//        vaccinationAdmin = findViewById(R.id.editAdmin);
//        vaccinationDosage = findViewById(R.id.textViewDosage);
//        vaccinationNotes = findViewById(R.id.textViewNotes);
//        vaccinationDrug = findViewById(R.id.textViewVaccinationDrug);
//
//
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        final String vaccinationGroupIDText = extras.getString(GROUP_ID);
//        final String vaccinationGroupNumberText = extras.getString(GROUP_NUMBER);
//        final String vaccinationIDText = extras.getString(GroupActivity.VACCINATION_ID);
//        final String vaccinationDateText = extras.getString(VACCINATION_DATE);
//        final String vaccinationAdminText = extras.getString(GroupActivity.VACCINATION_ADMIN);
//        final String vaccinationDosageText = extras.getString(GroupActivity.VACCINATION_DOSAGE);
//        final String vaccinationDrugText = extras.getString(GroupActivity.VACCINATION_DRUG);
//        final String vaccinationNotesText = extras.getString(GroupActivity.VACCINATION_GROUP_NOTES);
//        //final String allVaccinatedText = extras.getString(GroupActivity.ALL_VACCINATED);
//
//        String vaccinationGroupID = vaccinationGroupIDText;
//        String vaccinationID = vaccinationIDText;
//        vaccinationGroupNumber.setText(vaccinationGroupNumberText);
//        vaccinationDate.setText(vaccinationDateText);
//        vaccinationDrug.setText(vaccinationDrugText);
//        vaccinationDosage.setText(vaccinationDosageText);
//        vaccinationAdmin.setText(vaccinationAdminText);
//        vaccinationNotes.setText(vaccinationNotesText);
//
//
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
//        updateVaccination = findViewById(R.id.buttonUpdateVaccination);
//        updateVaccination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                update(vaccinationGroupNumberText,  vaccinationIDText, vaccinationDrugText,
//                        vaccinationAdminText,vaccinationDosageText,vaccinationDateText,
//                        vaccinationNotesText, allVaccinated);
//            }
//        });
//
//
//    }
//
//    private void update(String vaccinationGroupNumber, String vaccinationID, String vaccinationDrug,
//                        String vaccinationAdmin, String vaccinationDosage, String vaccinationDate, String vaccinationNotes, Boolean allVaccinated){
//        String id = vaccinationID;
//        String number = vaccinationGroupNumber;
//        String drug = vaccinationDrug;
//        //Date date = dateVaccination;
//        String date = vaccinationDate;
//        String admin = vaccinationAdmin;
//        String dosage = vaccinationDosage;
//        String notes = vaccinationNotes;
//
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
