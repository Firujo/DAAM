package pt.iul.iscte.daam.fitmeet.account;

/**
 * Created by filipe on 01-05-2017.
 */

class RegisterCredentialsValidator {

  public boolean validate(String name, String username, String password,
      String passwordConfirmation) {
    if (name.isEmpty()) {
      return false;
    } else if (username.isEmpty()) {
      return false;
    } else if (password.isEmpty()) {
      return false;
    } else if (passwordConfirmation.isEmpty()) {
      return false;
    } else if (!password.equals(passwordConfirmation)) {
      return false;
    }
    return true;
  }
}
