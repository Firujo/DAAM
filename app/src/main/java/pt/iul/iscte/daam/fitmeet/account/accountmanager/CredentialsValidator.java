package pt.iul.iscte.daam.fitmeet.account.accountmanager;

public class CredentialsValidator {
  /**
   * Returns true if email and password are not empty. If validate password content is enable
   * returns true if password is at least 8 characters long and has at least 1 number and 1 letter.
   *
   * @param email to be validated.
   * @param password to be validated.
   * @param validatePassword whether password content should be validated.
   */
  public void validate(String email, String password, boolean validatePassword)
      throws AccountValidationException {
    if (isEmpty(email) && isEmpty(password)) {
      throw new AccountValidationException(AccountValidationException.EMPTY_EMAIL_AND_PASSWORD);
    } else if (isEmpty(password)) {
      throw new AccountValidationException(AccountValidationException.EMPTY_PASSWORD);
    } else if (isEmpty(email)) {
      throw new AccountValidationException(AccountValidationException.EMPTY_EMAIL);
    } else if (validatePassword && (password.length() < 8 || !has1number1letter(password))) {
      throw new AccountValidationException(AccountValidationException.INVALID_PASSWORD);
    }
  }

  private boolean has1number1letter(String password) {
    boolean hasLetter = false;
    boolean hasNumber = false;

    for (char c : password.toCharArray()) {
      if (!hasLetter && Character.isLetter(c)) {
        if (hasNumber) return true;
        hasLetter = true;
      } else if (!hasNumber && Character.isDigit(c)) {
        if (hasLetter) return true;
        hasNumber = true;
      }
    }
    if (password.contains("!")
        || password.contains("@")
        || password.contains("#")
        || password.contains("$")
        || password.contains("#")
        || password.contains("*")) {
      hasNumber = true;
    }

    return hasNumber && hasLetter;
  }

  private boolean isEmpty(CharSequence str) {
    if (str == null || str.length() == 0) {
      return true;
    } else {
      return false;
    }
  }
}