package com.example.ciara.drugsmart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class AnimalInfoAdapter extends ArrayAdapter<Animal> {
    private Activity context;
    private List<Animal> animalList;



    public AnimalInfoAdapter(Activity context, List<Animal>animalList){
        super(context, R.layout.list_view,animalList);
        this.context = context;
        this.animalList = animalList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.list_view, null, true);

        TextView animalBreed = (TextView)listView.findViewById(R.id.breed);
        TextView animalGender = (TextView)listView.findViewById(R.id.gender);
        TextView animalID = (TextView)listView.findViewById(R.id.animalID);
        TextView animalSource = (TextView)listView.findViewById(R.id.source);
        TextView animalDOB = (TextView)listView.findViewById(R.id.DOB) ;
        TextView animalTag = (TextView)listView.findViewById(R.id.tag);

        Animal animal = animalList.get(position);
        animalBreed.setText(animal.getAnimalBreed());
        animalGender.setText(animal.getAnimalGender());
        animalID.setText(animal.getAnimalID());
        animalSource.setText(animal.getAnimalSource());
        animalDOB.setText(animal.getAnimalDOB());
        animalTag.setText(animal.getAnimalTag());

        return listView;



    }
}
