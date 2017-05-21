package pt.iul.iscte.daam.fitmeet.eventdetail.presenter;

/**
 * Created by Utilizador on 5/21/2017.
 */

public class EventDetailPresenter implements EventDetailContract.UserActionsListener {

  private EventDetailContract.View eventsView;

  public EventDetailPresenter(EventDetailContract.View eventsView) {
    this.eventsView = eventsView;
  }

  @Override public void joinEventClicked() {

  }
}
