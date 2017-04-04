/*
 * Copyright (c) 2016.
 * Modified by SithEngineer on 02/09/2016.
 */

package pt.iul.iscte.daam.fitmeet.realm;

import android.os.HandlerThread;
import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Utilizador on 4/4/2017.
 */
public final class RealmSchedulers {

  private static final AtomicReference<RealmSchedulers> INSTANCE = new AtomicReference<>();
  private static final String THREAD_NAME = "Realm Thread";

  private final HandlerThread handlerThread;
  private final Scheduler threadScheduler;

  private RealmSchedulers() {
    handlerThread = new HandlerThread(THREAD_NAME);
    handlerThread.start();
    threadScheduler = AndroidSchedulers.from(handlerThread.getLooper());
  }

  public static Scheduler getScheduler() {
    return getInstance().threadScheduler;
  }

  /**
   * Spin-Lock to create a single instance of {@link RealmSchedulers}
   */
  private static RealmSchedulers getInstance() {
    for (; ; ) {
      RealmSchedulers current = INSTANCE.get();
      if (current != null) {
        return current;
      }
      current = new RealmSchedulers();
      if (INSTANCE.compareAndSet(null, current)) {
        return current;
      }
    }
  }

  public static boolean isRealmSchedulerThread(Thread otherThread) {
    return otherThread != null && TextUtils.equals(otherThread.getName(), THREAD_NAME);
  }
}
