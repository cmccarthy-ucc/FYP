package com.example.ciara.drugsmart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GroupVaccinationList extends ArrayAdapter<GroupVaccination> {
    private Activity context;
   List<GroupVaccination> groupVaccinations;

    public GroupVaccinationList(Activity context, List<GroupVaccination> groupVaccinations) {
        super(context, R.layout.layout_group_vaccination_list, groupVaccinations);
        this.context = context;
        this.groupVaccinations = groupVaccinations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_group_vaccination_list, null, true);

        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewVaccinationDate);
        TextView textViewDrug = (TextView) listViewItem.findViewById(R.id.textViewVaccinationDrug);

        GroupVaccination groupVaccination = groupVaccinations.get(position);
        textViewDate.setText(groupVaccination.getVaccinationDate());
        textViewDrug.setText(groupVaccination.getVaccinationDrug());

        return listViewItem;
    }
}