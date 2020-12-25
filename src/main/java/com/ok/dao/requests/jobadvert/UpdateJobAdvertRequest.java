package com.ok.dao.requests.jobadvert;

public class UpdateJobAdvertRequest {

    private String companyName;
    private String companyNameBranchPosition;
    private String country;
    private String department;
    private String address;
    private String jobAdvertDescription;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNameBranchPosition() {
        return companyNameBranchPosition;
    }

    public void setCompanyNameBranchPosition(String companyNameBranchPosition) {
        this.companyNameBranchPosition = companyNameBranchPosition;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
}
