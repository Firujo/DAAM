package pt.iul.iscte.daam.fitmeet.account.model;

public class AccountFactory {

  private final ExternalAccountFactory externalAccountFactory;

  public AccountFactory(ExternalAccountFactory externalAccountFactory) {
    this.externalAccountFactory = externalAccountFactory;
  }

  public Account createAccount(String id, String name, String nickname, String avatar,
      String password, Account.Type type) {
    final Account fit2gatherAccount = new NativeAccount(id, name, nickname, avatar, password, type);
    switch (type) {
      case FIT2GATHER:
        return fit2gatherAccount;
      case FACEBOOK:
        return externalAccountFactory.createFacebookAccount(fit2gatherAccount);
      case GOOGLE:
        return externalAccountFactory.createGoogleAccount(fit2gatherAccount);
      default:
        throw new IllegalArgumentException("Illegal account type " + type);
    }
  }
}