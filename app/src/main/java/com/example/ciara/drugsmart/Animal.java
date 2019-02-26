package com.example.ciara.drugsmart;

public class Animal {

    String animalID;
    String animalBreed;
    String animalGender;
    String animalSource;
    String animalDOB;
    String animalTag;
    String groupNumber;
    String notes;


    public Animal(){

    }

    public Animal(String animalID, String animalBreed, String animalGender, String animalSource, String animalDOB, String animalTag, String groupNumber, String notes){
        this.animalID = animalID;
        this.animalBreed = animalBreed;
        this.animalGender = animalGender;
        this.animalSource = animalSource;
        this.animalDOB = animalDOB;
        this.animalTag = animalTag;
        this.groupNumber = groupNumber;
        this.notes = notes;
    }

    public String getAnimalID() {
        return animalID;
    }

    public String getAnimalBreed() {
        return animalBreed;
    }

    public String getAnimalGender() {
        return animalGender;
    }

    public String getAnimalSource() { return animalSource; }

    public String getAnimalDOB() { return  animalDOB;}

    public String getAnimalTag() {return animalTag;}

    public String getGroupNumber() {return groupNumber;}

    public String getNotes() {return notes;}

}
