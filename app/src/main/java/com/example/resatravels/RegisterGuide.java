package com.example.resatravels;
public class RegisterGuide {
    private String id;
    private String fname;
    private String lname;
    private int age;
    private String dob;
    private String gender;
    private String mobile;
    private String email;
    private String address;
    private String experience;
    private String skills;
    private String language;
    public RegisterGuide() {
    }
    public RegisterGuide(String id, String fname, String lname, int age, String dob, String gender, String mobile, String email, String address, String experience,
                         String skills, String language) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.dob = dob;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.experience = experience;
        this.skills = skills;
        this.language = language;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }
    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
}
