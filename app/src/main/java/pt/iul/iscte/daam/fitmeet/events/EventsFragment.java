package pt.iul.iscte.daam.fitmeet.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.data.Event;
import pt.iul.iscte.daam.fitmeet.data.EventRepositories;
import pt.iul.iscte.daam.fitmeet.data.EventsRepository;
import pt.iul.iscte.daam.fitmeet.data.EventsServiceApiImplementation;
import pt.iul.iscte.daam.fitmeet.newevent.view.NewEventActivity;

/**
 * Created by jdandrade on 09/02/2017.
 */
public class EventsFragment extends Fragment implements EventsContract.View {
  private EventsContract.UserActionsListener mActionsListener;
  EventItemListener mItemListener = new EventItemListener() {
    @Override public void onEventClick(Event clickedEvent) {
      mActionsListener.openEventDetails(clickedEvent);
    }
  };
  private EventsAdapter mListAdapter;

  public EventsFragment() {

  }

  public static EventsFragment newInstance() {
    return new EventsFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventsRepository eventsRepository =
        EventRepositories.getInMemoryRepoInstance(new EventsServiceApiImplementation());
    mActionsListener = new EventsPresenter(eventsRepository, this);
    mListAdapter = new EventsAdapter(new ArrayList<>(0), mItemListener);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.events_list);
    recyclerView.setAdapter(mListAdapter);

    int numColumns = getContext().getResources().getInteger(R.integer.num_events_columns);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

    FloatingActionButton fab =
        (FloatingActionButton) getActivity().findViewById(R.id.fab_add_event);
    fab.setOnClickListener(v -> mActionsListener.addNewEvent());

    // Pull-to-refresh
    SwipeRefreshLayout swipeRefreshLayout =
        (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
    swipeRefreshLayout.setColorSchemeColors(
        ContextCompat.getColor(getActivity(), R.color.colorPrimary),
        ContextCompat.getColor(getActivity(), R.color.colorAccent),
        ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
    swipeRefreshLayout.setOnRefreshListener(() -> mActionsListener.loadEvents(true));

    return view;
  }

  @Override public void showEvents(List<Event> events) {
    mListAdapter.refreshData(events);
  }

  @Override public void showAddEvent() {
    startActivity(new Intent(getActivity(), NewEventActivity.class));
  }

  @Override public void showEventDetails(long eventId) {
    //Intent intent = new Intent(getContext(), EventDetailActivity.class);
    //intent.putExtra(EventDetailActivity.EXTRA_EVENT_ID, eventId);
    //startActivity(intent);
  }

  @Override public void setProgressIndicator(final boolean showProgress) {
    if (getView() == null) {
      return;
    }
    final SwipeRefreshLayout swipeRefreshLayout =
        (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

    // Make sure setRefreshing() is called after the layout is done with everything else.
    swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(showProgress));
  }

  public interface EventItemListener {
    void onEventClick(Event clickedEvent);
  }
}
