package com.example.ciara.drugsmart;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_ID;
import static com.example.ciara.drugsmart.ActivityAddGroup.GROUP_NUMBER;

public class GroupVaccinationDetailsActivity extends AppCompatActivity {

    TextView groupID;
    TextView vaccinationGroupNumber;
    TextView vaccinationID;
    TextView vaccinationDate;
    TextView vaccinationDosage;
    TextView vaccinationAdmin;
    TextView allVaccination;
    TextView vaccinationDrug;
    TextView vaccinationNotes;
    Button btnUpdate;

    Boolean allAnimalsVaccinated = true;

    RadioButton radioButtonYes;
    RadioButton radioButtonNo;
    RadioGroup allVaccinatedBoolean;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_vaccination_details);


        vaccinationGroupNumber = findViewById(R.id.vaccinationGroupNumber);
        vaccinationID = findViewById(R.id.vaccinationID);
        vaccinationDate = findViewById(R.id.treatmentDate);
        vaccinationAdmin = findViewById(R.id.vaccinationAdmin);
        vaccinationDosage = findViewById(R.id.vaccinationDosage);
        vaccinationDrug = findViewById(R.id.vaccinationDrug);
        vaccinationNotes = findViewById(R.id.vaccinationNotes);
        allVaccination = findViewById(R.id.allVaccinated);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String vaccinationGroupIDText = extras.getString(GROUP_ID);
        final String vaccinationGroupNumberText = extras.getString(GROUP_NUMBER);
        final String vaccinationIDText = extras.getString(GroupActivity.VACCINATION_ID);
        final String vaccinationDateText = extras.getString(GroupActivity.VACCINATION_DATE);
        final String vaccinationAdminText = extras.getString(GroupActivity.VACCINATION_ADMIN);
        final String vaccinationDosageText = extras.getString(GroupActivity.VACCINATION_DOSAGE);
        final String vaccinationDrugText = extras.getString(GroupActivity.VACCINATION_DRUG);
        final String vaccinationNotesText = extras.getString(GroupActivity.VACCINATION_GROUP_NOTES);
        String allVaccinatedText = extras.getString(GroupActivity.ALL_VACCINATED);


//        groupID.setText(vaccinationGroupIDText);
        vaccinationID.setText(vaccinationIDText);
        vaccinationDate.setText(vaccinationDateText);
        vaccinationAdmin.setText(vaccinationAdminText);
        vaccinationDosage.setText(vaccinationDosageText);
        vaccinationNotes.setText(vaccinationNotesText);
        vaccinationDrug.setText(vaccinationDrugText);
        allVaccination.setText(allVaccinatedText);
        vaccinationGroupNumber.setText(vaccinationGroupNumberText);


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
                    case R.id.animals:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "Animals", Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(GroupVaccinationDetailsActivity.this, ActivityIndividualHome.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(GroupVaccinationDetailsActivity.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(GroupVaccinationDetailsActivity.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(GroupVaccinationDetailsActivity.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(GroupVaccinationDetailsActivity.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(GroupVaccinationDetailsActivity.this, ActivityToDoList.class);
                        startActivity(intentToDo);
                        break;
                    default:
                        return true;
                }
                return true;

            }

        });

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDeleteDialog(vaccinationGroupNumberText, vaccinationIDText, vaccinationDrugText, vaccinationAdminText, vaccinationDosageText,
                        vaccinationDateText, vaccinationNotesText, allAnimalsVaccinated);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void showUpdateDeleteDialog(String vaccinationGroupNumber, String vaccinationID, String vaccinationDrug,
                                        String vaccinationAdmin, String vaccinationDosage, String vaccinationDate, String vaccinationNotes, Boolean allVaccinated) {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String groupIDText = extras.getString(GROUP_ID);
        final String groupNumberText = extras.getString(ActivityAddGroup.GROUP_NUMBER);
        String vaccinationIDText = extras.getString(GroupActivity.VACCINATION_ID);
        String vaccinationDrugText = extras.getString(GroupActivity.VACCINATION_DRUG);
        String vaccinationAdminText = extras.getString(GroupActivity.VACCINATION_ADMIN);
        String vaccinationDosageText = extras.getString(GroupActivity.VACCINATION_DOSAGE);
        String vaccinationDateText = extras.getString(GroupActivity.VACCINATION_DATE);
        String vaccinationNotesText = extras.getString(GroupActivity.VACCINATION_GROUP_NOTES);
        final String allVaccinatedText = extras.getString(GroupActivity.ALL_VACCINATED);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_group_vaccination, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextAdmin = dialogView.findViewById(R.id.editTextAdmin);
        editTextAdmin.setText(vaccinationAdminText);
        final EditText editTextDosage = dialogView.findViewById(R.id.editTextDosage);
        editTextDosage.setText(vaccinationDosageText);
        final EditText editTextNotes = dialogView.findViewById(R.id.editTextNotes);
        editTextNotes.setText(vaccinationNotesText);
        final TextView textViewDate = dialogView.findViewById(R.id.textViewDate);
        textViewDate.setText(vaccinationDateText);
        final Spinner spinnerDrug = (Spinner) dialogView.findViewById(R.id.spinnerDrug);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateArtist);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteArtist);

        dialogBuilder.setTitle("GroupNumber:   " + groupNumberText);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groupNo = groupNumberText;
                String id = groupIDText;
                String admin = editTextAdmin.getText().toString().trim();
                String dosage = editTextDosage.getText().toString().trim();
                String date = textViewDate.getText().toString().trim();
                String notes = editTextNotes.getText().toString().trim();
                String drug = spinnerDrug.getSelectedItem().toString();
//                    Boolean allVaccinated = GroupActivity.ALL_VACCINATED;
                if (!TextUtils.isEmpty(dosage)) {
                    updateGroupVaccination(groupNo, id, drug, admin, dosage, date, notes, allAnimalsVaccinated);
                    b.dismiss();
                }
                finish();
                startActivity(getIntent());
            }
        });
    }

    private boolean updateGroupVaccination(String vaccinationGroupNumber, String vaccinationID, String vaccinationDrug,
                                           String vaccinationAdmin, String vaccinationDosage, String vaccinationDate, String vaccinationNotes, Boolean allVaccinated) {
        String groupID = ActivityAddGroup.GROUP_ID;
        //getting the specified vaccination reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("groupVaccinations");
        DatabaseReference drGroup = dR.child(groupID);
        DatabaseReference drVaccination = drGroup.child(vaccinationID);


        //updating vaccination
        GroupVaccination group = new GroupVaccination(vaccinationGroupNumber, vaccinationID, vaccinationDrug, vaccinationAdmin, vaccinationDosage,
                                vaccinationDate, vaccinationNotes, allVaccinated);
        drVaccination.setValue(group);
        Toast.makeText(getApplicationContext(), "Vaccination Updated", Toast.LENGTH_LONG).show();


        return true;
        //updating artist
    }

}