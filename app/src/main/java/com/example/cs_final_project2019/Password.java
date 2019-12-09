package com.example.cs_final_project2019;

public class Password {
    private String website;
    private String username;
    private String password;

    public Password(String setWebsite, String setUsername, String setPassword) {
        website = setWebsite;
        username = setUsername;
        password = setPassword;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String setWebsite) {
        website = setWebsite;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String setUsername) {
        username = setUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String setPassword) {
        password = setPassword;
    }
}
