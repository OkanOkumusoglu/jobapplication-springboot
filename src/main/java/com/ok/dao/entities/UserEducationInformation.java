package com.ok.dao.entities;

public class UserEducationInformation {

    private String college;
    private String faculty;
    private String department;
    private String startDate;
    private String endDate;
    private String educationLanguage;
    private double gpa;

    public UserEducationInformation(String college, String faculty, String department, String startDate, String endDate, String educationLanguage,
                                    double gpa) {
        this.college = college;
        this.faculty = faculty;
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.educationLanguage = educationLanguage;
        this.gpa = gpa;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getEducationLanguage() {
        return educationLanguage;
    }

    public void setEducationLanguage(String educationLanguage) {
        this.educationLanguage = educationLanguage;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
