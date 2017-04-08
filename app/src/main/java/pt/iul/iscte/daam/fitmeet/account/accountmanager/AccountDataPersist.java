package pt.iul.iscte.daam.fitmeet.account.accountmanager;

import pt.iul.iscte.daam.fitmeet.account.model.Account;

public interface AccountDataPersist {

  void saveAccount(Account account);

  Account getAccount();

  void removeAccount();
}
