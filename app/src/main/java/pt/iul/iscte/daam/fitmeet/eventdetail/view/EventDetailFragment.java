package pt.iul.iscte.daam.fitmeet.eventdetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.eventdetail.presenter.EventDetailContract;
import pt.iul.iscte.daam.fitmeet.eventdetail.presenter.EventDetailPresenter;

/**
 * Created by Utilizador on 5/21/2017.
 */

public class EventDetailFragment extends Fragment implements EventDetailContract.View {
  public static final String EVENT_ID = "EVENT_ID";
  private EventDetailPresenter eventDetailPresenter;
  private String eventId;

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
    eventDetailPresenter = new EventDetailPresenter(this);
  }

  private void loadExtras(Bundle arguments) {
    eventId = arguments.getString(EVENT_ID);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
    setupViews();
    return view;
  }

  private void setupViews() {
    //setup click listeners and whatnots
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
}
