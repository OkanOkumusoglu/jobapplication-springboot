package com.ok.dao.requests.company;

public class CreateCompanyRequest {

    private String companyName;
    private String companyMail;
    private String companyPassword;
    private String companyPasswordAgain;
    private String companySector;
    private long employeeCount;
    private String foundationYear;
    private String website;

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

    public String getCompanyPassword() {
        return companyPassword;
    }

    public void setCompanyPassword(String companyPassword) {
        this.companyPassword = companyPassword;
    }

    public String getCompanyPasswordAgain() {
        return companyPasswordAgain;
    }

    public void setCompanyPasswordAgain(String companyPasswordAgain) {
        this.companyPasswordAgain = companyPasswordAgain;
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

    public void setWebsite(String webSite) {
        this.website = webSite;
    }

}
