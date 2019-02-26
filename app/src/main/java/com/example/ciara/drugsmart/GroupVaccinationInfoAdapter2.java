//package com.example.ciara.drugsmart;
//
//import android.app.Activity;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class GroupVaccinationInfoAdapter2 extends ArrayAdapter<GroupVaccination> {
//    private Activity context;
//    private List<GroupVaccination> groupVaccinationList;
//
//    public GroupVaccinationInfoAdapter2(Activity context, List<GroupVaccination>groupVaccinationList){
//        super(context, R.layout.group_vaccination_list_view_2,groupVaccinationList);
//        this.context = context;
//        this.groupVaccinationList = groupVaccinationList;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = context.getLayoutInflater();
//        View listView = inflater.inflate(R.layout.group_vaccination_list_view_2, null, true);
////
////        TextView vaccinationDrug = (TextView)listView.findViewById(R.id.drug);
////        TextView vaccinationDosage = (TextView)listView.findViewById(R.id.dosage);
//        TextView vaccinationDate = (TextView)listView.findViewById(R.id.date);
//        //TextView vaccinationAdmin = (TextView)listView.findViewById(R.id.administrator);
//        TextView vaccinationID = (TextView) listView.findViewById(R.id.groupVaccinationID);
//        TextView vaccinationGroupNumber = (TextView)listView.findViewById(R.id.groupNumber);
//
//        GroupVaccination groupVaccination = groupVaccinationList.get(position);
////        vaccinationDrug.setText(groupVaccination.getVaccinationDrug());
////        vaccinationDosage.setText(groupVaccination.getVaccinationDosage());
//        vaccinationDate.setText(groupVaccination.getVaccinationDate());
//        vaccinationID.setText(groupVaccination.getVaccinationID());
//        //vaccinationAdmin.setText(groupVaccination.getVaccinationAdmin());
//        vaccinationGroupNumber.setText(groupVaccination.getVaccinationGroupNumber());
//
//        return listView;
//    }
//}
