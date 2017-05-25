package pt.iul.iscte.daam.fitmeet.eventdetail.presenter;

import pt.iul.iscte.daam.fitmeet.view.Presenter;

/**
 * Created by Utilizador on 5/21/2017.
 */

public interface EventDetailContract {
  interface View {
    void showEventJoinMessage();

    void showEventJoinErrorMessage();

    void showEventFullMessage();

    void showEventLeftMessage();

    void setLoadingIndicator(boolean showProgress);

    void hideEventDetailTitle();

    void showEventDetailTitle(String title);

    void hideEventDetailLocation();

    void showEventDetailLocation(String location);

    void showEventDetailNumberOfRunners(String numberOfRunners);

    void showEventDetailFeatureGraphic(String featureGraphicUrl);
  }

  interface UserActionsListener extends Presenter{
    void joinEventClicked();
  }
}
