package pt.iul.iscte.daam.fitmeet.events;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pt.iul.iscte.daam.fitmeet.data.Difficulty;
import pt.iul.iscte.daam.fitmeet.data.Event;
import pt.iul.iscte.daam.fitmeet.data.EventsRepository;

/**
 * Created by jdandrade on 09/02/2017.
 */
public class EventsPresenter implements EventsContract.UserActionsListener {
  private final EventsRepository mEventsRepository;
  private final EventsContract.View mEventsView;

  public EventsPresenter(@NonNull EventsRepository mEventsRepository,
      @NonNull EventsContract.View eventsView) {
    this.mEventsRepository = mEventsRepository;
    this.mEventsView = eventsView;
  }

  @Override public void loadEvents(boolean bypassCache) {
    mEventsView.setProgressIndicator(true);

    if (bypassCache) {
      mEventsRepository.refreshData();
    }

    List<Event> events = getFakeEvents();

    mEventsView.showEvents(events);
    mEventsView.setProgressIndicator(false);

    //mEventsRepository.getEvents(new EventsRepository.LoadEventsCallback() {
    //  @Override public void onEventsLoaded(List<Event> events) {
    //    mEventsView.setProgressIndicator(false);
    //    mEventsView.showEvents(events);
    //  }
    //});
  }

  @NonNull private List<Event> getFakeEvents() {
    List<Event> events = new ArrayList<>();

    events.add(new Event(1, "tragam as mines!", "corrida do benfica", new Date(),
        "http://images.huffingtonpost.com/2016-08-07-1470611179-5139689-MorningRun.png",
        Difficulty.MEDIUM, null));
    events.add(new Event(1, "tragam as mines!", "corrida do benfica", new Date(),
        "http://images.huffingtonpost.com/2016-08-07-1470611179-5139689-MorningRun.png",
        Difficulty.MEDIUM, null));
    events.add(new Event(1, "tragam as mines!", "corrida do benfica", new Date(),
        "http://images.huffingtonpost.com/2016-08-07-1470611179-5139689-MorningRun.png",
        Difficulty.MEDIUM, null));
    events.add(new Event(1, "tragam as mines!", "corrida do benfica", new Date(),
        "http://images.huffingtonpost.com/2016-08-07-1470611179-5139689-MorningRun.png",
        Difficulty.MEDIUM, null));
    return events;
  }

  @Override public void addNewEvent() {
    mEventsView.showAddEvent();
  }

  @Override public void openEventDetails(@NonNull Event requestedEvent) {
    mEventsView.showEventDetails(requestedEvent.getId());
  }
}
