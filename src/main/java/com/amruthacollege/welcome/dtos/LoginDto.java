package com.amruthacollege.welcome.dtos;

/**
 * Model class for login which has two parameters like username and password.
 */
public class LoginDto {

    private String userName;
    private String password;

    public LoginDto() {
    }

    public String getUserName() { return userName; }

    public void setUserName( String userName ) { this.userName = userName; }

    public String getPassword() { return password; }

    public void setPassword( String password ) { this.password = password; }

    @Override
    public String toString() {
        return "LoginDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}