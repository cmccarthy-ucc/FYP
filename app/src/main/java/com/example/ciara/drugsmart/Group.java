package com.example.ciara.drugsmart;

import java.util.Date;

public class Group {
    String groupNo;
    String groupAnimalType;
    String groupBreed;
    String groupSource;
    String groupDOB;
    String groupID;
    String groupNotes;
    Boolean vaccinated;


    public Group(){
    }

    public Group(String groupNo, String groupAnimalType, String groupBreed, String groupSource, String groupDOB, String groupID, String groupNotes, Boolean vaccinated){
        this.groupNo = groupNo;
        this.groupAnimalType = groupAnimalType;
        this.groupBreed = groupBreed;
        this.groupSource = groupSource;
        this.groupDOB = groupDOB;
        this.groupID = groupID;
        this.groupNotes = groupNotes;
        this.vaccinated = vaccinated;
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
    public Boolean getVaccinated() {return vaccinated; }


}


