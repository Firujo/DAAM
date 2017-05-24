package pt.iul.iscte.daam.fitmeet.account;

/**
 * Created by filipe on 21-05-2017.
 */

interface AccountActivityView {
  void navigateToSignUpOrLogin();

  void navigateToLoggedIn(String name);
}
