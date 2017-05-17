package pt.iul.iscte.daam.fitmeet.newevent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import pt.iul.iscte.daam.fitmeet.R;

/**
 * Main UI for the new event screen. Users can enter event details.
 */
public class NewEventFragment extends Fragment implements NewEventContract.View {

  private NewEventContract.UserActionsListener actionsListener;

  private TextView title;
  private TextView description;

  public NewEventFragment() {
  }

  public static NewEventFragment newInstance() {
    return new NewEventFragment();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_newevent, container, false);
    title = (EditText) view.findViewById(R.id.new_event_title);
    description = (EditText) view.findViewById(R.id.new_event_description);

    setHasOptionsMenu(true);
    setRetainInstance(true);
    return view;
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
      case R.id.save:
        Snackbar.make(getView(), getString(R.string.create_event_gather_created),
            Snackbar.LENGTH_LONG).show();
        return true;
    }
    return false;
  }

  @Override public void showNewEventError() {
    // TODO: 04/05/2017 error
  }
}
