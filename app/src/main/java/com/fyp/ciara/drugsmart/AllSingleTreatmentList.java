package com.fyp.ciara.drugsmart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AllSingleTreatmentList extends ArrayAdapter<Treatments> {
    private Activity context;
    List<Treatments> singleTreatments;

    public AllSingleTreatmentList (Activity context, List<Treatments> singleTreatments) {
        super(context, R.layout.layout_all_single_treatments, singleTreatments);
        this.context = context;
        this.singleTreatments = singleTreatments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_all_single_treatments, null, true);

        TextView textViewTag = (TextView) listViewItem.findViewById(R.id.textViewTagNo);
        TextView textViewDate = listViewItem.findViewById(R.id.textViewDate);
        TextView textViewDrug = listViewItem.findViewById(R.id.textViewDrug);

        Treatments treatment = singleTreatments.get(position);
        textViewTag.setText(treatment.getTreatmentAnimalTag());
        textViewDate.setText(treatment.getTreatmentDate());
        textViewDrug.setText(treatment.getTreatmentDrug());

        return listViewItem;
    }
}
