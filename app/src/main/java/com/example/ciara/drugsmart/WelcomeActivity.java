package com.example.ciara.drugsmart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    ImageView imageViewGroups;
    ImageView imageViewMedicalRecords;
    ImageView imageViewToDo;
    Button login;

    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;

    String userID;


    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userID = auth.getUid();

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);

//        TextView user = findViewById(R.id.textViewUser);
//        user.setText(userID);

        imageViewGroups = (ImageView) findViewById(R.id.imageViewGroups);
        imageViewGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, ActivityAllGroups.class);
                startActivity(intent);
            }
        });

//        login = findViewById(R.id.button2);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent (WelcomeActivity.this, TestMainActivity.class);
//                startActivity(intent);
//            }
//        });

        imageViewMedicalRecords = (ImageView) findViewById(R.id.imageViewMedicaRecords);
        imageViewMedicalRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, ActivityMedicalRecords2.class);
                startActivity(intent);
            }
        });

        imageViewToDo = (ImageView) findViewById(R.id.imageViewToDo);
        imageViewMedicalRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, ActivityMedicalRecords2.class);
                startActivity(intent);
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
                    case R.id.animals:
                        Toast.makeText(WelcomeActivity.this, "Animals", Toast.LENGTH_SHORT).show();
                        Intent intentAnimal = new Intent(WelcomeActivity.this, ActivityAllIndividuals.class);
                        startActivity(intentAnimal);
                        break;
                    case R.id.vaccinations:
                        Toast.makeText(WelcomeActivity.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(WelcomeActivity.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(WelcomeActivity.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(WelcomeActivity.this, ActivityAllGroups.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(WelcomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(WelcomeActivity.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(WelcomeActivity.this, "To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(WelcomeActivity.this, ActivityToDoDoses.class);
                        startActivity(intentToDo);
                        break;
                    case R.id.drugs:
                        Toast.makeText(WelcomeActivity.this, "Drugs Available", Toast.LENGTH_SHORT).show();
                        Intent intentDrugs = new Intent(WelcomeActivity.this, AddDrug.class);
                        startActivity(intentDrugs);
                        break;
                    case R.id.signOut:
                        auth.signOut();
                        startActivity(new Intent(WelcomeActivity.this, Login.class));
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;

            }

        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "Check your To-Do List", Snackbar.LENGTH_LONG).setAction("Action", null);
                snackbar.setAction(R.string.title_todo, new WelcomeActivity.openToDo());
                snackbar.show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public class openToDo implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Intent intentToDo = new Intent(WelcomeActivity.this, ActivityToDoDoses.class);
            startActivity(intentToDo);
        }
    }
    @Override
    protected void onResume() {
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(WelcomeActivity.this, Login.class));
            finish();
        }
        super.onResume();
    }
}
