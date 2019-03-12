package com.fyp.ciara.drugsmart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AllGroupDoseList extends ArrayAdapter<Dosing> {
    private Activity context;
    List<Dosing> groupDoses;

    public AllGroupDoseList (Activity context, List<Dosing> groupDoses) {
        super(context, R.layout.layout_all_group_dose, groupDoses);
        this.context = context;
        this.groupDoses = groupDoses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_all_group_dose, null, true);

        TextView textViewGroup = (TextView) listViewItem.findViewById(R.id.textViewGroupNumber);
        TextView textViewDate = listViewItem.findViewById(R.id.textViewDate);
        TextView textViewDrug = listViewItem.findViewById(R.id.textViewDrug);
        TextView textViewAllDosed = listViewItem.findViewById(R.id.textViewAllDosed);

        Dosing dose = groupDoses.get(position);
        textViewGroup.setText(dose.getDoseGroupNumber());
        textViewDate.setText(dose.getDoseDate());
        textViewDrug.setText(dose.getDoseDrug());
        textViewAllDosed.setText(dose.getAllDosed().toString());

        return listViewItem;
    }
}
