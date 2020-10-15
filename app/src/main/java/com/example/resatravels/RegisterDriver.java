package com.example.resatravels;

public class RegisterDriver {
    private String id;
    private String rdfirst;
    private String rdlast;
    private int rdage;
    private String rdinsurance;
    private String rggender;
    private String rddob;
    private String rdmobile;
    private String rdemail;
    private String rdaddress;
    private String experience;
    private String skills;

    public RegisterDriver() {
    }

    public RegisterDriver(String id, String rdfirst, String rdlast, int rdage, String rdinsurance, String rggender, String rddob, String rdmobile, String rdemail, String rdaddress, String experience, String skills) {
        this.id = id;
        this.rdfirst = rdfirst;
        this.rdlast = rdlast;
        this.rdage = rdage;
        this.rdinsurance = rdinsurance;
        this.rggender = rggender;
        this.rddob = rddob;
        this.rdmobile = rdmobile;
        this.rdemail = rdemail;
        this.rdaddress = rdaddress;
        this.experience = experience;
        this.skills = skills;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRdfirst() {
        return rdfirst;
    }

    public void setRdfirst(String rdfirst) {
        this.rdfirst = rdfirst;
    }

    public String getRdlast() {
        return rdlast;
    }

    public void setRdlast(String rdlast) {
        this.rdlast = rdlast;
    }

    public int getRdage() {
        return rdage;
    }

    public void setRdage(int rdage) {
        this.rdage = rdage;
    }

    public String getRdinsurance() {
        return rdinsurance;
    }

    public void setRdinsurance(String rdinsurance) {
        this.rdinsurance = rdinsurance;
    }

    public String getRggender() {
        return rggender;
    }

    public void setRggender(String rggender) {
        this.rggender = rggender;
    }

    public String getRddob() {
        return rddob;
    }

    public void setRddob(String rddob) {
        this.rddob = rddob;
    }

    public String getRdmobile() {
        return rdmobile;
    }

    public void setRdmobile(String rdmobile) {
        this.rdmobile = rdmobile;
    }

    public String getRdemail() {
        return rdemail;
    }

    public void setRdemail(String rdemail) {
        this.rdemail = rdemail;
    }

    public String getRdaddress() {
        return rdaddress;
    }

    public void setRdaddress(String rdaddress) {
        this.rdaddress = rdaddress;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
