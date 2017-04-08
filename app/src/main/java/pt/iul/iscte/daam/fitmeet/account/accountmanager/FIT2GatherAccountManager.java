/*
 * Copyright (c) 2016.
 * Modified by SithEngineer on 27/06/2016.
 */

package pt.iul.iscte.daam.fitmeet.account.accountmanager;

import android.text.TextUtils;
import com.jakewharton.rxrelay.PublishRelay;
import java.net.SocketTimeoutException;
import pt.iul.iscte.daam.fitmeet.account.model.Account;
import pt.iul.iscte.daam.fitmeet.account.model.AccountFactory;
import pt.iul.iscte.daam.fitmeet.account.model.ExternalAccountFactory;
import pt.iul.iscte.daam.fitmeet.account.model.LocalAccount;
import rx.Completable;
import rx.Observable;
import rx.Single;

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

  public Observable<Account> accountStatus() {
    return Observable.merge(accountRelay, dataPersist.getAccount().onErrorReturn(throwable -> {
      return createLocalAccount();
    }).toObservable());
  }

  private Single<Account> singleAccountStatus() {
    return accountStatus().first().toSingle();
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
    return singleAccountStatus().onErrorReturn(throwable -> null).toBlocking().value();
  }

  public Completable logout() {
    return singleAccountStatus().flatMapCompletable(
        account -> account.logout().andThen(removeAccount()));
  }

  public Completable removeAccount() {
    return dataPersist.removeAccount().doOnCompleted(() -> accountRelay.call(createLocalAccount()));
  }

  private Completable saveAccount(Account account) {
    return dataPersist.saveAccount(account).doOnCompleted(() -> accountRelay.call(account));
  }

  public Completable signUp(String email, String password) {
    return credentialsValidator.validate(email, password, true)
        .andThen(accountManagerService.createAccount(email, password))
        .andThen(login(Account.Type.FIT2GATHER, email, password, null))
        .onErrorResumeNext(throwable -> {
          if (throwable instanceof SocketTimeoutException) {
            return login(Account.Type.FIT2GATHER, email, password, null);
          }
          return Completable.error(throwable);
        });
  }

  public Completable login(Account.Type type, final String email, final String password,
      final String name) {
    // TODO: 08/04/2017
    return Completable.complete();
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

  public Completable updateAccount(String nickname, String avatarPath) {
    return singleAccountStatus().flatMapCompletable(account -> {
      if (TextUtils.isEmpty(nickname) && TextUtils.isEmpty(avatarPath)) {
        return Completable.error(
            new AccountValidationException(AccountValidationException.EMPTY_NAME_AND_AVATAR));
      } else if (TextUtils.isEmpty(nickname)) {
        return Completable.error(
            new AccountValidationException(AccountValidationException.EMPTY_NAME));
      }
      return accountManagerService.updateAccount(account.getEmail(), nickname,
          account.getPassword(), TextUtils.isEmpty(avatarPath) ? "" : avatarPath);
    });
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
