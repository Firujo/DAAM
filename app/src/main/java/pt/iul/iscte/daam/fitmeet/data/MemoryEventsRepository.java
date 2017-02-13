package pt.iul.iscte.daam.fitmeet.data;

import android.support.annotation.NonNull;
import android.support.test.espresso.core.deps.guava.collect.ImmutableList;
import java.util.List;

/**
 * Created by Jdandradex on 2/12/2017.
 */
public class MemoryEventsRepository implements EventsRepository {
  private final EventsServiceApi mEventsServiceApi;

  /**
   * This method has reduced visibility for testing and is only visible to tests in the same
   * package.
   */
  private List<Event> mCachedEvents;

  public MemoryEventsRepository(@NonNull EventsServiceApi eventsServiceApi) {
    mEventsServiceApi = eventsServiceApi;
  }

  @Override public void getEvents(@NonNull final LoadEventsCallback callback) {
    // Load from API only if needed.
    if (mCachedEvents == null) {
      mEventsServiceApi.getAllEvents(new EventsServiceApi.EventsServiceCallback<List<Event>>() {
        @Override public void onLoaded(List<Event> notes) {
          mCachedEvents = ImmutableList.copyOf(notes);
          callback.onEventsLoaded(mCachedEvents);
        }
      });
    } else {
      callback.onEventsLoaded(mCachedEvents);
    }
  }

  @Override
  public void getEvent(@NonNull final String noteId, @NonNull final GetEventCallback callback) {
    // Load notes matching the id always directly from the API.
    mEventsServiceApi.getEvent(noteId, new EventsServiceApi.EventsServiceCallback<Event>() {
      @Override public void onLoaded(Event note) {
        callback.onEventLoaded(note);
      }
    });
  }

  @Override public void saveEvent(@NonNull Event event) {
    mEventsServiceApi.saveEvent(event);
    refreshData();
  }

  @Override public void refreshData() {
    mCachedEvents = null;
  }
}
