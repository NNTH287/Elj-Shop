/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package constant;

/**
 *
 * @author Admin
 */
public interface IConstant {
    String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    String REGEX_FIRSTNAME = "^[a-zA-Z-. ]+$";
    String REGEX_LASTNAME = "^[a-zA-Z-. ]+$";
    String REGEX_STREET = "^[a-zA-Z0-9-. ]+$";
    String REGEX_CITY = "^[a-zA-Z0-9-. ]+$";
    String REGEX_PROVINCE = "^[a-zA-Z0-9-. ]+$";
    String REGEX_COUNTRY = "^[a-zA-Z ]+$";
    String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    String REGEX_PHONE = "^0[0-9]{9}$";
    char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    String SALT = "LHNHNH";
    String SMTP_HOST = "smtp.gmail.com";
    int SMTP_TLS_PORT = 587;
    String APP_EMAIL = "huynnthe176346@fpt.edu.vn";
    String APP_PASSWORD = "ivyc frds erdq eglg";
    public static String GOOGLE_CLIENT_ID = "114010930889-heqf5hnbf5eo1vfb4p5j02pcr8vl3bfu.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "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";
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
