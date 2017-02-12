package pt.iul.iscte.daam.fitmeet.data;

import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.core.deps.guava.collect.Lists;
import android.support.v4.util.ArrayMap;
import java.util.List;

/**
 * Created by Jdandradex on 2/12/2017.
 */

public class EventsServiceApiImplementation implements EventsServiceApi {
  // TODO replace this with a new test specific data set.
  private static final ArrayMap<String, Event> NOTES_SERVICE_DATA = new ArrayMap();

  @Override public void getAllEvents(EventsServiceCallback<List<Event>> callback) {
    callback.onLoaded(Lists.newArrayList(NOTES_SERVICE_DATA.values()));
  }

  @Override public void getEvent(String noteId, EventsServiceCallback<Event> callback) {
    Event note = NOTES_SERVICE_DATA.get(noteId);
    callback.onLoaded(note);
  }

  @Override public void saveEvent(Event event) {
    NOTES_SERVICE_DATA.put(String.valueOf(event.getId()), event);
  }

  @VisibleForTesting public static void addEvents(Event... events) {
    for (Event event : events) {
      NOTES_SERVICE_DATA.put(String.valueOf(event.getId()), event);
    }
  }
}
