package com.example.nos.secureflomessaging;

/**
 * Created by Nos on 11/9/2016.
 */

public class Constants {

    //Constants for web connections
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 100000;
    public static final int STATUS_ERROR = 400;
    public static final int STATUS_UNAUTHORIZED = 401;

    //Application key and secret to access api
    public static final String APP_KEY = "3a0ca6b41ece4de2964c96f4b124a928";
    public static final String APP_SECRET = "5b6e90d8e8064e789aa908b8fc256438";

    //URL's to be used to access the API
    public static final String END_POINT = "http://www.galadriel.cs.utsa.edu/group2/public_html/api";
    public static final String LOGIN_URL =  END_POINT + "/login.php";
    public static final String INFO_URL = END_POINT + "/info.php";
    public static final String UPDATE_URL = END_POINT + "/update.php";
    public static final String DELETE_URL = END_POINT + "/delete.php";
    public static final String RESET_URL = END_POINT + "/reset.php";

    //Constants used in JSON Parsing or values attached in a URL server connection
    public static final String AUTHORIZATION = "Authorization";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_CREDENTIALS = "client_credentials";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String ACCESS = "access";
    public static final String INFO = "info";
    public static final String STATUS = "status";
    public static final String MESSAGE = "msg";
    public static final String ID = "id";
    public static final String ID_INFO = "ID";

    public static final String CONNECTION_MESSAGE = "No Internet Connection!";

}
