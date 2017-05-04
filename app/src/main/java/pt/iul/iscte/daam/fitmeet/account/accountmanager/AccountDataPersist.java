package pt.iul.iscte.daam.fitmeet.account.accountmanager;

import pt.iul.iscte.daam.fitmeet.account.model.Account;
import rx.Completable;
import rx.Single;

public interface AccountDataPersist {

  void saveAccount(Account account);

  Account getAccount();

  void removeAccount();
}
