package pt.iul.iscte.daam.fitmeet.events;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pt.iul.iscte.daam.fitmeet.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class EventsFragment extends Fragment {

  public EventsFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }
}
