package pt.iul.iscte.daam.fitmeet.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.data.Event;

/**
 * Created by jdandrade on 09/02/2017.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
  private final EventsFragment.EventItemListener itemListener;
  DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
  private List<Event> mEvents;

  public EventsAdapter(ArrayList<Event> events, EventsFragment.EventItemListener itemListener) {
    setList(events);
    this.itemListener = itemListener;
  }

  private void setList(List<Event> events) {
    this.mEvents = events;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);
    View eventView = inflater.inflate(R.layout.item_event, parent, false);

    return new ViewHolder(eventView, itemListener);
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int position) {
    Event event = mEvents.get(position);
    viewHolder.title.setText(event.getTitle());
    viewHolder.description.setText(event.getDescription());
    viewHolder.location.setText(event.getLocation());
    viewHolder.numberOfLikes.setText((String.valueOf(event.getNumberOfLikes())));
    Picasso.with(viewHolder.userAvatar.getContext())
        .load(event.getOwner().getAvatarUrl())
        .fit()
        .centerInside()
        .into(viewHolder.userAvatar, new Callback() {
          @Override public void onSuccess() {
            Log.d(this.getClass().getName(), "success");
          }

          @Override public void onError() {
            Log.d(this.getClass().getName(), "error");
          }
        });
    viewHolder.userName.setText(event.getOwner().getName());
    viewHolder.timestamp.setText(dateFormat.format(event.getDate()));
  }

  @Override public int getItemCount() {
    return mEvents.size();
  }

  public Event getItem(int position) {
    return mEvents.get(position);
  }

  public void refreshData(List<Event> events) {
    setList(events);
    notifyDataSetChanged();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title;
    public ImageView userAvatar;
    public TextView userName;
    public TextView description;
    public TextView numberOfLikes;
    public TextView location;
    public TextView timestamp;

    private EventsFragment.EventItemListener mItemListener;

    public ViewHolder(View itemView, EventsFragment.EventItemListener listener) {
      super(itemView);
      mItemListener = listener;
      title = (TextView) itemView.findViewById(R.id.event_detail_title);
      userAvatar = (ImageView) itemView.findViewById(R.id.user_avatar);
      userName = (TextView) itemView.findViewById(R.id.user_name);
      numberOfLikes = (TextView) itemView.findViewById(R.id.number_of_likes);
      location = (TextView) itemView.findViewById(R.id.event_location);
      timestamp = (TextView) itemView.findViewById(R.id.timestamp);
      description = (TextView) itemView.findViewById(R.id.event_detail_description);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      int position = getAdapterPosition();
      Event event = getItem(position);
      mItemListener.onEventClick(event);
    }
  }
}
