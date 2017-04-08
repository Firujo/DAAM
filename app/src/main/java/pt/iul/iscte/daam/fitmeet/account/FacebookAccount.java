package pt.iul.iscte.daam.fitmeet.account;

import com.facebook.login.LoginManager;
import rx.Completable;

public class FacebookAccount implements Account {

  private final Account account;

  public FacebookAccount(Account account) {
    this.account = account;
  }

  @Override public Completable logout() {
    return Completable.fromAction(() -> LoginManager.getInstance().logOut());
  }

  @Override public String getId() {
    return account.getId();
  }

  @Override public String getNickname() {
    return account.getNickname();
  }

  @Override public String getAvatar() {
    return account.getAvatar();
  }

  @Override public boolean isLoggedIn() {
    return account.isLoggedIn();
  }

  @Override public String getEmail() {
    return account.getEmail();
  }

  @Override public String getPassword() {
    return account.getPassword();
  }

  @Override public Type getType() {
    return Type.FACEBOOK;
  }
}
