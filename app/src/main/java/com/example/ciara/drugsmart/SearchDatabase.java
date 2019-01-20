package com.example.ciara.drugsmart;
//https://www.youtube.com/watch?v=b_tz8kbFUsU&fbclid=IwAR1miglP_HLi82-Fu4YVt98aJsbOC9nPfVkVVD5powiXardkPxzIOwB6wHg

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SearchDatabase extends AppCompatActivity {

    Button buttonSearch;
    EditText editTextSearch;
    ListView resultsListView;
    private DatabaseReference mVaccinationDatabase;
    List<Vaccination> vaccinationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_database);

        mVaccinationDatabase = FirebaseDatabase.getInstance().getReference("vaccination");

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        resultsListView = (ListView) findViewById(R.id.resultsListView);


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //resultsList.clear(); //clear RecyclerView
                //get text from search field
                String vaccinationInfo = editTextSearch.getText().toString();
                firebaseVaccinationSearch(vaccinationInfo);
            }
        });
    }

    private void firebaseVaccinationSearch(String vaccinationInfo) {
        //query to search database based on text in textbox - made case insensitive
        //code from https://stackoverflow.com/questions/54155576/android-firebase-search-query-not-working-properly

        //query to search database based on text in textbox - made case insensitive
        //code from https://stackoverflow.com/questions/54155576/android-firebase-search-query-not-working-properly
        Query vaccinationSearchQuery = mVaccinationDatabase.orderByChild("ID").startAt(vaccinationInfo.toLowerCase()).endAt(vaccinationInfo.toLowerCase() + "\uf8ff");
        vaccinationSearchQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot vaccinationSnapshot : dataSnapshot.getChildren()) {
                    Vaccination vaccination = vaccinationSnapshot.getValue(Vaccination.class);
                    vaccinationList.add(vaccination);
                }
                VaccinationInfoAdapter vaccinationInfoAdapter = new VaccinationInfoAdapter(SearchDatabase.this, vaccinationList);
                resultsListView.setAdapter(vaccinationInfoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

        //FirebaseRecyclerAdapter provided by the Firebase UI then you pass in the model class and the viewHolder
//        FirebaseRecyclerAdapter<Vaccination, vaccinationViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Vaccination, vaccinationViewHolder>() {
//            @Override
//            protected void onBindViewHolder(@NonNull vaccinationViewHolder holder, int position, @NonNull Vaccination model) {
//
//            }
//
//            @NonNull
//            @Override
//            public vaccinationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//                return null;
//            }
//        };

//                Vaccination.class, R.layout.list_layout,
//                vaccinationViewHolder.class,
//                mVaccinationDatabase
//
//        ) {
//            @Override
//            protected void populateViewHolder(vaccinationViewHolder viewHolder, Vaccination model, int position) {
//
//            }};

//
//


    //View Holder Class
    //Help initialise the content from the single layout
    public class vaccinationViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public vaccinationViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }
    }

}
