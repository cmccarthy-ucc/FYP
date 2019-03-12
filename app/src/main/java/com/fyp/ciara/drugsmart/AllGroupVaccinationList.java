package com.fyp.ciara.drugsmart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AllGroupVaccinationList extends ArrayAdapter<GroupVaccination> {
    private Activity context;
    List<GroupVaccination> groupVaccinations;

    public AllGroupVaccinationList (Activity context, List<GroupVaccination> groupVaccinations) {
        super(context, R.layout.layout_all_group_vaccination_list, groupVaccinations);
        this.context = context;
        this.groupVaccinations = groupVaccinations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_all_group_vaccination_list, null, true);

        TextView textViewGroup = (TextView) listViewItem.findViewById(R.id.textViewGroupNumber);
        TextView textViewDate = listViewItem.findViewById(R.id.textViewDate);
        TextView textViewDrug = listViewItem.findViewById(R.id.textViewDrug);
        TextView textViewAllVaccinated = listViewItem.findViewById(R.id.textViewAllVaccinated);

        GroupVaccination groupVaccination = groupVaccinations.get(position);
        textViewGroup.setText(groupVaccination.getVaccinationGroupNumber());
        textViewDate.setText(groupVaccination.getVaccinationDate());
        textViewDrug.setText(groupVaccination.getVaccinationDrug());
        textViewAllVaccinated.setText(groupVaccination.getAllVaccinated().toString());

        return listViewItem;
    }
}
