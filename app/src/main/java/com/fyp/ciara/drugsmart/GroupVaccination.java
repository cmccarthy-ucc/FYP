package com.fyp.ciara.drugsmart;



public class GroupVaccination {

    String vaccinationGroupNumber;
    String vaccinationID;
    String vaccinationDrug;
    String vaccinationAdmin;
    String vaccinationDosage;
    String vaccinationDate;
    String vaccinationNotes;
    Boolean allVaccinated;
    Long timeStamp;
    String vaccinationUserID;

    public GroupVaccination(){

    }

    public GroupVaccination( String vaccinationGroupNumber, String vaccinationID, String vaccinationDrug,
                       String vaccinationAdmin, String vaccinationDosage, String vaccinationDate, String vaccinationNotes, Boolean allVaccinated, Long timeStamp, String vaccinationUserID){
//        this.vaccinationGroupID = vaccinationGroupID;
        this.vaccinationGroupNumber = vaccinationGroupNumber;
        this.vaccinationID = vaccinationID;
        this.vaccinationDrug = vaccinationDrug;
        this.vaccinationAdmin = vaccinationAdmin;
        this.vaccinationDosage = vaccinationDosage;
        this.vaccinationDate = vaccinationDate;
        this.vaccinationNotes = vaccinationNotes;
        this.allVaccinated = allVaccinated;
        this.timeStamp = timeStamp;
        this.vaccinationUserID = vaccinationUserID;

    }

//    public String getVaccinationGroupID() {   return vaccinationGroupID;    }

    public String getVaccinationGroupNumber() {   return vaccinationGroupNumber;    }

    public String getVaccinationID() {  return vaccinationID;    }

    public String getVaccinationDrug() { return vaccinationDrug; }

    public String getVaccinationAdmin() { return  vaccinationAdmin;}

    public String getVaccinationDosage() {return vaccinationDosage;}

    public String getVaccinationDate() {return  vaccinationDate; }

    public String getVaccinationNotes() {return vaccinationNotes; }

    public Boolean getAllVaccinated() {return allVaccinated; }

    public Long getTimeStamp(){return timeStamp;}

    public String getVaccinationUserID() {return vaccinationUserID; }


}
