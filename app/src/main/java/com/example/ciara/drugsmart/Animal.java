package com.example.ciara.drugsmart;

public class Animal {

    String animalID;
    String animalBreed;
    String animalGender;



    public Animal(){

    }

    public Animal(String animalID, String animalBreed, String animalGender){
        this.animalID = animalID;
        this.animalBreed = animalBreed;
        this.animalGender = animalGender;
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

}
