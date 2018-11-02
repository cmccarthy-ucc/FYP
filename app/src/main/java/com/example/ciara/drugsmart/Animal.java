package com.example.ciara.drugsmart;

public class Animal {

    String animalID;
    String animalBreed;
    String animalGender;
    String animalSource;



    public Animal(){

    }

    public Animal(String animalID, String animalBreed, String animalGender, String animalSource){
        this.animalID = animalID;
        this.animalBreed = animalBreed;
        this.animalGender = animalGender;
        this.animalSource = animalSource;
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

}
