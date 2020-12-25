package com.ok.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Company {

    @Id
    @org.springframework.data.annotation.Id
    private String companyName;

    private String companyMail;
    private String companyPassword;
    private String companySector;
    private long employeeCount;
    private String foundationYear;
    private String website;

    private ArrayList<JobAdvert> jobAdverts = new ArrayList<>();

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
    }

    @JsonIgnore
    @JsonProperty(value = "companyPassword")
    public String getCompanyPassword() {
        return companyPassword;
    }

    public void setCompanyPassword(String companyPassword) {
        this.companyPassword = companyPassword;
    }

    public String getCompanySector() {
        return companySector;
    }

    public void setCompanySector(String companySector) {
        this.companySector = companySector;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(String foundationYear) {
        this.foundationYear = foundationYear;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public ArrayList<JobAdvert> getJobAdverts() {
        return jobAdverts;
    }

    public void setJobAdverts(ArrayList<JobAdvert> jobAdverts) {
        this.jobAdverts = jobAdverts;
    }
}
