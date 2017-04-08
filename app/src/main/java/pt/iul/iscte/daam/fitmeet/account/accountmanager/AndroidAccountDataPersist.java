package pt.iul.iscte.daam.fitmeet.account.accountmanager;

import android.accounts.AccountManager;
import android.os.Build;
import java.util.concurrent.Executors;
import pt.iul.iscte.daam.fitmeet.account.model.Account;
import pt.iul.iscte.daam.fitmeet.account.model.AccountFactory;
import rx.Completable;
import rx.Single;
import rx.schedulers.Schedulers;

/**
 * Persists {@link Account} data in using {@link AccountManager}.
 */
public class AndroidAccountDataPersist implements AccountDataPersist {

  private static final String ACCOUNT_TYPE = "account_manager_login_mode";
  private static final String ACCOUNT_NICKNAME = "username";
  private static final String ACCOUNT_AVATAR_URL = "useravatar";
  private static final String ACCOUNT_ID = "userId";

  private final String accountType;
  private final AccountManager androidAccountManager;
  private final AccountFactory accountFactory;

  private Account accountCache;

  public AndroidAccountDataPersist(String accountType, AccountManager androidAccountManager,
      AccountFactory accountFactory) {
    this.accountType = accountType;
    this.androidAccountManager = androidAccountManager;
    this.accountFactory = accountFactory;
  }

  @Override public Completable saveAccount(Account account) {
    return Completable.defer(() -> {
      final android.accounts.Account[] androidAccounts =
          androidAccountManager.getAccountsByType(accountType);

      final android.accounts.Account androidAccount;
      if (androidAccounts.length == 0) {
        androidAccount = new android.accounts.Account(account.getEmail(), accountType);
        try {
          androidAccountManager.addAccountExplicitly(androidAccount, account.getPassword(), null);
        } catch (SecurityException e) {
          return Completable.error(e);
        }
      } else {
        androidAccount = androidAccounts[0];
      }

      androidAccountManager.setUserData(androidAccount, ACCOUNT_ID, account.getId());
      androidAccountManager.setUserData(androidAccount, ACCOUNT_NICKNAME, account.getNickname());
      androidAccountManager.setUserData(androidAccount, ACCOUNT_AVATAR_URL, account.getAvatar());
      androidAccountManager.setUserData(androidAccount, ACCOUNT_TYPE, account.getType().name());
      return Completable.complete().doOnCompleted(() -> accountCache = account);
    }).subscribeOn(Schedulers.io());
  }

  @Override public Single<Account> getAccount() {
    if (accountCache != null) {
      return Single.just(accountCache);
    }

    return getAndroidAccount().flatMap(androidAccount -> Single.just(null)
        .map(__ -> accountFactory.createAccount(
            androidAccountManager.getUserData(androidAccount, AndroidAccountDataPersist.ACCOUNT_ID),
            androidAccount.name, androidAccountManager.getUserData(androidAccount,
                AndroidAccountDataPersist.ACCOUNT_NICKNAME),
            androidAccountManager.getUserData(androidAccount,
                AndroidAccountDataPersist.ACCOUNT_AVATAR_URL),
            androidAccountManager.getPassword(androidAccount), Account.Type.valueOf(
                androidAccountManager.getUserData(androidAccount,
                    AndroidAccountDataPersist.ACCOUNT_TYPE)))));
  }

  @Override public Completable removeAccount() {
    return getAndroidAccount().doOnSuccess(androidAccount -> {
      if (Build.VERSION.SDK_INT >= 22) {
        androidAccountManager.removeAccountExplicitly(androidAccount);
      } else {
        androidAccountManager.removeAccount(androidAccount, null, null);
      }
      accountCache = null;
    }).toCompletable();
  }

  private Single<android.accounts.Account> getAndroidAccount() {
    return Single.defer(() -> {
      final android.accounts.Account[] accounts =
          androidAccountManager.getAccountsByType(accountType);

      if (accounts.length == 0) {
        return Single.error(new IllegalStateException("No account found."));
      }
      return Single.just(accounts[0]);
    }).observeOn(Schedulers.from(Executors.newSingleThreadExecutor()));
  }
}
