package com.amruthacollege.welcome.services;

public class UserLoginInfo {
    private String token;
    private String firstName;

    public UserLoginInfo( String token, String firstName ) {
        this.token = token;
        this.firstName = firstName;
    }

    public String getToken() { return token; }

    public void setToken( String token ) { this.token = token; }

    public String getFirstName() { return firstName; }

    public void setFirstName( String firstName ) { this.firstName = firstName; }

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "token='" + token + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}