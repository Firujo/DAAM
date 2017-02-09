package pt.iul.iscte.daam.fitmeet.events;

import android.support.annotation.NonNull;

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

  @Override public void openEventDetails() {

  }
}
