package pt.iul.iscte.daam.fitmeet.account;


/**
 * Created by filipe on 01-05-2017.
 */

class RegisterCredentialsValidator {

  public static final int EMPTY_NAME = 0;
  public static final int EMPTY_USERNAME = 1;
  public static final int EMPTY_PASSWORD = 2;
  public static final int INVALID_PASSWORD_MATCH = 3;
  public static final int EMPTY_BIRTHDAY = 4;
  public static final int EMPTY_COUNTRY = 5;
  public static final int EMPTY_CITY = 6;
  public static final int SUCCESS = 7;

  public int validate(String name, String username, String password, String passwordConfirmation) {
    if (name.isEmpty()) {
      return EMPTY_NAME;
    } else if (username.isEmpty()) {
      return EMPTY_USERNAME;
    } else if (password.isEmpty()) {
      return EMPTY_PASSWORD;
    } else if (passwordConfirmation.isEmpty() || !password.equals(passwordConfirmation)) {
      return INVALID_PASSWORD_MATCH;
    }
    return SUCCESS;
  }
}
