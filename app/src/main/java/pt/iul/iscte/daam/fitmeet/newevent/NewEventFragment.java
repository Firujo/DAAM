package pt.iul.iscte.daam.fitmeet.newevent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pt.iul.iscte.daam.fitmeet.R;

/**
 * Main UI for the new event screen. Users can enter event details.
 */
public class NewEventFragment extends Fragment implements NewEventContract.View {

  private NewEventContract.UserActionsListener actionsListener;

  private TextView name;
  private TextView description;

  public NewEventFragment() {
  }

  public static NewEventFragment newInstance() {
    return new NewEventFragment();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_newevent, container, false);
    //name = (TextView) root.findViewById(R.id.new_event_title);

    setHasOptionsMenu(true);
    setRetainInstance(true);
    return root;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    actionsListener = new NewEventPresenter(this);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.fragment_newevent_options_menu_actions, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.placeholder:
        // TODO: 04/05/2017 if necessary new menu events here, maybe save?
        return true;
    }
    return false;
  }

  @Override public void showNewEventError() {
    // TODO: 04/05/2017 error
  }
}
