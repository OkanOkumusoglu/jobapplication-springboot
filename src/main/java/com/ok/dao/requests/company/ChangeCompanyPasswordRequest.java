package com.ok.dao.requests.company;

public class ChangeCompanyPasswordRequest {

    private String companyName;
    private String oldPass;
    private String newPass;
    private String newPassAgain;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getNewPassAgain() {
        return newPassAgain;
    }

    public void setNewPassAgain(String newPassAgain) {
        this.newPassAgain = newPassAgain;
    }
}
