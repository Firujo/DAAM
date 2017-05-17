package pt.iul.iscte.daam.fitmeet;

import android.content.Context;
import android.support.multidex.MultiDex;
import pt.iul.iscte.daam.fitmeet.account.accountmanager.AndroidAccountDataPersist;
import pt.iul.iscte.daam.fitmeet.account.accountmanager.FIT2GatherAccountManager;
import pt.iul.iscte.daam.fitmeet.account.model.AccountFactory;
import pt.iul.iscte.daam.fitmeet.account.model.SocialAccountFactory;

/**
 * Created by jdandrade on 08/04/2017.
 */

public class FIT2Gather extends android.app.Application {
  private FIT2GatherAccountManager accountManager;

  @Override public void onCreate() {
    super.onCreate();
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }

  public FIT2GatherAccountManager getAccountManager() {
    if (accountManager == null) {

      Context context = this;

      final AccountFactory accountFactory = new AccountFactory(new SocialAccountFactory());

      // TODO: 08/04/2017 init this
      final AndroidAccountDataPersist androidAccountDataPersist = null;

      accountManager =
          new FIT2GatherAccountManager.Builder().setAccountDataPersist(androidAccountDataPersist)
              .setAccountFactory(accountFactory)
              .build();
    }
    return accountManager;
  }
}
