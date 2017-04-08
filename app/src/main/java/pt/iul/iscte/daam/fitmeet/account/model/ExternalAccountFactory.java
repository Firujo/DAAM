package pt.iul.iscte.daam.fitmeet.account.model;

import pt.iul.iscte.daam.fitmeet.account.model.Account;

public interface ExternalAccountFactory {

  Account createFacebookAccount(Account account);

  Account createGoogleAccount(Account account);
}
