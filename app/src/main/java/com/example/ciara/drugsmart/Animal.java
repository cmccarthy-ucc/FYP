package com.example.ciara.drugsmart;

public class Animal {

    String animalID;
    String animalBreed;
    String animalGender;
    String animalSource;
    String animalDOB;
    String animalTag;


    public Animal(){

    }

    public Animal(String animalID, String animalBreed, String animalGender, String animalSource, String animalDOB, String animalTag){
        this.animalID = animalID;
        this.animalBreed = animalBreed;
        this.animalGender = animalGender;
        this.animalSource = animalSource;
        this.animalDOB = animalDOB;
        this.animalTag = animalTag;
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

}
