package com.ok.dao.requests.user;

public class UserChangeIdRequest {

    private String userMail;
    private String newUserMail;
    private String userPassword;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getNewUserMail() {
        return newUserMail;
    }

    public void setNewUserMail(String newUserMail) {
        this.newUserMail = newUserMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
