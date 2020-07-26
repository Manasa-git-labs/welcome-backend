package com.amruthacollege.welcome.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * User model which has the parameters which will hit with the database
 */
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    private String firstName;
    private String lastName;
    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(length = 10, unique = true)
    private long mobileNumber;
    @Column(unique = true)
    @Email
    private String emailId;
    private String password;
    @Column(length = 30)
    private String createdDateTime;
    private boolean isVerified;
    private boolean isLogin;

    public long getUserId() {
        return userId;
    }

    public void setUserId( long userId ) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber( long mobileNumber ) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId( String emailId ) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime( String createdDateTime ) {
        this.createdDateTime = createdDateTime;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified( boolean verified ) {
        isVerified = verified;
    }

    public boolean isLogin() { return isLogin; }

    public void setLogin( boolean login ) { isLogin = login; }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", createdDateTime='" + createdDateTime + '\'' +
                ", isVerified=" + isVerified +
                ", isLogin=" + isLogin +
                '}';
    }
}