package pt.iul.iscte.daam.fitmeet.data;

import java.util.List;

/**
 * Created by Jdandradex on 2/12/2017.
 */
public interface EventsServiceApi {

  interface EventsServiceCallback<T> {

    void onLoaded(T events);
  }

  void getAllEvents(EventsServiceCallback<List<Event>> callback);

  void getEvent(String eventId, EventsServiceCallback<Event> callback);

  void saveEvent(Event event);
}
