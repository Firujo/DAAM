package pt.iul.iscte.daam.fitmeet.newevent.model;

public class NewEventDataValidator {
  public void validate(String title, String description) throws NewEventValidationException {
    if (isEmpty(title) || isEmpty(description)) {
      throw new NewEventValidationException(NewEventValidationException.EMPTY_FIELDS);
    }
  }

  private boolean isEmpty(CharSequence str) {
    if (str == null || str.length() == 0) {
      return true;
    } else {
      return false;
    }
  }
}