package com.ok.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class JobAdvert {

    @Id
    @org.springframework.data.annotation.Id
    private String companyNameBranchPosition;

    private String country;
    private String department;
    private String address;
    private String jobAdvertDescription;
    private HashMap<String, Integer> minLanguageLevel = new HashMap<>();
    private List<User> applicants = new ArrayList<>();
    private List<User> acceptedUser = new ArrayList<>();

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

    public HashMap<String, Integer> getMinLanguageLevel() {
        return minLanguageLevel;
    }

    public void setMinLanguageLevel(HashMap<String, Integer> minLanguageLevel) {
        this.minLanguageLevel = minLanguageLevel;
    }

    @JsonIgnore
    @JsonProperty(value = "applicants")
    public List<User> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<User> applicants) {
        this.applicants = applicants;
    }

    @JsonIgnore
    @JsonProperty(value = "acceptedUser")
    public List<User> getAcceptedUser() {
        return acceptedUser;
    }

    public void setAcceptedUser(List<User> acceptedUser) {
        this.acceptedUser = acceptedUser;
    }
}
