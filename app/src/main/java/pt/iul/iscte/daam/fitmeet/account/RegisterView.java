package pt.iul.iscte.daam.fitmeet.account;

/**
 * Created by filipe on 30-04-2017.
 */

interface RegisterView {

  void showInvalidInputsMessage();

  void successfulRegistration();

  void showUnsuccessfulRegistration();
}
