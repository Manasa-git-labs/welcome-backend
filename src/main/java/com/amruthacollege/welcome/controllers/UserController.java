package com.amruthacollege.welcome.controllers;

import com.amruthacollege.welcome.dtos.UserDto;
import com.amruthacollege.welcome.responses.Response;
import com.amruthacollege.welcome.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registration( @RequestBody final UserDto userDto ) {
        boolean isRegistered = userService.register (userDto);
        if (!isRegistered) {
            return ResponseEntity.status (HttpStatus.ALREADY_REPORTED)
                    .body (new Response ("User already exist... please login!", 208));
        }
        return ResponseEntity.status (HttpStatus.CREATED)
                .body (new Response ("Registration successful... check your mail for verification!", 201));
    }

    @PutMapping("/verification/{token}")
    public ResponseEntity<Response> verifyRegistration( @PathVariable("token") final String token ) {
        if (userService.isVerifiedUser (token)) {
            return ResponseEntity.ok (new Response ("Account verified successfully.", 200));
        }
        return ResponseEntity.status (HttpStatus.NOT_ACCEPTABLE)
                .body (new Response ("Invalid verification attempt", 406));
    }
}
