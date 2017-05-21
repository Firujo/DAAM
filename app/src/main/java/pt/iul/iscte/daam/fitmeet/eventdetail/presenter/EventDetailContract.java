package pt.iul.iscte.daam.fitmeet.eventdetail.presenter;

/**
 * Created by Utilizador on 5/21/2017.
 */

public interface EventDetailContract {
  interface View {
    void showEventJoinMessage();

    void showEventJoinErrorMessage();

    void showEventFullMessage();

    void showEventLeftMessage();
  }

  interface UserActionsListener {
    void joinEventClicked();
  }
}
