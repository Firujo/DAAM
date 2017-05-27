package pt.iul.iscte.daam.fitmeet.account;

import pt.iul.iscte.daam.fitmeet.utils.SharedPreferencesUtils;

/**
 * Created by filipe on 20-05-2017.
 */

public class LoggedInManager {
  private LoginStatusManager loginStatusManager;
  private FacebookLoginManager facebookLoginManager;

  public LoggedInManager(LoginStatusManager loginStatusManager,
      FacebookLoginManager facebookLoginManager) {

    this.loginStatusManager = loginStatusManager;
    this.facebookLoginManager = facebookLoginManager;
  }

  public void logout() {
    if (loginStatusManager.getAccountType().equals(SharedPreferencesUtils.LOGIN_TYPE_FACEBOOK)) {
      facebookLoginManager.logout();
    }
    loginStatusManager.logout();
  }

  public String getLoginName() {
    return loginStatusManager.getLoginName();
  }

  public String getPhotoUrl() {
    return loginStatusManager.getPhotoUrl();
  }
}
