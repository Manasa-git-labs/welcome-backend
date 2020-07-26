package com.amruthacollege.welcome.responses;

import com.amruthacollege.welcome.services.UserLoginInfo;

public class LoginResponse extends Response {
    private final UserLoginInfo loginInfo;

    public LoginResponse( String message, int statusCode, UserLoginInfo loginInfo ) {
        super (message, statusCode);
        this.loginInfo = loginInfo;
    }
    public UserLoginInfo getLoginInfo() { return loginInfo; }
}