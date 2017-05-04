package pt.iul.iscte.daam.fitmeet.account.accountmanager;

import android.accounts.AccountManager;
import android.os.Build;
import pt.iul.iscte.daam.fitmeet.account.model.Account;
import pt.iul.iscte.daam.fitmeet.account.model.AccountFactory;

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

  @Override public void saveAccount(Account account) {
    final android.accounts.Account[] androidAccounts =
        androidAccountManager.getAccountsByType(accountType);

    final android.accounts.Account androidAccount;
    if (androidAccounts.length == 0) {
      androidAccount = new android.accounts.Account(account.getEmail(), accountType);
      try {
        androidAccountManager.addAccountExplicitly(androidAccount, account.getPassword(), null);
      } catch (SecurityException e) {
        throw e;
      }
    } else {
      androidAccount = androidAccounts[0];
    }

    androidAccountManager.setUserData(androidAccount, ACCOUNT_ID, account.getId());
    androidAccountManager.setUserData(androidAccount, ACCOUNT_NICKNAME, account.getNickname());
    androidAccountManager.setUserData(androidAccount, ACCOUNT_AVATAR_URL, account.getAvatar());
    androidAccountManager.setUserData(androidAccount, ACCOUNT_TYPE, account.getType().name());
  }

  @Override public Account getAccount() {
    if (accountCache != null) {
      return accountCache;
    }

    android.accounts.Account account = getAndroidAccount();

    return accountFactory.createAccount(
        androidAccountManager.getUserData(account, AndroidAccountDataPersist.ACCOUNT_ID),
        account.name,
        androidAccountManager.getUserData(account, AndroidAccountDataPersist.ACCOUNT_NICKNAME),
        androidAccountManager.getUserData(account, AndroidAccountDataPersist.ACCOUNT_AVATAR_URL),
        androidAccountManager.getPassword(account), Account.Type.valueOf(
            androidAccountManager.getUserData(account, AndroidAccountDataPersist.ACCOUNT_TYPE)));
  }

  @Override public void removeAccount() {
    if (Build.VERSION.SDK_INT >= 22) {
      androidAccountManager.removeAccountExplicitly(getAndroidAccount());
    } else {
      androidAccountManager.removeAccount(getAndroidAccount(), null, null);
    }
    accountCache = null;
  }

  private android.accounts.Account getAndroidAccount() {
    final android.accounts.Account[] accounts =
        androidAccountManager.getAccountsByType(accountType);

    if (accounts.length == 0) {
      throw new IllegalStateException("No account found.");
    }
    return accounts[0];
  }
}
