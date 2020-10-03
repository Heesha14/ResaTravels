package com.example.resatravels;

public class Heesha_Places_Model {

   private String place_name;
   private String place_province;
   private String place_mobile;
   private String place_description;
   private String image;
   private String date;
   private String time;

    public Heesha_Places_Model() {
    }

    public Heesha_Places_Model(String place_name, String place_province, String place_mobile, String place_description, String image, String date, String time) {
        this.place_name = place_name;
        this.place_province = place_province;
        this.place_mobile = place_mobile;
        this.place_description = place_description;
        this.image = image;
        this.date = date;
        this.time = time;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPlace_province() {
        return place_province;
    }

    public void setPlace_province(String place_province) {
        this.place_province = place_province;
    }

    public String getPlace_mobile() {
        return place_mobile;
    }

    public void setPlace_mobile(String place_mobile) {
        this.place_mobile = place_mobile;
    }

    public String getPlace_description() {
        return place_description;
    }

    public void setPlace_description(String place_description) {
        this.place_description = place_description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
