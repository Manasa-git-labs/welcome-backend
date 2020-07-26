package com.amruthacollege.welcome.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

/**
 * This singleton class has the all the reusable methods => createLink
 */
public class Util {

    private Util() { }

    private static Random random = new Random ();

    public static final String IP_ADDRESS = "http://localhost:";
    public static final String SENDER_EMAIL_ID = System.getenv ("email");
    public static final String SENDER_PASSWORD = System.getenv ("paassword");
    public static final String ANGULAR_PORT_NUMBER = "4200";
    public static final String SPRING_PORT_NUMBER = "8081";
    public static final String REGISTRATION_VERIFICATION_LINK = "/verification";
    public static final String REGISTRATION_EMAIL_SUBJECT = "Registration Verification Link";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "Oops...User not found!";
//    token info
    public static final String SECRET_KEY = "welcome$20200726IVProject";
    public static final long VALIDITY_PERIOD_IN_MILLISECOND = 24 * 60 * 60 * 1000;// 1 day


    /**
     * this function just concatenate two parameter.
     *
     * @param url   as String input parameter
     * @param token as String input parameter
     * @return String
     */
    public static String createLink( String url, String token ) {
        return url + "/" + token;
    }

    public static String currentDateTime() {
        return LocalDateTime.now ().format (DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * This function generate random id.
     *
     * @return String data
     */
    public static String idGenerator() {
        return UUID.randomUUID ().toString ();
    }

    /**
     * This function generate order id
     *
     * @return String data
     */
    public static String generateOrderNumber() {
        String inputId = "#Du";
        inputId += random.nextInt (10000000);
        return inputId;
    }
}
