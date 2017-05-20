package pt.iul.iscte.daam.fitmeet.newevent.view;

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
import pt.iul.iscte.daam.fitmeet.newevent.model.NewEventDataValidator;
import pt.iul.iscte.daam.fitmeet.newevent.presenter.NewEventContract;
import pt.iul.iscte.daam.fitmeet.newevent.presenter.NewEventPresenter;

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
    actionsListener = new NewEventPresenter(this, new NewEventDataValidator());
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.fragment_newevent_options_menu_actions, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.save:
        actionsListener.saveEvent(title.getText().toString(), description.getText().toString());
        return true;
    }
    return false;
  }

  @Override public void showNewEventError() {
    if (getView() != null) {
      Snackbar.make(getView(), getString(R.string.create_event_gather_error),
          Snackbar.LENGTH_LONG).show();
    }
  }

  @Override public void showNewEventSuccess() {
    if (getView() != null) {
      Snackbar.make(getView(), getString(R.string.create_event_gather_created),
          Snackbar.LENGTH_LONG).show();
    }
  }
}
