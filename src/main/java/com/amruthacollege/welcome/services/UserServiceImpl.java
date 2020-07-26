package com.amruthacollege.welcome.services;

import com.amruthacollege.welcome.dtos.LoginDto;
import com.amruthacollege.welcome.dtos.UserDto;
import com.amruthacollege.welcome.exceptions.InvalidCredentialsException;
import com.amruthacollege.welcome.exceptions.UserNotFoundException;
import com.amruthacollege.welcome.models.UserEntity;
import com.amruthacollege.welcome.repository.UserRepository;
import com.amruthacollege.welcome.responses.MailObject;
import com.amruthacollege.welcome.securities.JwtTokenProvider;
import com.amruthacollege.welcome.utility.EmailServiceProvider;
import com.amruthacollege.welcome.utility.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private EmailServiceProvider emailServiceProvider;

    @Override
    public boolean register( UserDto newUserDto ) throws UserNotFoundException {
        if (!userRepository.existsByUserName (newUserDto.getUserName ())) {
            UserEntity newUser = new UserEntity ();
            BeanUtils.copyProperties (newUserDto, newUser);
            newUser.setCreatedDateTime (Util.currentDateTime ());
            newUser.setVerified (false);
            newUser.setPassword (passwordEncoder.encode (newUser.getPassword ()));
            userRepository.save (newUser);
//            fetching the user from data base again to send verification mail
            Optional<UserEntity> fetchedUser = userRepository.findOneByUserName (newUser.getUserName ());
            if (fetchedUser.isPresent ()) {
                emailServiceProvider.sendMail (mailContent (fetchedUser.get ()));
                return true;
            }
            throw new UserNotFoundException (Util.USER_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND);
        }
        return false;
    }

    private MailObject mailContent( final UserEntity fetchedUser ) {
        String emailId = fetchedUser.getEmailId ();
        String bodyContent = Util.createLink (
                Util.IP_ADDRESS + Util.ANGULAR_PORT_NUMBER + Util.REGISTRATION_VERIFICATION_LINK,
                jwtTokenProvider.createToken (fetchedUser.getUserName ()));
        String subject = Util.REGISTRATION_EMAIL_SUBJECT;
        return new MailObject (emailId, subject, bodyContent);
    }

    @Override
    public boolean isVerifiedUser( final String token ) {
        userRepository.verifyTheUser (jwtTokenProvider.getUserName (token));
        return true;
    }

    @Override
    public UserLoginInfo login( LoginDto loginDto ) throws UserNotFoundException, InvalidCredentialsException {
        /*login operation after disabling spring security*/
        Optional<UserEntity> fetchedUser = userRepository.findOneByUserName (loginDto.getUserName ());
//        fetched user present
        if (fetchedUser.isPresent ()) {
//            password matches
            if (passwordEncoder.matches (loginDto.getPassword (), fetchedUser.get ().getPassword ())) {
//                verified
                if (fetchedUser.get ().isVerified ()) {
                    String createdToken = jwtTokenProvider.createToken (fetchedUser.get ().getUserName ());
                    fetchedUser.get ().setLogin (true);
                    userRepository.loggedInUser (fetchedUser.get ().getUserName ());
                    return new UserLoginInfo (createdToken, fetchedUser.get ().getFirstName ());
                }
//                user is valid but not verified.
                emailServiceProvider.sendMail (mailContent (fetchedUser.get ()));
                return new UserLoginInfo ("", fetchedUser.get ().getFirstName ());
            }
//            password dint match
            throw new InvalidCredentialsException ("Oops...Invalid username/password supplied!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
//        not found
        throw new UserNotFoundException (Util.USER_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND);
    }

    @Override
    public void signOutUser( String token ) {
        Optional<UserEntity> fetchedUser = getAuthenticatedUser (token);
        fetchedUser.get ().setLogin (false);
        userRepository.signOutUser (fetchedUser.get ().getUserName ());
    }

    /**
     * get the user from the token provided from user repository and
     *
     * @param token as String input parameter
     * @return Optional<UserEntity>
     */
    private Optional<UserEntity> getAuthenticatedUser( final String token ) throws UserNotFoundException {
        Optional<UserEntity> fetchedUser = userRepository.findOneByUserName (jwtTokenProvider.getUserName (token));
        if (fetchedUser.isPresent ()) {
            return fetchedUser;
        }
        throw new UserNotFoundException (Util.USER_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<UserEntity> getActiveUsers() {
        return userRepository.findAll ()
                .stream ()
                .filter (fetchedUser -> fetchedUser.isLogin ())
                .collect (Collectors.toList ());
    }


}
