package com.ok.dao.requests.user;

public class AddUserGeneralInformationRequest {

    private String userMail;
    private String nationality;
    private long wageExpectation;
    private String driverLicence;
    private String gender;
    private String summary;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
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
