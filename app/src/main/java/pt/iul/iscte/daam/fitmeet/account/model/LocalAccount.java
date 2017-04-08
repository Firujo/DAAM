package pt.iul.iscte.daam.fitmeet.account.model;

import pt.iul.iscte.daam.fitmeet.account.model.Account;
import rx.Completable;

public class LocalAccount implements Account {

  @Override public Completable logout() {
    return Completable.complete();
  }

  @Override public String getId() {
    return "";
  }

  @Override public String getNickname() {
    return "";
  }

  @Override public String getAvatar() {
    return "";
  }

  @Override public boolean isLoggedIn() {
    return false;
  }

  @Override public String getEmail() {
    return "";
  }

  @Override public String getPassword() {
    return "";
  }

  @Override public Type getType() {
    return Type.LOCAL;
  }
}
