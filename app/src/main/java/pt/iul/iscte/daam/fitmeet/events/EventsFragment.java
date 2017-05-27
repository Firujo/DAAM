package pt.iul.iscte.daam.fitmeet.events;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.util.List;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.utils.SharedPreferencesUtils;
import pt.iul.iscte.daam.fitmeet.account.LoginStatusManager;
import pt.iul.iscte.daam.fitmeet.data.Event;
import pt.iul.iscte.daam.fitmeet.data.EventRepositories;
import pt.iul.iscte.daam.fitmeet.data.EventsRepository;
import pt.iul.iscte.daam.fitmeet.data.EventsServiceApiImplementation;
import pt.iul.iscte.daam.fitmeet.eventdetail.view.EventDetailActivity;
import pt.iul.iscte.daam.fitmeet.newevent.view.NewEventActivity;

/**
 * Created by jdandrade on 09/02/2017.
 */
public class EventsFragment extends Fragment implements EventsContract.View {
  private EventsContract.UserActionsListener mActionsListener;
  private DatabaseReference mDatabase;
  private FirebaseRecyclerAdapter<Event, ViewHolder> mAdapter;
  private RecyclerView mRecycler;

  public EventsFragment() {

  }

  public static EventsFragment newInstance() {
    return new EventsFragment();
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    // Set up FirebaseRecyclerAdapter with the Query
    Query postsQuery = getQuery(mDatabase);
    mAdapter = new FirebaseRecyclerAdapter<Event, ViewHolder>(Event.class,
        R.layout.item_event, ViewHolder.class, postsQuery) {
      @Override protected void populateViewHolder(final ViewHolder viewHolder,
          final Event model, final int position) {
        final DatabaseReference postRef = getRef(position);

        // Set click listener for the whole post view
        final String eventId = postRef.getKey();
        viewHolder.itemView.setOnClickListener(v -> {
          // Launch EventDetailActivity
          Intent intent = new Intent(getActivity(), EventDetailActivity.class);
          intent.putExtra(EventDetailActivity.EXTRA_EVENT_ID, eventId);
          startActivity(intent);
        });

        // Bind Post to ViewHolder, setting OnClickListener for the star button
        viewHolder.bindToPost(model);
      }
    };
    mRecycler.setAdapter(mAdapter);
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventsRepository eventsRepository =
        EventRepositories.getInMemoryRepoInstance(new EventsServiceApiImplementation());
    mActionsListener = new EventsPresenter(eventsRepository, LoginStatusManager.getInstance(
        getContext().getSharedPreferences(SharedPreferencesUtils.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE)), this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);

    mRecycler = (RecyclerView) view.findViewById(R.id.events_list);
    mRecycler.setHasFixedSize(true);
    mDatabase = FirebaseDatabase.getInstance().getReference();
    int numColumns = getContext().getResources().getInteger(R.integer.num_events_columns);
    mRecycler.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

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
    //mListAdapter.refreshData(events);
  }

  @Override public void showAddEvent() {
    startActivity(new Intent(getActivity(), NewEventActivity.class));
  }

  @Override public void showEventDetails(long eventId) {
    Intent intent = new Intent(getContext(), EventDetailActivity.class);
    intent.putExtra(EventDetailActivity.EXTRA_EVENT_ID, eventId);
    startActivity(intent);
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

  @Override public void setLoginInformation(String email) {
    NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
    TextView textView =
        (TextView) navigationView.getHeaderView(0).findViewById(R.id.email_information);
    textView.setText(email);
  }

  @Override public void setDrawerAvatar(Uri uri) {
    NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
    ImageView imageView =
        (ImageView) navigationView.getHeaderView(0).findViewById(R.id.drawer_avatar);
    imageView.setImageURI(uri);
  }

  @Override public void onResume() {
    super.onResume();
    mActionsListener.onResume();
  }

  public Query getQuery(DatabaseReference databaseReference) {
    return databaseReference.child("Events");
  }
}
