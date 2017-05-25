package pt.iul.iscte.daam.fitmeet.account;

import pt.iul.iscte.daam.fitmeet.view.Presenter;

/**
 * Created by filipe on 21-05-2017.
 */

public class AccountPresenter implements Presenter {
  private LoginStatusManager loginStatusManager;
  private AccountActivityView view;

  public AccountPresenter(LoginStatusManager loginStatusManager, AccountActivityView view) {
    this.loginStatusManager = loginStatusManager;
    this.view = view;

    getLoginStatus();
  }

  private void getLoginStatus() {
    loginStatusManager.checkLoginStatus(new LoginStatusListener() {
      @Override public void isLoggedIn(boolean isLoggedIn) {
        if (isLoggedIn) {
          view.navigateToLoggedIn();
        } else {
          view.navigateToSignUpOrLogin();
        }

      }

    });
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

  interface LoginStatusListener {
    void isLoggedIn(boolean isLoggedIn);
  }
}
