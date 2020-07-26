package com.amruthacollege.welcome.securities;

import com.amruthacollege.welcome.exceptions.UserAuthenticationException;
import com.amruthacollege.welcome.utility.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * -> Verify the access token's signature
 * -> Extract identity and authorization claims from Access token and use them to create UserContext
 * -> If Access token is malformed, expired or simply if token is not signed with the appropriate
 * signing key Authentication exception will be thrown
 *
 * @see {@link UserAuthenticationException} if user is not authenticated
 */
@Component
public class JwtTokenProvider {

    public String createToken( String userName, String role ) {
        Claims claims = Jwts.claims ().setSubject (userName);
        claims.put ("auth", role);
        Date currentDate = new Date ();
        Date validity = new Date (currentDate.getTime () + Util.VALIDITY_PERIOD_IN_MILLISECOND);
        return Jwts.builder ()
                .setClaims (claims)
                .setIssuedAt (currentDate)
                .setExpiration (validity)
                .signWith (SignatureAlgorithm.HS256, String.valueOf (Util.SECRET_KEY))
                .compact ();
    }

    public boolean isVerifiedUser( String token ) {
        try {
            Jwts.parser ().setSigningKey (String.valueOf (Util.SECRET_KEY)).parseClaimsJws (token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            throw new UserAuthenticationException ("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED);

        }
    }

    public String getUserName( String token ) {
        return Jwts.parser ()
                .setSigningKey (String.valueOf (Util.SECRET_KEY))
                .parseClaimsJws (token)
                .getBody ()
                .getSubject ();
    }
}