package com.ok.dao.requests.user;

public class DeleteUserCertificationRequest {

    private String userMail;
    private int certificationId;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public int getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(int certificationId) {
        this.certificationId = certificationId;
    }
}
