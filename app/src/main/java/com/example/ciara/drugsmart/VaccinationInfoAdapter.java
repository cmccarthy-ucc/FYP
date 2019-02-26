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

public class VaccinationInfoAdapter extends ArrayAdapter<Vaccination> {
    private Activity context;
    private List<Vaccination> vaccinationList;

    public VaccinationInfoAdapter(Activity context, List<Vaccination>vaccinationList){
        super(context, R.layout.vaccination_list_view,vaccinationList);
        this.context = context;
        this.vaccinationList = vaccinationList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.vaccination_list_view, null, true);

        TextView vaccinationDrug = (TextView)listView.findViewById(R.id.drug);
        TextView vaccinationDosage = (TextView)listView.findViewById(R.id.dosage);
        TextView vaccinationDate = (TextView)listView.findViewById(R.id.date);
        TextView vaccinationAdmin = (TextView)listView.findViewById(R.id.administrator);
        TextView vaccinationID = (TextView) listView.findViewById(R.id.vaccinationID);
        TextView vaccinationAnimalTag = (TextView)listView.findViewById(R.id.tag);

        Vaccination vaccination = vaccinationList.get(position);
        vaccinationDrug.setText(vaccination.getVaccinationDrug());
        vaccinationDosage.setText(vaccination.getVaccinationDosage());
        vaccinationDate.setText(vaccination.getVaccinationDate());
        vaccinationID.setText(vaccination.getVaccinationID());
        vaccinationAdmin.setText(vaccination.getVaccinationAdmin());
        vaccinationAnimalTag.setText(vaccination.getVaccinationAnimalTag());

        return listView;



    }
}
