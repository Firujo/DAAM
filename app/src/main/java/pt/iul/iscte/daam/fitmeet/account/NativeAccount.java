/*
 * Copyright (c) 2017.
 * Modified by Marcelo Benites on 10/02/2017.
 */

package pt.iul.iscte.daam.fitmeet.account;

import rx.Completable;

public final class NativeAccount implements Account {

  private final String id;
  private final String email;
  private final String nickname;
  private final String avatar;
  private final Type type;
  private final String password;

  public NativeAccount(String id, String email, String nickname, String avatar, String password,
      Type type) {
    this.id = id;
    this.email = email;
    this.nickname = nickname;
    this.avatar = avatar;
    this.password = password;
    this.type = type;
  }

  @Override public Completable logout() {
    return Completable.complete();
  }

  @Override public String getId() {
    return id;
  }

  @Override public String getNickname() {
    return nickname;
  }

  @Override public String getAvatar() {
    return avatar;
  }

  @Override public boolean isLoggedIn() {
    return (!isEmpty(getEmail()) && !isEmpty(getPassword()));
  }

  @Override public String getEmail() {
    return email;
  }

  @Override public String getPassword() {
    return password;
  }

  @Override public Type getType() {
    return type;
  }

  private boolean isEmpty(String string) {
    return string == null || string.trim().length() == 0;
  }

  @Override public String toString() {
    return "AptoideAccount{"
        + "id='"
        + id
        + '\''
        + ", email='"
        + email
        + '\''
        + ", nickname='"
        + nickname
        + '\''
        + ", avatar='"
        + avatar
        + '\''
        + ", type="
        + type
        + ", password='"
        + password
        + '\''
        + '}';
  }
}
