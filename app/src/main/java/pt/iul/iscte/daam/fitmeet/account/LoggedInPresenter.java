package pt.iul.iscte.daam.fitmeet.account;

import pt.iul.iscte.daam.fitmeet.view.FragmentPresenter;

/**
 * Created by filipe on 20-05-2017.
 */

public class LoggedInPresenter implements FragmentPresenter {

  private LoggedInView view;
  private LoginStatusManager loginStatusManager;

  public LoggedInPresenter(LoggedInView view, LoginStatusManager loginStatusManager) {
    this.view = view;
    this.loginStatusManager = loginStatusManager;
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
    String name = loginStatusManager.getLoginName();
    view.setupWelcomeMessage(name);
  }

  public void pressedLogout() {
    loginStatusManager.logout();
    view.finish();
  }
}
