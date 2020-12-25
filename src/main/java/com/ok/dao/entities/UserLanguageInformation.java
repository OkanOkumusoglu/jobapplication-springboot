package com.ok.dao.entities;

import java.util.HashMap;

public class UserLanguageInformation {

    private HashMap<String, Integer> languages = new HashMap();

    public HashMap<String, Integer> getLanguages() {
        return languages;
    }

    public void setLanguages(HashMap<String, Integer> languages) {
        this.languages = languages;
    }
}
