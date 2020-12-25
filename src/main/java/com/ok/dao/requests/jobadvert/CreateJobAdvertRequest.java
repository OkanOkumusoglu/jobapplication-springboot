package com.ok.dao.requests.jobadvert;


public class CreateJobAdvertRequest {

    private String companyName;
    private String country;
    private String companyNameBranchPosition;
    private String department;
    private String address;
    private String jobAdvertDescription;
    private String language;
    private int minLanguageLevel;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompanyNameBranchPosition() {
        return companyNameBranchPosition;
    }

    public void setCompanyNameBranchPosition(String companyNameBranchPosition) {
        this.companyNameBranchPosition = companyNameBranchPosition;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobAdvertDescription() {
        return jobAdvertDescription;
    }

    public void setJobAdvertDescription(String jobAdvertDescription) {
        this.jobAdvertDescription = jobAdvertDescription;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMinLanguageLevel() {
        return minLanguageLevel;
    }

    public void setMinLanguageLevel(int minLanguageLevel) {
        this.minLanguageLevel = minLanguageLevel;
    }
}
