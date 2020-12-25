package com.ok.dao.requests.user;

public class AddUserTalentRequest {

    private String userMail;
    private String talent;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }
}
