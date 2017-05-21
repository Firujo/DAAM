package pt.iul.iscte.daam.fitmeet.account;

import android.content.SharedPreferences;
import pt.iul.iscte.daam.fitmeet.Utils.SharedPreferencesUtils;

/**
 * Created by filipe on 21-05-2017.
 */

public class LoginStatusManager {
  private static LoginStatusManager instance;
  private SharedPreferences sharedPreferences;

  public LoginStatusManager(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  public static LoginStatusManager getInstance(SharedPreferences sharedPreferences) {
    if (instance == null) {
      instance = new LoginStatusManager(sharedPreferences);
    }
    return instance;
  }

  public void checkLoginStatus(AccountPresenter.LoginStatusListener listener) {
    boolean status = sharedPreferences.getBoolean(SharedPreferencesUtils.LOGIN_STATUS, false);
    listener.isLoggedIn(status);
  }

  public void saveLoginStatus(String username, String password) {
    sharedPreferences.edit().putString(SharedPreferencesUtils.LOGIN_EMAIL, username).apply();
    sharedPreferences.edit().putString(SharedPreferencesUtils.LOGIN_PASSWORD, password).apply();
    sharedPreferences.edit().putBoolean(SharedPreferencesUtils.LOGIN_STATUS, true).apply();
  }
}
