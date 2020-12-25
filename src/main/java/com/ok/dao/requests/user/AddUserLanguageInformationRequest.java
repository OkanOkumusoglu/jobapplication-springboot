package com.ok.dao.requests.user;

import java.util.HashMap;

public class AddUserLanguageInformationRequest {

    private String userMail;
    private String language;
    private int level;


    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
