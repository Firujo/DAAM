package pt.iul.iscte.daam.fitmeet.account;

import pt.iul.iscte.daam.fitmeet.view.FragmentPresenter;

/**
 * Created by filipe on 20-05-2017.
 */

public class LoggedInPresenter implements FragmentPresenter {

  private LoggedInView view;
  private LoggedInManager loggedInManager;

  public LoggedInPresenter(LoggedInView view, LoggedInManager loggedInManager) {
    this.view = view;
    this.loggedInManager = loggedInManager;
  }

  @Override public void onCreate() {

  }

  @Override public void onResume() {

  }

  @Override public void onPause() {

  }

  @Override public void onDestroy() {

  }

  @Override public void onStop() {

  }

  @Override public void onStart() {

  }

  @Override public void onCreateView() {

  }

  @Override public void onViewCreated() {
    String name = loggedInManager.getLoginName();
    String photoUrl = loggedInManager.getPhotoUrl();
    view.setupWelcomeInformation(name, photoUrl);
  }

  public void pressedLogout() {
    loggedInManager.logout();
    view.finish();
  }
}
