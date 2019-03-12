package com.fyp.ciara.drugsmart;

public class Drug {

        String name;
        //String drugID;

    public Drug() {
    }

public Drug(String name) {
        this.name = name;
        //this.drugID = drugID;
        }




    public String getName() {
            return name;
        }

    //public String getDrugID() {
//        return drugID;
//    }

        public void setName(String name) {
            this.name = name;
        }

//    public void setDrugID(String drugID) {
//        this.drugID = drugID;
//    }
}

