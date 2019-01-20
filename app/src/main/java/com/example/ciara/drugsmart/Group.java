package com.example.ciara.drugsmart;

public class Group {
    String groupID;
    String groupAnimalType;
    String groupBreed;
    String groupSource;
    String groupDOB;


    public Group(){
    }

    public Group(String groupID, String groupAnimalType, String groupBreed, String groupSource, String groupDOB){
        this.groupID = groupID;
        this.groupAnimalType = groupAnimalType;
        this.groupBreed = groupBreed;
        this.groupSource = groupSource;
        this.groupDOB = groupDOB;
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


}


