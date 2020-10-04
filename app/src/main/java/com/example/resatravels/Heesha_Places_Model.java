package com.example.resatravels;

public class Heesha_Places_Model {

   private String pname;
   private String province;
   private String mobile;
   private String description;
   private String image;
   private String date;
   private String time;
   private String pid;

    public Heesha_Places_Model() {
    }

    public Heesha_Places_Model(String place_name, String place_province, String place_mobile, String place_description, String image, String date, String time,String pid) {
        this.pname = place_name;
        this.province = place_province;
        this.mobile = place_mobile;
        this.description = place_description;
        this.image = image;
        this.date = date;
        this.time = time;
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String place_name) {
        this.pname = place_name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String place_province) {
        this.province = place_province;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String place_mobile) {
        this.mobile = place_mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String place_description) {
        this.description = place_description;
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
