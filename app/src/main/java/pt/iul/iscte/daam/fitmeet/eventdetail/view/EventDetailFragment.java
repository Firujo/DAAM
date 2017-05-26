package pt.iul.iscte.daam.fitmeet.eventdetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.data.Event;
import pt.iul.iscte.daam.fitmeet.data.EventRepositories;
import pt.iul.iscte.daam.fitmeet.data.EventsRepository;
import pt.iul.iscte.daam.fitmeet.data.EventsServiceApiImplementation;
import pt.iul.iscte.daam.fitmeet.eventdetail.presenter.EventDetailContract;
import pt.iul.iscte.daam.fitmeet.eventdetail.presenter.EventDetailPresenter;

/**
 * Created by Utilizador on 5/21/2017.
 */

public class EventDetailFragment extends Fragment implements EventDetailContract.View {
  public static final String EVENT_ID = "EVENT_ID";
  private EventDetailPresenter presenter;
  private String eventId;
  private ImageView featureGraphic;
  private TextView eventTitle;
  private TextView numberOfRunners;
  private TextView distanceKm;
  private TextView messagesNumber;
  private Button joinButton;
  private TextView location;
  private TextView privacy;
  private DatabaseReference mEventReference;

  public static Fragment newInstance(String eventId) {
    Bundle arguments = new Bundle();
    arguments.putString(EVENT_ID, eventId);
    EventDetailFragment fragment = new EventDetailFragment();
    fragment.setArguments(arguments);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      loadExtras(getArguments());
    }
    EventsRepository eventsRepository =
        EventRepositories.getInMemoryRepoInstance(new EventsServiceApiImplementation());
    presenter = new EventDetailPresenter(eventId, this, eventsRepository);
    // Initialize Database
    mEventReference = FirebaseDatabase.getInstance().getReference().child("Events").child(eventId);
  }

  @Override public void onStart() {
    super.onStart();

    // Add value event listener to the post
    // [START post_value_event_listener]
    ValueEventListener eventListener = new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        // Get Post object and use the values to update the UI
        Event event = dataSnapshot.getValue(Event.class);
        // [START_EXCLUDE]
        Picasso.with(getContext()).load(event.getImageUrl()).fit().into(featureGraphic, null);
        eventTitle.setText(event.getTitle());
        location.setText(event.getLocation());
        privacy.setText(event.getPrivacy());
        numberOfRunners.setText(String.valueOf(event.getNumberOfAttendees()));
        distanceKm.setText(String.valueOf(event.getDistanceKm()));
        // [END_EXCLUDE]
      }

      @Override public void onCancelled(DatabaseError databaseError) {
        // Getting Post failed, log a message
        Log.w(this.getClass().getCanonicalName(), "loadPost:onCancelled",
            databaseError.toException());
        // [START_EXCLUDE]
        Toast.makeText(getContext(), "Failed to load post.", Toast.LENGTH_SHORT).show();
        // [END_EXCLUDE]
      }
    };
    mEventReference.addValueEventListener(eventListener);
    // [END post_value_event_listener]
  }

  private void loadExtras(Bundle arguments) {
    eventId = arguments.getString(EVENT_ID);
  }

  @Override public void onResume() {
    super.onResume();
    presenter.onStart();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
    bindViews(view);
    setupViews();
    return view;
  }

  private void bindViews(View view) {
    featureGraphic = (ImageView) view.findViewById(R.id.event_detail_feature_graphic);
    eventTitle = (TextView) view.findViewById(R.id.event_detail_title);
    location = (TextView) view.findViewById(R.id.event_detail_location);
    privacy = (TextView) view.findViewById(R.id.event_detail_privacy);
    numberOfRunners = (TextView) view.findViewById(R.id.event_detail_number_of_runners);
    distanceKm = (TextView) view.findViewById(R.id.event_detail_distance_km);
    messagesNumber = (TextView) view.findViewById(R.id.event_detail_messages_number);
    joinButton = (Button) view.findViewById(R.id.event_detail_join_button);
  }

  private void setupViews() {
    joinButton.setOnClickListener(click -> presenter.joinEventClicked());
  }

  @Override public void showEventJoinMessage() {
    Toast.makeText(getContext(), "EVENT JOINED", Toast.LENGTH_SHORT).show();
  }

  @Override public void showEventJoinErrorMessage() {
    Toast.makeText(getContext(), "ERROR JOINING EVENT", Toast.LENGTH_SHORT).show();
  }

  @Override public void showEventFullMessage() {
    Toast.makeText(getContext(), "EVENT IS FULL", Toast.LENGTH_SHORT).show();
  }

  @Override public void showEventLeftMessage() {
    Toast.makeText(getContext(), "LEFT EVENT", Toast.LENGTH_SHORT).show();
  }

  @Override public void setLoadingIndicator(boolean active) {
    if (active) {
      eventTitle.setText(R.string.loading);
    }
  }

  @Override public void hideEventDetailTitle() {
    eventTitle.setVisibility(View.GONE);
  }

  @Override public void showEventDetailTitle(String title) {
    eventTitle.setText(title);
  }

  @Override public void hideEventDetailLocation() {
    location.setVisibility(View.INVISIBLE);
  }

  @Override public void showEventDetailLocation(String location) {
    this.location.setText(location);
  }

  @Override public void showEventDetailNumberOfRunners(String numberOfRunners) {
    this.numberOfRunners.setText(numberOfRunners);
  }

  @Override public void showEventDetailFeatureGraphic(String featureGraphicUrl) {
    Picasso.with(getContext()).load(featureGraphicUrl).fit().into(this.featureGraphic, null);
  }

  @Override public void showEventDetailDistance(String distanceKm) {
    this.distanceKm.setText(getString(R.string.event_detail_distance_km, distanceKm));
  }

  @Override public void showEventDetailMessagesNumber(String messagesNumber) {
    this.messagesNumber.setText(messagesNumber);
  }

  @Override public void showEventDetailPrivacy(String privacy) {
    this.privacy.setText(privacy);
  }
}
