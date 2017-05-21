package pt.iul.iscte.daam.fitmeet;

import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by jdandrade on 08/04/2017.
 */

public class FIT2Gather extends android.app.Application {

  @Override public void onCreate() {
    super.onCreate();
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }

}
