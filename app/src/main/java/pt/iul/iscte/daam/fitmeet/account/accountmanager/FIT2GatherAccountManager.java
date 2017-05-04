/*
 * Copyright (c) 2016.
 * Modified by SithEngineer on 27/06/2016.
 */

package pt.iul.iscte.daam.fitmeet.account.accountmanager;

import android.text.TextUtils;
import com.jakewharton.rxrelay.PublishRelay;
import pt.iul.iscte.daam.fitmeet.account.model.Account;
import pt.iul.iscte.daam.fitmeet.account.model.AccountFactory;
import pt.iul.iscte.daam.fitmeet.account.model.ExternalAccountFactory;
import pt.iul.iscte.daam.fitmeet.account.model.LocalAccount;

public class FIT2GatherAccountManager {

  public static final String IS_FACEBOOK_OR_GOOGLE = "facebook_google";

  private final CredentialsValidator credentialsValidator;
  private final PublishRelay<Account> accountRelay;
  private final AccountDataPersist dataPersist;
  private final AccountManagerService accountManagerService;

  private FIT2GatherAccountManager(CredentialsValidator credentialsValidator,
      AccountDataPersist dataPersist, AccountManagerService accountManagerService,
      PublishRelay<Account> accountRelay) {
    this.credentialsValidator = credentialsValidator;
    this.dataPersist = dataPersist;
    this.accountManagerService = accountManagerService;
    this.accountRelay = accountRelay;
  }

  public Account accountStatus() {
    return dataPersist.getAccount();
    //return createLocalAccount();
  }

  private Account singleAccountStatus() {
    return accountStatus();
  }

  private Account createLocalAccount() {
    return new LocalAccount();
  }

  /**
   * Use {@link Account#isLoggedIn()} instead.
   *
   * @return true if user is logged in, false otherwise.
   */
  @Deprecated public boolean isLoggedIn() {
    final Account account = getAccount();
    return account != null && account.isLoggedIn();
  }

  /**
   * Use {@link #accountStatus()} method instead.
   *
   * @return user Account
   */
  @Deprecated public Account getAccount() {
    return singleAccountStatus();
  }

  public void logout() {
    singleAccountStatus().logout();
    removeAccount();
  }

  public void removeAccount() {
    dataPersist.removeAccount();
  }

  private void saveAccount(Account account) {
    dataPersist.saveAccount(account);
  }

  public void signUp(String email, String password) {
    try {
      credentialsValidator.validate(email, password, true);
    } catch (AccountValidationException e) {
      return;
    }
    accountManagerService.createAccount(email, password);
    login(Account.Type.FIT2GATHER, email, password, null);
  }

  public void login(Account.Type type, final String email, final String password,
      final String name) {
    // TODO: 08/04/2017
  }

  /**
   * Use {@link Account#getEmail()} instead.
   *
   * @return user e-mail.
   */
  @Deprecated public String getAccountEmail() {
    final Account account = getAccount();
    return account == null ? null : account.getEmail();
  }

  public void updateAccount(String nickname, String avatarPath) throws AccountValidationException {
    if (TextUtils.isEmpty(nickname) && TextUtils.isEmpty(avatarPath)) {
      throw new AccountValidationException(AccountValidationException.EMPTY_NAME_AND_AVATAR);
    } else if (TextUtils.isEmpty(nickname)) {
      throw new AccountValidationException(AccountValidationException.EMPTY_NAME);
    }
    accountManagerService.updateAccount(singleAccountStatus().getEmail(), nickname,
        singleAccountStatus().getPassword(), TextUtils.isEmpty(avatarPath) ? "" : avatarPath);
  }

  public static class Builder {

    private CredentialsValidator credentialsValidator;
    private AccountManagerService accountManagerService;
    private PublishRelay<Account> accountRelay;
    private AccountDataPersist accountDataPersist;
    private AccountFactory accountFactory;

    private ExternalAccountFactory externalAccountFactory;

    public Builder setCredentialsValidator(CredentialsValidator credentialsValidator) {
      this.credentialsValidator = credentialsValidator;
      return this;
    }

    public Builder setAccountManagerService(AccountManagerService accountManagerService) {
      this.accountManagerService = accountManagerService;
      return this;
    }

    public Builder setAccountRelay(PublishRelay<Account> accountRelay) {
      this.accountRelay = accountRelay;
      return this;
    }

    public Builder setAccountDataPersist(AccountDataPersist accountDataPersist) {
      this.accountDataPersist = accountDataPersist;
      return this;
    }

    public Builder setExternalAccountFactory(ExternalAccountFactory externalAccountFactory) {
      this.externalAccountFactory = externalAccountFactory;
      return this;
    }

    public Builder setAccountFactory(AccountFactory accountFactory) {
      this.accountFactory = accountFactory;
      return this;
    }

    public FIT2GatherAccountManager build() {

      if (accountDataPersist == null) {
        throw new IllegalArgumentException("AccountDataPersist is mandatory.");
      }

      if (accountManagerService == null) {

        if (accountFactory == null) {

          if (externalAccountFactory == null) {
            throw new IllegalArgumentException(
                "ExternalAccountFactory is mandatory if AccountFactory is not provided.");
          }

          this.accountFactory = new AccountFactory(externalAccountFactory);
        }

        accountManagerService = new AccountManagerService(accountFactory);
      }

      if (credentialsValidator == null) {
        credentialsValidator = new CredentialsValidator();
      }

      if (accountRelay == null) {
        accountRelay = PublishRelay.create();
      }

      return new FIT2GatherAccountManager(credentialsValidator, accountDataPersist,
          accountManagerService, accountRelay);
    }
  }
}
