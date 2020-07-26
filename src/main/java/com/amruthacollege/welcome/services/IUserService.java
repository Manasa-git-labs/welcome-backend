package com.amruthacollege.welcome.services;

import com.amruthacollege.welcome.dtos.LoginDto;
import com.amruthacollege.welcome.dtos.UserDto;
import com.amruthacollege.welcome.exceptions.InvalidCredentialsException;
import com.amruthacollege.welcome.exceptions.UserNotFoundException;
import com.amruthacollege.welcome.models.UserEntity;

import java.util.List;

/**
 * This interface has the UnImplemented functionality of registering the user
 * and verifying with the identity, login, forget password and update password
 * functionality.
 */
public interface IUserService {

    boolean register( UserDto newUserDto ) throws UserNotFoundException;

    boolean isVerifiedUser( String token );

    UserLoginInfo login( LoginDto loginDto ) throws UserNotFoundException, InvalidCredentialsException;

    void signOutUser( String token );

    List<UserEntity> getActiveUsers();
}
