package com.fyp.ciara.drugsmart;

public class Vaccination {

    String vaccinationAnimalID;
    String vaccinationID;
    String vaccinationDrug;
    String vaccinationAdmin;
    String vaccinationDosage;
    String vaccinationDate;

    public Vaccination(){

    }

    public Vaccination(String vaccinationAnimalID, String vaccinationID, String vaccinationDrug,
                       String vaccinationAdmin, String vaccinationDosage, String vaccinationDate){
        this.vaccinationAnimalID = vaccinationAnimalID;
        this.vaccinationID = vaccinationID;
        this.vaccinationDrug = vaccinationDrug;
        this.vaccinationAdmin = vaccinationAdmin;
        this.vaccinationDosage = vaccinationDosage;
        this.vaccinationDate = vaccinationDate;
    }

    public String getVaccinationAnimalID() {
        return vaccinationAnimalID;
    }


    public String getVaccinationID() {
        return vaccinationID;
    }

    public String getVaccinationDrug() { return vaccinationDrug; }

    public String getVaccinationAdmin() { return  vaccinationAdmin;}

    public String getVaccinationDosage() {return vaccinationDosage;}

    public String getVaccinationDate() {return  vaccinationDate; }

}
