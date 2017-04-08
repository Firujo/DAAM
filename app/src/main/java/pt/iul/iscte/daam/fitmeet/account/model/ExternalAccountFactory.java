package pt.iul.iscte.daam.fitmeet.account.model;

public interface ExternalAccountFactory {

  Account createFacebookAccount(Account account);

  Account createGoogleAccount(Account account);
}
