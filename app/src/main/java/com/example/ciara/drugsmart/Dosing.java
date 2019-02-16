package com.example.ciara.drugsmart;

public class Dosing {
    String doseGroupID;
    String doseGroupNumber;
    String doseID;
    String doseDrug;
    String doseAdmin;
    String doseDosage;
    String doseDate;
    //Date doseDate;
    String doseNotes;
    Boolean allDosed;
    String doseMethod;


    public Dosing(){

    }

    public Dosing(String doseGroupID, String doseGroupNumber, String doseID, String doseDrug,
                  String doseAdmin, String doseDosage, String doseDate, String doseNotes, Boolean allDosed, String doseMethod){
        //Date doseDate
        this.doseGroupID = doseGroupID;
        this.doseGroupNumber = doseGroupNumber;
        this.doseID = doseID;
        this.doseDrug = doseDrug;
        this.doseAdmin = doseAdmin;
        this.doseDosage = doseDosage;
        this.doseDate = doseDate;
        this.doseNotes = doseNotes;
        this.allDosed = allDosed;
        this.doseMethod = doseMethod;
    }

    public String getDoseGroupID() {   return doseGroupID;    }

    public String getDoseGroupNumber() {   return doseGroupNumber;    }

    public String getDoseID() {  return doseID;    }

    public String getDoseDrug() { return doseDrug; }

    public String getDoseAdmin() { return  doseAdmin;}

    public String getDoseDosage() {return doseDosage;}

    public String getDoseDate() {return  doseDate; }
    //public Date getDoseDate() {return  doseDate; }

    public String getDoseNotes() {return doseNotes; }

    public Boolean getAllDosed() {return allDosed; }

    public String getDoseMethod() { return doseMethod; }


}
