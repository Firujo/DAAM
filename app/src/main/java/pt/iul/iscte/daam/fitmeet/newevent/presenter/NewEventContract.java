package pt.iul.iscte.daam.fitmeet.newevent.presenter;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface NewEventContract {

  interface View {

    void showNewEventError();

    void showNewEventSuccess();
  }

  interface UserActionsListener {

    void saveEvent(String title, String description);
  }
}
