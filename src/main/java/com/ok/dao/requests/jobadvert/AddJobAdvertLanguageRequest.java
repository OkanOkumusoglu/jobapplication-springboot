package com.ok.dao.requests.jobadvert;

public class AddJobAdvertLanguageRequest {

    private String companyName;
    private String companyNameBranchPosition;
    private String language;
    private int minLanguageLevel;

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
