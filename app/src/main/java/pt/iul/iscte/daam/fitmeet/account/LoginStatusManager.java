package pt.iul.iscte.daam.fitmeet.account;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.firebase.auth.FirebaseAuth;
import pt.iul.iscte.daam.fitmeet.utils.SharedPreferencesUtils;

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

  public boolean isLoggedIn() {
    return sharedPreferences.getBoolean(SharedPreferencesUtils.LOGIN_STATUS, false);
  }

  public void saveLoginStatus(String email, String photoURL, String displayName, String type) {
    sharedPreferences.edit().putString(SharedPreferencesUtils.LOGIN_EMAIL, email).apply();
    sharedPreferences.edit().putString(SharedPreferencesUtils.LOGIN_PHOTO_URL, photoURL).apply();
    sharedPreferences.edit()
        .putString(SharedPreferencesUtils.LOGIN_DISPLAY_NAME, displayName)
        .apply();
    sharedPreferences.edit().putBoolean(SharedPreferencesUtils.LOGIN_STATUS, true).apply();
    sharedPreferences.edit().putString(SharedPreferencesUtils.LOGIN_ACCOUNT_TYPE, type).apply();
  }

  public void logout() {

    clearSharedPreferences();
  }

  private void clearSharedPreferences() {
    sharedPreferences.edit().remove(SharedPreferencesUtils.LOGIN_STATUS).commit();
    sharedPreferences.edit().remove(SharedPreferencesUtils.LOGIN_EMAIL).commit();
    sharedPreferences.edit().remove(SharedPreferencesUtils.LOGIN_PHOTO_URL).commit();
    sharedPreferences.edit().remove(SharedPreferencesUtils.LOGIN_DISPLAY_NAME).commit();
    sharedPreferences.edit().remove(SharedPreferencesUtils.LOGIN_ACCOUNT_TYPE).commit();
  }

  public String getLoginName() {
    String name = sharedPreferences.getString(SharedPreferencesUtils.LOGIN_EMAIL, "");
    if (TextUtils.isEmpty(name)) {
      name = sharedPreferences.getString(SharedPreferencesUtils.LOGIN_DISPLAY_NAME, "");
    }
    return name;
  }

  public String getLoginNameForDrawer() {
    if (isLoggedIn()) {
      return getLoginName();
    }
    return "";
  }

  public String getPhotoUrlForDrawer() {
    if (isLoggedIn()) {
      return getPhotoUrl();
    }
    return "";
  }

  public String getPhotoUrl() {
    return sharedPreferences.getString(SharedPreferencesUtils.LOGIN_PHOTO_URL, "");
  }

  public String getAccountType() {
    return sharedPreferences.getString(SharedPreferencesUtils.LOGIN_ACCOUNT_TYPE, "");
  }
}
