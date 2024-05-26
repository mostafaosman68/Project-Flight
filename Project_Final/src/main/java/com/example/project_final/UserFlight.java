package com.example.project_final;


public class UserFlight extends User {                   //constructor, getter and setter to take data for sort data in search
    String where_field;
    String from_field;
    String dateOfFly_picker;

    public UserFlight(String where_field, String from_field, String dateOfFly_picker) {
        super();
        this.where_field = where_field;
        this.from_field = from_field;
        this.dateOfFly_picker = dateOfFly_picker;
    }

    public String getWhere_field() {
        return where_field;
    }

    public void setWhere_field(String where_field) {
        this.where_field = where_field;
    }

    public String getFrom_field() {
        return from_field;
    }

    public void setFrom_field(String from_field) {
        this.from_field = from_field;
    }

    public String getDateOfFly_picker() {
        return dateOfFly_picker;
    }

    public void setDateOfFly_picker(String dateOfFly_picker) {
        this.dateOfFly_picker = dateOfFly_picker;
    }
}


