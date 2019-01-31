package com.example.ciara.drugsmart;

public class GroupVaccination {
    String vaccinationGroupID;
    String vaccinationGroupNumber;
    String vaccinationID;
    String vaccinationDrug;
    String vaccinationAdmin;
    String vaccinationDosage;
    String vaccinationDate;

    public GroupVaccination(){

    }

    public GroupVaccination(String vaccinationGroupID, String vaccinationGroupNumber, String vaccinationID, String vaccinationDrug,
                       String vaccinationAdmin, String vaccinationDosage, String vaccinationDate){
        this.vaccinationGroupID = vaccinationGroupID;
        this.vaccinationGroupNumber = vaccinationGroupNumber;
        this.vaccinationID = vaccinationID;
        this.vaccinationDrug = vaccinationDrug;
        this.vaccinationAdmin = vaccinationAdmin;
        this.vaccinationDosage = vaccinationDosage;
        this.vaccinationDate = vaccinationDate;
    }

    public String getVaccinationGroupID() {   return vaccinationGroupID;    }

    public String getVaccinationGroupNumber() {   return vaccinationGroupNumber;    }

    public String getVaccinationID() {  return vaccinationID;    }

    public String getVaccinationDrug() { return vaccinationDrug; }

    public String getVaccinationAdmin() { return  vaccinationAdmin;}

    public String getVaccinationDosage() {return vaccinationDosage;}

    public String getVaccinationDate() {return  vaccinationDate; }


}
