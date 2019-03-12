package com.fyp.ciara.drugsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityOptionsTwo extends AppCompatActivity {

    ImageView imageViewIndividualAnimal;
    ImageView imageViewAnimalGroups;
    Button buttonToDo;

    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_two);

        imageViewIndividualAnimal = (ImageView) findViewById(R.id.imageViewAnimal);
        imageViewIndividualAnimal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ActivityOptionsTwo.this, ActivityIndividualHome.class);
               startActivity(intent);
           }
    });

        imageViewAnimalGroups = (ImageView) findViewById(R.id.imageViewGroup);
        imageViewAnimalGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityOptionsTwo.this, ActivityAllGroups.class);
                startActivity(intent);
            }
        });

        buttonToDo = (Button)findViewById(R.id.button);
        buttonToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityOptionsTwo.this, DrugActivity.class);
                startActivity(intent);
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
//                    case R.id.animals:
//                        Toast.makeText(ActivityOptionsTwo.this, "Animals",Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(ActivityOptionsTwo.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
                    case R.id.vaccinations:
                        Toast.makeText(ActivityOptionsTwo.this, "Medical Records", Toast.LENGTH_SHORT).show();
                        Intent intentVaccination = new Intent(ActivityOptionsTwo.this, ActivityMedicalRecords2.class);
                        startActivity(intentVaccination);
                        break;
                    case R.id.groups:
                        Toast.makeText(ActivityOptionsTwo.this, "Groups", Toast.LENGTH_SHORT).show();
                        Intent intentGroups = new Intent(ActivityOptionsTwo.this, ActivityAddGroup.class);
                        startActivity(intentGroups);
                        break;
                    case R.id.home:
                        Toast.makeText(ActivityOptionsTwo.this,"Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(ActivityOptionsTwo.this, ActivityOptionsTwo.class);
                        startActivity(intentHome);
                        break;
                    case R.id.todo:
                        Toast.makeText(ActivityOptionsTwo.this,"To-Do List", Toast.LENGTH_SHORT).show();
                        Intent intentToDo = new Intent(ActivityOptionsTwo.this, ActivityToDoDoses.class);
                        startActivity(intentToDo);
                        break;
                    default:
                        return true;
                }
return true;

            }

        });

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Check your To-Do List", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "Check your To-Do List", Snackbar.LENGTH_LONG).setAction("Action", null);
                snackbar.setAction(R.string.title_todo, new openToDo());
                snackbar.show();

        }});
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public class openToDo implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            Intent intentToDo = new Intent(ActivityOptionsTwo.this, ActivityToDoDoses.class);
            startActivity(intentToDo);
        }
    }

}
