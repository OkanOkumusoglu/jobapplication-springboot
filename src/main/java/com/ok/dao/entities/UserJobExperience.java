package com.ok.dao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class UserJobExperience {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    @Id
    @org.springframework.data.annotation.Id
    private int jobExperienceId;

    private String userMail;
    private String companyName;
    private String position;
    private String startDate;
    private String endDate;
    private String companySector;
    private String country;
    private String jobDefinition;

    public UserJobExperience(String userMail, String companyName, String position, String startDate, String endDate, String companySector,
                             String country, String jobDefinition) {
        jobExperienceId = ID_GENERATOR.getAndIncrement();
        this.userMail = userMail;
        this.companyName = companyName;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.companySector = companySector;
        this.country = country;
        this.jobDefinition = jobDefinition;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCompanySector() {
        return companySector;
    }

    public void setCompanySector(String companySector) {
        this.companySector = companySector;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getJobDefinition() {
        return jobDefinition;
    }

    public void setJobDefinition(String jobDefinition) {
        this.jobDefinition = jobDefinition;
    }
}
