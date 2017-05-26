package pt.iul.iscte.daam.fitmeet.events;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.data.Event;

/**
 * Created by Utilizador on 5/26/2017.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
  public TextView title;
  public ImageView userAvatar;
  public TextView userName;
  public TextView description;
  public TextView numberOfAttendees;
  public TextView location;
  public TextView timestamp;
  private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

  public ViewHolder(View itemView) {
    super(itemView);
    title = (TextView) itemView.findViewById(R.id.event_detail_title);
    userAvatar = (ImageView) itemView.findViewById(R.id.user_avatar);
    userName = (TextView) itemView.findViewById(R.id.user_name);
    numberOfAttendees = (TextView) itemView.findViewById(R.id.number_of_likes);
    location = (TextView) itemView.findViewById(R.id.event_location);
    timestamp = (TextView) itemView.findViewById(R.id.timestamp);
    description = (TextView) itemView.findViewById(R.id.event_detail_description);
  }

  public void bindToPost(Event event) {
    title.setText(event.getTitle());
    description.setText(event.getDescription());
    location.setText(event.getLocation());
    numberOfAttendees.setText((String.valueOf(event.getNumberOfAttendees())));
    Picasso.with(userAvatar.getContext())
        .load(event.getOwner().getAvatarUrl())
        .fit()
        .centerInside()
        .into(userAvatar, null);
    userName.setText(event.getOwner().getName());
    timestamp.setText(dateFormat.format(event.getDate()));
  }
}
