package pt.iul.iscte.daam.fitmeet.account;

import rx.Completable;

/**
 * User account information.
 */
public interface Account {

  /**
   * Changes state of the account to logged out.
   *
   * @return Completable to perform logout.
   */
  Completable logout();

  /**
   * Returns the id of the account.
   */
  String getId();

  /**
   * Returns the user's nickname.
   */
  String getNickname();

  /**
   * Returns the user's avatar URL.
   */
  String getAvatar();

  /**
   * Returns whether user is logged in or not.
   */
  boolean isLoggedIn();

  /**
   * Returns user's e-mail.
   */
  String getEmail();

  /**
   * Returns account's password.
   */
  String getPassword();

  /**
   * Account type.
   */
  public enum Type {
    /**
     * Default account when user did not login yet.
     */
    LOCAL, /**
     * Account created when user has logged in using in-house services.
     */
    FIT2GATHER, /**
     * Account created when user has logged in using Google services.
     */
    GOOGLE, /**
     * Account created when user has logged in using Facebook services.
     */
    FACEBOOK
  }
}
