package pt.iul.iscte.daam.fitmeet.events;

import android.net.Uri;
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

    void setProgressIndicator(boolean showProgress);

    void setLoginInformation(String email);

    void setDrawerAvatar(Uri uri);
  }

  interface UserActionsListener {

    void loadEvents(boolean bypassCache);

    void addNewEvent();

    void openEventDetails(@NonNull Event clickedEvent);

    void onResume();
  }
}
