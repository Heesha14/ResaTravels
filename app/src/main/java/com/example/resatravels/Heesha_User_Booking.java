package com.example.resatravels;

public class Heesha_User_Booking {

    private String place;
    private String vehicle;
    private String register_plate;
    private String check_date;
    private int no_adults;
    private int no_children;
    private int days;
    private String date3;
    private String time3;

    public Heesha_User_Booking() {
    }

    public Heesha_User_Booking(String place, String vehicle, String register_plate, String check_date, int no_adults, int no_children, int days, String date3, String time3) {
        this.place = place;
        this.vehicle = vehicle;
        this.register_plate = register_plate;
        this.check_date = check_date;
        this.no_adults = no_adults;
        this.no_children = no_children;
        this.days = days;
        this.date3 = date3;
        this.time3 = time3;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getRegister_plate() {
        return register_plate;
    }

    public void setRegister_plate(String register_plate) {
        this.register_plate = register_plate;
    }

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public int getNo_adults() {
        return no_adults;
    }

    public void setNo_adults(int no_adults) {
        this.no_adults = no_adults;
    }

    public int getNo_children() {
        return no_children;
    }

    public void setNo_children(int no_children) {
        this.no_children = no_children;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDate3() {
        return date3;
    }

    public void setDate3(String date3) {
        this.date3 = date3;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }
}
