package com.ok.dao.requests.jobadvert;

public class ResponseJobApplicantsRequest {

    private String userMail;
    private String companyNameBranchPosition;
    private boolean response;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getCompanyNameBranchPosition() {
        return companyNameBranchPosition;
    }

    public void setCompanyNameBranchPosition(String companyNameBranchPosition) {
        this.companyNameBranchPosition = companyNameBranchPosition;
    }

    public boolean getResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
