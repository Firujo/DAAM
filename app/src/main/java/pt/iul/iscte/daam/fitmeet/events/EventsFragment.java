package pt.iul.iscte.daam.fitmeet.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import pt.iul.iscte.daam.fitmeet.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class EventsFragment extends Fragment implements EventsContract.View {
  private EventsContract.UserActionsListener mActionsListener;

  public EventsFragment() {
  }

  public static EventsFragment newInstance() {
    return new EventsFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActionsListener = new EventsPresenter(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.events_list);

    int numColumns = getContext().getResources().getInteger(R.integer.num_events_columns);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

    FloatingActionButton fab =
        (FloatingActionButton) getActivity().findViewById(R.id.fab_add_event);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mActionsListener.addNewEvent();
      }
    });

    // Pull-to-refresh
    SwipeRefreshLayout swipeRefreshLayout =
        (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
    swipeRefreshLayout.setColorSchemeColors(
        ContextCompat.getColor(getActivity(), R.color.colorPrimary),
        ContextCompat.getColor(getActivity(), R.color.colorAccent),
        ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        mActionsListener.loadEvents();
      }
    });

    return view;
  }

  @Override public void showEvents(List<?> events) {

  }

  @Override public void showAddEvent() {
    Snackbar.make(getView(), "TODO", Snackbar.LENGTH_LONG).setAction("Action", null).show();
  }

  @Override public void showEventDetails() {

  }
}
