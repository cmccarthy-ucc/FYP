package com.example.ciara.drugsmart;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GroupInfoAdapter extends ArrayAdapter<Group> {
    private Activity context;
    private List<Group> groupList;


    public GroupInfoAdapter(Activity context, List<Group>groupList){
        super(context, R.layout.group_list_view,groupList);
        this.context = context;
        this.groupList = groupList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.group_list_view, null, true);

        TextView groupType = (TextView)listView.findViewById(R.id.groupType);
        TextView groupBreed = (TextView)listView.findViewById(R.id.groupBreed);
        TextView groupDOB = (TextView)listView.findViewById(R.id.groupDOB);
        TextView groupID = (TextView)listView.findViewById(R.id.groupID);
        TextView groupSource = (TextView) listView.findViewById(R.id.groupSource);
        TextView animalTag = (TextView)listView.findViewById(R.id.tag);

        Group group = groupList.get(position);
        groupType.setText(group.getGroupAnimalType());
        groupBreed.setText(group.getGroupBreed());
        groupDOB.setText(group.getGroupDOB());
        groupID.setText(group.getGroupID());
        groupSource.setText(group.getGroupSource());

        return listView;



    }
}
