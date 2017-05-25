package pt.iul.iscte.daam.fitmeet.eventdetail.presenter;

import android.support.test.espresso.core.deps.guava.base.Strings;
import java.util.Date;
import pt.iul.iscte.daam.fitmeet.data.Difficulty;
import pt.iul.iscte.daam.fitmeet.data.Event;
import pt.iul.iscte.daam.fitmeet.data.EventsRepository;
import pt.iul.iscte.daam.fitmeet.data.User;

/**
 * Created by Utilizador on 5/21/2017.
 */

public class EventDetailPresenter implements EventDetailContract.UserActionsListener {

  private String eventId;
  private EventDetailContract.View eventsDetailView;
  private EventsRepository eventsRepository;

  public EventDetailPresenter(String eventId, EventDetailContract.View eventsDetailView,
      EventsRepository eventsRepository) {
    this.eventId = eventId;
    this.eventsDetailView = eventsDetailView;
    this.eventsRepository = eventsRepository;
  }

  @Override public void joinEventClicked() {

  }

  @Override public void onCreate() {

  }

  @Override public void onResume() {

  }

  @Override public void onPause() {

  }

  @Override public void onDestroy() {

  }

  @Override public void onStop() {

  }

  @Override public void onStart() {
    openEvent();
  }

  private void openEvent() {
    eventsDetailView.setLoadingIndicator(true);

    eventsRepository.getEvent(eventId, event -> {
      eventsDetailView.setLoadingIndicator(false);
      showEvent(
          new Event(1, "tragam as mines!", "corrida do benfica", new Date(), "benfica!", 90,
              "http://images.huffingtonpost.com/2016-08-07-1470611179-5139689-MorningRun.png",
              Difficulty.MEDIUM, new User(1, "jonenz", "jonenz@richenz.comenz",
              "http://smalldata.io/startup/common-files/icons/sdl_logo.png"), 19, 10, "public"));
    });
  }

  private void showEvent(Event event) {
    String title = event.getTitle();
    String location = event.getLocation();
    String numberOfAttendees = String.valueOf(event.getNumberOfAttendees());
    String featureGraphicUrl = event.getImageUrl();
    String privacy = event.getPrivacy();

    if (Strings.isNullOrEmpty(title)) {
      eventsDetailView.hideEventDetailTitle();
    } else {
      eventsDetailView.showEventDetailTitle(title);
    }

    if (Strings.isNullOrEmpty(location)) {
      eventsDetailView.hideEventDetailLocation();
    } else {
      eventsDetailView.showEventDetailLocation(location);
    }

    if (Strings.isNullOrEmpty(numberOfAttendees)) {
      eventsDetailView.showEventDetailNumberOfRunners("-");
    } else {
      eventsDetailView.showEventDetailNumberOfRunners(numberOfAttendees);
    }

    if (Strings.isNullOrEmpty(featureGraphicUrl)) {
      // TODO: 5/25/2017 show placeholder
    } else {
      eventsDetailView.showEventDetailFeatureGraphic(featureGraphicUrl);
    }
    eventsDetailView.showEventDetailPrivacy(privacy);
    eventsDetailView.showEventDetailMessagesNumber(String.valueOf(event.getMessagesNumber()));
    eventsDetailView.showEventDetailDistance(String.valueOf(event.getDistanceKm()));
  }
}
