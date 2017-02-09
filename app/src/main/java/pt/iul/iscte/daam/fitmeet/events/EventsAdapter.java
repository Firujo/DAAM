package pt.iul.iscte.daam.fitmeet.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.data.Event;

/**
 * Created by jdandrade on 09/02/2017.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
  private final EventsFragment.EventItemListener itemListener;
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
  }

  @Override public int getItemCount() {
    return 0;
  }

  public Event getItem(int position) {
    return mEvents.get(position);
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title;

    public TextView description;
    private EventsFragment.EventItemListener mItemListener;

    public ViewHolder(View itemView, EventsFragment.EventItemListener listener) {
      super(itemView);
      mItemListener = listener;
      title = (TextView) itemView.findViewById(R.id.event_detail_title);
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
