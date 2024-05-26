package com.example.project_final;


public class User implements Person {                 //constructor, getter setter for data table booking
    private String firstName;
    private String middleName;
    private String lastName;

    private String numPass;
    private String emailAddress;

    private String USERNAME;

    private String Pass;

    public User(String firstName, String middleName, String lastName, String numPass, String emailAddress) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.numPass = numPass;
        this.emailAddress = emailAddress;
    }

    public User(String USERNAME, String Pass) {
        this.Pass=Pass;
        this.USERNAME= USERNAME;
    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumPass() {
        return numPass;
    }

    public void setNumPass(String numPass) {
        this.numPass = numPass;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
}
