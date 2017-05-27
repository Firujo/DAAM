package pt.iul.iscte.daam.fitmeet.account;

import android.content.Intent;
import pt.iul.iscte.daam.fitmeet.utils.SharedPreferencesUtils;
import pt.iul.iscte.daam.fitmeet.view.FragmentPresenter;

/**
 * Created by filipe on 26-05-2017.
 */

public class SignUpOrLoginPresenter implements FragmentPresenter {

  private FacebookLoginManager facebookLoginManager;
  private LoginStatusManager loginStatusManager;
  private SignUpOrLoginView view;

  public SignUpOrLoginPresenter(FacebookLoginManager facebookLoginManager,
      LoginStatusManager loginStatusManager, SignUpOrLoginView view) {
    this.facebookLoginManager = facebookLoginManager;
    this.loginStatusManager = loginStatusManager;
    this.view = view;
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
    facebookLoginManager.setupFacebookCallback(new LoginPresenter.LoginListener() {

      @Override public void onSuccess(String email, String photourl, String displayName) {
        loginStatusManager.saveLoginStatus(email, photourl, displayName,
            SharedPreferencesUtils.LOGIN_TYPE_FACEBOOK);
        view.finish();
          }

          @Override public void onError(int error) {
            System.out.println("error");
          }
        });
  }

  @Override public void onCreateView() {

  }

  @Override public void onViewCreated() {
    facebookLoginManager.initializeLoginControls();
  }

  public void pressedRegister() {
    view.openRegisterFragment();
  }

  public void pressedLogin() {
    view.openLoginFragment();
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    facebookLoginManager.onResult(requestCode, resultCode, data);
  }
}
