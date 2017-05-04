package pt.iul.iscte.daam.fitmeet.newevent;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface NewEventContract {

  interface View {

    void showNewEventError();
  }

  interface UserActionsListener {

    void saveEvent(String title, String description);
  }
}
