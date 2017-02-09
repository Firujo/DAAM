package pt.iul.iscte.daam.fitmeet.events;

import android.support.annotation.NonNull;
import pt.iul.iscte.daam.fitmeet.data.Event;

/**
 * Created by jdandrade on 09/02/2017.
 */
public class EventsPresenter implements EventsContract.UserActionsListener {
  private final EventsContract.View mEventsView;

  public EventsPresenter(@NonNull EventsContract.View eventsView) {
    this.mEventsView = eventsView;
  }

  @Override public void loadEvents() {

  }

  @Override public void addNewEvent() {
    mEventsView.showAddEvent();
  }

  @Override public void openEventDetails(@NonNull Event requestedEvent) {
    mEventsView.showEventDetails(requestedEvent.getId());
  }
}
