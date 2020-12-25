package com.ok.dao.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class User {

    @Id
    @org.springframework.data.annotation.Id
    private String userMail;

    private String firstName;
    private String lastName;
    private String userPassword;
    private long userPhone;
    private String birthday;
    private String address;
    private UserGeneralInformation userGeneralInformation;
    private List<JobAdvert> followingJobAdverts = new ArrayList<>();
    private List<JobAdvert> appliedJobs = new ArrayList<>();
    private HashMap<String, Boolean> appliedJobResponses = new HashMap<>();
    private List<UserJobExperience> userJobExperiences = new ArrayList<>();
    private UserEducationInformation userEducationInformation;
    private UserLanguageInformation userLanguageInformation;
    private List<UserCertificationInformation> userCertificationsInformation = new ArrayList<>();
    private ArrayList<String> talents = new ArrayList<>();

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    @JsonProperty(value = "userPassword")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @JsonIgnore
    @JsonProperty(value = "userPhone")
    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @JsonIgnore
    @JsonProperty(value = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserGeneralInformation getUserGeneralInformation() {
        return userGeneralInformation;
    }

    public void setUserGeneralInformation(UserGeneralInformation userGeneralInformation) {
        this.userGeneralInformation = userGeneralInformation;
    }


    public List<JobAdvert> getFollowingJobAdverts() {
        return followingJobAdverts;
    }

    public void setFollowingJobAdverts(List<JobAdvert> followingJobAdverts) {
        this.followingJobAdverts = followingJobAdverts;
    }

    @JsonIgnore
    @JsonProperty(value = "appliedJobs")
    public List<JobAdvert> getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(List<JobAdvert> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    @JsonIgnore
    @JsonProperty(value = "appliedJobResponses")
    public HashMap<String, Boolean> getAppliedJobResponses() {
        return appliedJobResponses;
    }

    public void setAppliedJobResponses(HashMap<String, Boolean> appliedJobResponses) {
        this.appliedJobResponses = appliedJobResponses;
    }

    public List<UserJobExperience> getUserJobExperiences() {
        return userJobExperiences;
    }

    public void setUserJobExperiences(List<UserJobExperience> userJobExperiences) {
        this.userJobExperiences = userJobExperiences;
    }

    public UserEducationInformation getUserEducationInformation() {
        return userEducationInformation;
    }

    public void setUserEducationInformation(UserEducationInformation userEducationInformation) {
        this.userEducationInformation = userEducationInformation;
    }

    public UserLanguageInformation getUserLanguageInformation() {
        return userLanguageInformation;
    }

    public void setUserLanguageInformation(UserLanguageInformation userLanguageInformation) {
        this.userLanguageInformation = userLanguageInformation;
    }

    public List<UserCertificationInformation> getUserCertificationsInformation() {
        return userCertificationsInformation;
    }

    public void setUserCertificationsInformation(List<UserCertificationInformation> userCertificationsInformation) {
        this.userCertificationsInformation = userCertificationsInformation;
    }

    public ArrayList<String> getTalents() {
        return talents;
    }

    public void setTalents(ArrayList<String> talents) {
        this.talents = talents;
    }
}
