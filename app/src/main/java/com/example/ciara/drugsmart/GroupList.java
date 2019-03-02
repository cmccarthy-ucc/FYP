package com.example.ciara.drugsmart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GroupList extends ArrayAdapter<Group> {
    private Activity context;
   List<Group> groups;

    public GroupList(Activity context, List<Group> groups) {
        super(context, R.layout.layout_group_list, groups);
        this.context = context;
        this.groups = groups;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_group_list, null, true);

        TextView textViewGroupNumber = (TextView) listViewItem.findViewById(R.id.textViewGroupNumber);
        TextView textViewDOB = (TextView) listViewItem.findViewById(R.id.textViewVaccinationDrug);

        Group group = groups.get(position);
        textViewGroupNumber.setText(group.getGroupNo());
        textViewDOB.setText(group.getGroupDOB());

        return listViewItem;
    }
}