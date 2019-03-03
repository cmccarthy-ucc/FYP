package com.example.ciara.drugsmart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DrugsList extends ArrayAdapter<Drug> {

    private Activity context;
    List<Drug> drugList;

    public DrugsList(Activity context, List<Drug> drugs) {
        super(context, R.layout.layout_drugs, drugs);
        this.context = context;
        this.drugList = drugs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_drugs, null, true);

        TextView textViewDrug = (TextView) listViewItem.findViewById(R.id.textViewDrugName);

        Drug drugs = drugList.get(position);
        textViewDrug.setText(drugs.getName());

        return listViewItem;
    }
}
