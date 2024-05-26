package com.example.project_final;


public class Flight {           //constructor, getter and setter for flight table
    private String Fromrr, Where, DateFrom, DateWhere;
    private int Price, IDflight;

    public Flight(String Fromrr, String Where, String DateFrom, String DateWhere, int price, int IDflight) {
        this.Fromrr = Fromrr;
        this.Where = Where;
        this.DateFrom = DateFrom;
        this.DateWhere = DateWhere;
        this.Price = price;
        this.IDflight = IDflight;
    }

    public Flight() {

    }

    public String getFromrr() {
        return Fromrr;
    }

    public void setFromrr(String fromFlightColumn) {
        this.Fromrr = Fromrr;
    }

    public String getWhere() {
        return Where;
    }

    public void setWhere(String whereFlightColumn) {
        this.Where = Where;
    }

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFromColumn) {
        this.DateFrom = DateFrom;
    }

    public String getDateWhere() {
        return DateWhere;
    }

    public void setDateWhere(String dateWhereColumn) {
        this.DateWhere = DateWhere;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = Price;
    }

    public int getIDflight() {
        return IDflight;
    }

    public void setIDflight(String price) {
        this.IDflight = IDflight;
    }

}

