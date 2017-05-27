package pt.iul.iscte.daam.fitmeet.account;

/**
 * Created by filipe on 20-05-2017.
 */

interface LoggedInView {

  void finish();

  void setupWelcomeInformation(String name, String photoUrl);
}
