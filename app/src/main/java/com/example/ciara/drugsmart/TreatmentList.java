package com.example.ciara.drugsmart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TreatmentList extends ArrayAdapter<Treatments> {
    private Activity context;
    List<Treatments> animalTreatment;

    public TreatmentList(Activity context, List<Treatments> animalTreatment) {
        super(context, R.layout.layout_treatment_list, animalTreatment);
        this.context = context;
        this.animalTreatment = animalTreatment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_treatment_list, null, true);

        TextView textViewTreatmentDate = (TextView) listViewItem.findViewById(R.id.textViewTreatmentDate);
        TextView textViewTreatmentDrug = (TextView) listViewItem.findViewById(R.id.textViewTreatmentDrug);

        Treatments treatments = animalTreatment.get(position);
        textViewTreatmentDate.setText(treatments.getTreatmentDate());
        textViewTreatmentDrug.setText(treatments.getTreatmentDrug());
        return listViewItem;
    }
}
