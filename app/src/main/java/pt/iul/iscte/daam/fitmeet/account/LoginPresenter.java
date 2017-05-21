package pt.iul.iscte.daam.fitmeet.account;

import android.content.Intent;
import pt.iul.iscte.daam.fitmeet.view.FragmentPresenter;

/**
 * Created by filipe on 08-04-2017.
 */

public class LoginPresenter implements FragmentPresenter {

  private LoginView view;
  private AppLoginManager appLoginManager;
  private FacebookLoginManager facebookLoginManager;
  private LoginStatusManager loginStatusManager;

  public LoginPresenter(LoginView view, AppLoginManager appLoginManager,
      FacebookLoginManager facebookLoginManager, LoginStatusManager loginStatusManager) {
    this.view = view;
    this.appLoginManager = appLoginManager;
    this.facebookLoginManager = facebookLoginManager;
    this.loginStatusManager = loginStatusManager;
  }

  @Override public void onCreateView() {

  }

  @Override public void onViewCreated() {
    facebookLoginManager.initializeLoginControls();
  }

  @Override public void onCreate() {
  }

  @Override public void onResume() {

  }

  @Override public void onPause() {

  }

  @Override public void onDestroy() {
    appLoginManager.stop();
  }

  @Override public void onStop() {
    appLoginManager.stop();
  }

  @Override public void onStart() {
    appLoginManager.setupAuthListener();
    facebookLoginManager.setupFacebookCallback(
        new FacebookLoginManager.FacebookLoginStatusListener() {

          @Override public void onSuccess() {
            System.out.println("success facebook");
          }

          @Override public void onError(int error) {
            System.out.println("error");
          }
        });
  }

  public void pressedLogin(String username, String password) {
    appLoginManager.login(username, password, new AppLoginManager.LoginListener() {

      @Override public void onSuccess() {
        view.showLoginSuccessfulToast();
        loginStatusManager.saveLoginStatus(username, password);

      }

      @Override public void onError(int error) {
        view.showErrorToast(error);
      }
    });
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    facebookLoginManager.onResult(requestCode, resultCode, data);
  }
}
