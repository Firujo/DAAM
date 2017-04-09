package pt.iul.iscte.daam.fitmeet.account;

import pt.iul.iscte.daam.fitmeet.view.FragmentPresenter;

/**
 * Created by filipe on 08-04-2017.
 */

public class LoginPresenter implements FragmentPresenter {

  private LoginView view;
  private AppLoginManager appLoginManager;

  public LoginPresenter(LoginView view, AppLoginManager appLoginManager) {
    this.view = view;
    this.appLoginManager = appLoginManager;
  }

  @Override public void onCreateView() {

  }

  @Override public void onViewCreated() {
    appLoginManager.initializeAuth();
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
    appLoginManager.removeAuthListener();
  }

  @Override public void onStart() {
    appLoginManager.setupAuthListener();
  }

  public void pressedLogin(String username, String password) {
    appLoginManager.login(username, password, new AppLoginManager.LoginListener() {

      @Override public void onSuccess() {
        view.showLoginSuccessfulToast();
      }

      @Override public void onError(int error) {
        view.showErrorToast(error);
      }
    });
  }
}
