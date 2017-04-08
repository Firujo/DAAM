package pt.iul.iscte.daam.fitmeet.account.accountmanager;

import pt.iul.iscte.daam.fitmeet.account.model.AccountFactory;
import rx.Completable;
import rx.Single;

public class AccountManagerService {

  private final AccountFactory accountFactory;

  public AccountManagerService(AccountFactory accountFactory) {
    this.accountFactory = accountFactory;
  }

  public Completable createAccount(String email, String password) {
    //// TODO: 08/04/2017 createAccount request
    return Completable.complete();
  }

  public Single login(String type, String email, String password, String name) {
    // TODO: 08/04/2017 login request
    return Single.just(null);
  }

  public Completable updateAccount(String email, String nickname, String password,
      String avatarUrl) {
    // TODO: 08/04/2017 update account request
    return Completable.complete();
  }
}