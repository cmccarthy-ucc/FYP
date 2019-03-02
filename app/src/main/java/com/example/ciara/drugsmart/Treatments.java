package com.example.ciara.drugsmart;

public class Treatments {

    String treatmentAnimalTag;
    String treatmentID;
    String treatmentDrug;
    String treatmentAdmin;
    String treatmentDosage;
    String treatmentDate;
    String treatmentNotes;

    public Treatments(){

    }

    public Treatments(String treatmentAnimalTag, String treatmentID, String treatmentDrug,
                       String treatmentAdmin, String treatmentDosage, String treatmentDate, String treatmentNotes){
        this.treatmentAnimalTag = treatmentAnimalTag;
        this.treatmentID = treatmentID;
        this.treatmentDrug = treatmentDrug;
        this.treatmentAdmin = treatmentAdmin;
        this.treatmentDosage = treatmentDosage;
        this.treatmentDate = treatmentDate;
        this.treatmentNotes = treatmentNotes;
    }

    public String getTreatmentAnimalTag() {
        return treatmentAnimalTag;
    }

    public String getTreatmentID() {
        return treatmentID;
    }

    public String getTreatmentDrug() { return treatmentDrug; }

    public String getTreatmentAdmin() { return  treatmentAdmin;}

    public String getTreatmentDosage() {return treatmentDosage;}

    public String getTreatmentDate() {return  treatmentDate; }

    public String getTreatmentNotes() {return treatmentNotes; }
}
