package demo_Login;

public class Constants {
  public static String FACEBOOK_APP_ID = "3183327778383252";
  public static String FACEBOOK_APP_SECRET = "b88e5fcb9ed974d4287ca5f75610404f";
  public static String FACEBOOK_REDIRECT_URL = "https://localhost:8443/login-fb";
  public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
  
  public static String GOOGLE_CLIENT_ID = "450020186857-712ujs7enb5qfh1e7i2le6cga2kt90i4.apps.googleusercontent.com";
  public static String GOOGLE_CLIENT_SECRET = "db9oMIF4DcrWjH_MfCiQ7fVb";
  public static String GOOGLE_REDIRECT_URI = "https://localhost:8080/login-google";
  public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
  public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
  public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
