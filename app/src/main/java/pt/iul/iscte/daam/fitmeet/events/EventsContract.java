package pt.iul.iscte.daam.fitmeet.events;

import java.util.List;

/**
 * Created by jdandrade on 09/02/2017.
 */

public interface EventsContract {
  interface View {

    void showEvents(List<?> events);

    void showAddEvent();

    void showEventDetails();
  }

  interface UserActionsListener {

    void loadEvents();

    void addNewEvent();

    void openEventDetails();
  }
}
