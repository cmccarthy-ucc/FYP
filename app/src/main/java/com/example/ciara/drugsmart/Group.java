package com.example.ciara.drugsmart;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {
    String groupNo;
    String groupAnimalType;
    String groupBreed;
    String groupSource;
    String groupDOB;
    String groupID;
    String groupNotes;
    String userID;
//
    public Group(){}

    public Group(String vaccinationGroupNumber, String vaccinationID, String vaccinationDrug,
                 String vaccinationAdmin, String vaccinationDosage, String vaccinationDate,
                 String vaccinationNotes, Boolean allAnimalsVaccinated, String userID){
    }


    public Group(String groupNo, String groupAnimalType, String groupBreed,
                 String groupSource, String groupDOB,
                 String groupID, String groupNotes, String userID){
        this.groupNo = groupNo;
        this.groupAnimalType = groupAnimalType;
        this.groupBreed = groupBreed;
        this.groupSource = groupSource;
        this.groupDOB = groupDOB;
        this.groupID = groupID;
        this.groupNotes = groupNotes;
        this.userID = userID;
        //this.vaccinated = vaccinated;
    }
    public String getGroupID() {
        return groupID;
    }
    public String getGroupBreed() {
        return groupBreed;
    }
    public String getGroupSource() {
        return groupSource;
    }
    public String getGroupAnimalType() { return groupAnimalType;  }
    public String getGroupDOB() { return groupDOB;  }
    public String getGroupNo() {return groupNo; }
    public String getGroupNotes() {return groupNotes; }
    public String getUserID() {return userID;}
   // public Boolean getVaccinated() {return vaccinated; }


}


