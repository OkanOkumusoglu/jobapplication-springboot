package com.ok.dao.entities;

public class UserGeneralInformation {

    private String nationality;
    private long wageExpectation;
    private String driverLicence;
    private String gender;
    private String summary;

    public UserGeneralInformation(String nationality, long wageExpectation, String driverLicence, String gender, String summary) {
        this.nationality = nationality;
        this.wageExpectation = wageExpectation;
        this.driverLicence = driverLicence;
        this.gender = gender;
        this.summary = summary;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public long getWageExpectation() {
        return wageExpectation;
    }

    public void setWageExpectation(long wageExpectation) {
        this.wageExpectation = wageExpectation;
    }

    public String getDriverLicence() {
        return driverLicence;
    }

    public void setDriverLicence(String driverLicence) {
        this.driverLicence = driverLicence;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
