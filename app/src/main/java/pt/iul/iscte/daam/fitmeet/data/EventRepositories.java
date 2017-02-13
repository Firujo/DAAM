package pt.iul.iscte.daam.fitmeet.data;

import android.support.annotation.NonNull;

/**
 * Created by Jdandradex on 2/12/2017.
 */

public class EventRepositories {
  private static EventsRepository repository = null;

  private EventRepositories() {
    // no instance
  }

  public synchronized static EventsRepository getInMemoryRepoInstance(
      @NonNull EventsServiceApi notesServiceApi) {
    if (null == repository) {
      repository = new MemoryEventsRepository(notesServiceApi);
    }
    return repository;
  }
}
