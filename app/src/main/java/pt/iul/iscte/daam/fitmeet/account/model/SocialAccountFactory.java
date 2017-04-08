package pt.iul.iscte.daam.fitmeet.account.model;

public class SocialAccountFactory implements ExternalAccountFactory {

  public SocialAccountFactory() {
  }

  @Override public Account createFacebookAccount(Account account) {
    return new FacebookAccount(account);
  }

  @Override public Account createGoogleAccount(Account account) {
    return null;
  }
}
