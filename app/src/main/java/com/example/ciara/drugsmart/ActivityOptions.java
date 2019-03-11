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
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//public class ActivityOptions extends AppCompatActivity {
//
//    ImageView imageViewAnimal;
//    ImageView imageViewVaccination;
//
//    //https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
//    private DrawerLayout dl;
//    private ActionBarDrawerToggle t;
//    private NavigationView nv;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_options);
////
////        imageViewAnimal = (ImageView) findViewById(R.id.imageViewAnimal);
////        imageViewAnimal.setOnClickListener(new View.OnClickListener() {
////           @Override
////           public void onClick(View v) {
////               Intent intent = new Intent(ActivityOptions.this, ActivityHome.class);
////               startActivity(intent);
////           }
////    });
//
//        imageViewVaccination = (ImageView) findViewById(R.id.imageViewVaccination);
//        imageViewVaccination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivityOptions.this, ActivityVaccinationHome.class);
//                startActivity(intent);
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
//
//            @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//
//                switch (id) {
//                    case R.id.animals:
//                        Toast.makeText(ActivityOptions.this, "Animals",Toast.LENGTH_SHORT).show();
//                        Intent intentAnimal = new Intent(ActivityOptions.this, ActivityIndividualHome.class);
//                        startActivity(intentAnimal);
//                        break;
//                    case R.id.vaccinations:
//                        Toast.makeText(ActivityOptions.this, "Medical Records", Toast.LENGTH_SHORT).show();
//                        Intent intentVaccination = new Intent(ActivityOptions.this, ActivityMedicalRecords2.class);
//                        startActivity(intentVaccination);
//                        break;
//                    case R.id.groups:
//                        Toast.makeText(ActivityOptions.this, "Groups", Toast.LENGTH_SHORT).show();
//                        Intent intentGroups = new Intent(ActivityOptions.this, ActivityGroupHome.class);
//                        startActivity(intentGroups);
//                        break;
//                    case R.id.home:
//                        Toast.makeText(ActivityOptions.this,"Home", Toast.LENGTH_SHORT).show();
//                        Intent intentHome = new Intent(ActivityOptions.this, ActivityOptionsTwo.class);
//                        startActivity(intentHome);
//                        break;
//                    case R.id.todo:
//                        Toast.makeText(ActivityOptions.this,"To-Do List", Toast.LENGTH_SHORT).show();
//                        Intent intentToDo = new Intent(ActivityOptions.this, ActivityToDoList.class);
//                        startActivity(intentToDo);
//                        break;
//                    default:
//                        return true;
//                }
//return true;
//
//            }
//
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
//
//}
