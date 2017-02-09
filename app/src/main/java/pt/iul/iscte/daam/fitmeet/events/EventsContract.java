package pt.iul.iscte.daam.fitmeet.events;

import android.support.annotation.NonNull;
import java.util.List;
import pt.iul.iscte.daam.fitmeet.data.Event;

/**
 * Created by jdandrade on 09/02/2017.
 */

public interface EventsContract {
  interface View {

    void showEvents(List<Event> events);

    void showAddEvent();

    void showEventDetails(long id);
  }

  interface UserActionsListener {

    void loadEvents();

    void addNewEvent();

    void openEventDetails(@NonNull Event clickedEvent);
  }
}
