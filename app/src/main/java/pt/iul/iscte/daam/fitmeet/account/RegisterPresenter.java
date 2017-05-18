package pt.iul.iscte.daam.fitmeet.account;

import pt.iul.iscte.daam.fitmeet.view.FragmentPresenter;

/**
 * Created by filipe on 30-04-2017.
 */

public class RegisterPresenter implements FragmentPresenter {

  private RegisterView view;
  private RegisterManager registerManager;

  public RegisterPresenter(RegisterView view, RegisterManager registerManager) {
    this.view = view;
    this.registerManager = registerManager;
  }

  @Override public void onCreate() {
    registerManager.initializeFirebase();
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

  }

  public void pressedRegister(String name, String username, String password,
      String passwordConfirmation, String birthday, String country, String city) {
    registerManager.registerNewUser(name, username, password, passwordConfirmation, birthday,
        country, city,
        new RegisterListener() {
          @Override public void onCompleteRegistration(int success) {
            if (success == RegisterManager.INVALID_INPUTS) {
              view.showInvalidInputsMessage();
            } else if (success == RegisterManager.SUCCESSFUL_REGISTRATION) {
              view.successfulRegistration();
            } else {
              view.showUnsuccessfulRegistration();
            }
          }
        });
  }

  interface RegisterListener {

    void onCompleteRegistration(int success);
  }
}
