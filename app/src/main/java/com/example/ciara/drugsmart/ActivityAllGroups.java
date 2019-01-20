package com.example.ciara.drugsmart;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityAllGroups extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<Group> groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_groups);

        listView = (ListView)findViewById(R.id.listViewGroups);


        databaseReference = FirebaseDatabase.getInstance().getReference("groups");
        groupList = new ArrayList<>();
    }
    protected void onStart(){
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot groupSnapshot : dataSnapshot.getChildren()){
                    Group group = groupSnapshot.getValue(Group.class);
                    groupList.add(group);
                }

                GroupInfoAdapter groupInfoAdapter = new GroupInfoAdapter(ActivityAllGroups.this, groupList);
                listView.setAdapter(groupInfoAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
