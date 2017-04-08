package pt.iul.iscte.daam.fitmeet.account.accountmanager;

import pt.iul.iscte.daam.fitmeet.account.model.Account;
import rx.Completable;
import rx.Single;

public interface AccountDataPersist {

  Completable saveAccount(Account account);

  Single<Account> getAccount();

  Completable removeAccount();
}
