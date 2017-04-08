package pt.iul.iscte.daam.fitmeet.account.model;

public class AccountFactory {

  private final ExternalAccountFactory externalAccountFactory;

  public AccountFactory(ExternalAccountFactory externalAccountFactory) {
    this.externalAccountFactory = externalAccountFactory;
  }

  public Account createAccount(String id, String name, String nickname, String avatar,
      String password, Account.Type type) {
    final Account aptoideAccount = new NativeAccount(id, name, nickname, avatar, password, type);
    switch (type) {
      case FIT2GATHER:
        return aptoideAccount;
      case FACEBOOK:
        return externalAccountFactory.createFacebookAccount(aptoideAccount);
      case GOOGLE:
        return externalAccountFactory.createGoogleAccount(aptoideAccount);
      default:
        throw new IllegalArgumentException("Illegal account type " + type);
    }
  }
}