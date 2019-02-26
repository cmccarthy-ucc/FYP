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

public class DosingInfoAdapter extends ArrayAdapter<Dosing> {
    private Activity context;
    private List<Dosing> dosingList;


    public DosingInfoAdapter(Activity context, List<Dosing>dosingList){
        super(context, R.layout.dose_list_view,dosingList);
        this.context = context;
        this.dosingList = dosingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.dose_list_view, null, true);

        TextView dosingDrug = (TextView)listView.findViewById(R.id.drug);
        TextView dosingDosage = (TextView)listView.findViewById(R.id.dosage);
        TextView dosingDate = (TextView)listView.findViewById(R.id.date);
        TextView dosingAdmin = (TextView)listView.findViewById(R.id.administrator);
        TextView dosingID = (TextView) listView.findViewById(R.id.groupDoseID);
        TextView dosingGroupNumber = (TextView)listView.findViewById(R.id.groupNumber);
        TextView dosingNotes = (TextView)listView.findViewById(R.id.notes);
        TextView allDosedTrueFalse = (TextView)listView.findViewById(R.id.allDosedTrueFalse);
        TextView dosingType = listView.findViewById(R.id.doseType);



        Dosing dosing = dosingList.get(position);
        dosingDrug.setText(dosing.getDoseDrug());
        dosingDosage.setText(dosing.getDoseDosage());
        dosingDate.setText(dosing.getDoseDate());
        dosingID.setText(dosing.getDoseID());
        dosingAdmin.setText(dosing.getDoseAdmin());
        dosingGroupNumber.setText(dosing.getDoseGroupNumber());
        dosingNotes.setText(dosing.getDoseNotes());
        allDosedTrueFalse.setText(String.valueOf(dosing.getAllDosed()));
        dosingType.setText(dosing.getDoseType());
        return listView;
    }
}
