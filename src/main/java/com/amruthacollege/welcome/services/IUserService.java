package com.amruthacollege.welcome.services;

import com.amruthacollege.welcome.dtos.UserDto;
import com.amruthacollege.welcome.exceptions.UserNotFoundException;

/**
 * This interface has the UnImplemented functionality of registering the user
 * and verifying with the identity, login, forget password and update password
 * functionality.
 */
public interface IUserService {

    boolean register( UserDto newUserDto ) throws UserNotFoundException;

    boolean isVerifiedUser( String token );
}
