package pt.iul.iscte.daam.fitmeet.account;

import android.content.Intent;
import pt.iul.iscte.daam.fitmeet.view.FragmentPresenter;

/**
 * Created by filipe on 26-05-2017.
 */

public class SignUpOrLoginPresenter implements FragmentPresenter {

  private FacebookLoginManager facebookLoginManager;
  private SignUpOrLoginView view;

  public SignUpOrLoginPresenter(FacebookLoginManager facebookLoginManager, SignUpOrLoginView view) {
    this.facebookLoginManager = facebookLoginManager;
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
