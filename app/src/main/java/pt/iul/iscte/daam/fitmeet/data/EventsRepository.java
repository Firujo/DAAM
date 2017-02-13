package pt.iul.iscte.daam.fitmeet.data;

import android.support.annotation.NonNull;
import java.util.List;

/**
 * Created by Jdandradex on 2/12/2017.
 */
public interface EventsRepository {

  void getEvents(@NonNull LoadEventsCallback callback);

  void getEvent(@NonNull String eventId, @NonNull GetEventCallback callback);

  void saveEvent(@NonNull Event event);

  void refreshData();

  interface LoadEventsCallback {

    void onEventsLoaded(List<Event> events);
  }

  interface GetEventCallback {

    void onEventLoaded(Event event);
  }
}
