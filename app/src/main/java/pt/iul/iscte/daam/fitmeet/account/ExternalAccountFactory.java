package pt.iul.iscte.daam.fitmeet.account;

public interface ExternalAccountFactory {

  Account createFacebookAccount(Account account);

  Account createGoogleAccount(Account account);
}
