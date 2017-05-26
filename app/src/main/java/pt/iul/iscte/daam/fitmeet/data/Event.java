package pt.iul.iscte.daam.fitmeet.data;

import com.google.firebase.database.Exclude;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jdandrade on 09/02/2017.
 */

public class Event {
  private String location;
  private User owner;
  private String privacy;
  private int id;
  private String description;
  private String title;
  private Date date;
  private String imageUrl;
  private String difficulty;
  private int messagesNumber;
  private int distanceKm;
  private int numberOfAttendees;

  public Event() {
  }

  public Event(int id, String description, String title, Date date, String location,
      int numberOfAttendees, String imageUrl, String difficulty, User owner, int messagesNumber,
      int distanceKm, String privacy) {
    this.id = id;
    this.description = description;
    this.title = title;
    this.privacy = privacy;
    this.date = date;
    this.location = location;
    this.numberOfAttendees = numberOfAttendees;
    this.imageUrl = imageUrl;
    this.difficulty = difficulty;
    this.owner = owner;
    this.messagesNumber = messagesNumber;
    this.distanceKm = distanceKm;
  }

  // [START post_to_map]
  @Exclude public Map<String, Object> toMap() {
    HashMap<String, Object> result = new HashMap<>();
    result.put("uid", id);
    result.put("title", title);
    result.put("description", description);
    result.put("imageUrl", imageUrl);
    result.put("numberOfAttendees", numberOfAttendees);
    result.put("distanceKm", distanceKm);
    result.put("owner", owner);
    result.put("privacy", privacy);
    result.put("date", date);
    result.put("difficulty", difficulty);
    result.put("location", location);
    return result;
  }
  // [END post_to_map]

  public int getMessagesNumber() {
    return messagesNumber;
  }

  public void setMessagesNumber(int messagesNumber) {
    this.messagesNumber = messagesNumber;
  }

  public int getDistanceKm() {
    return distanceKm;
  }

  public void setDistanceKm(int distanceKm) {
    this.distanceKm = distanceKm;
  }

  public String getPrivacy() {
    return privacy;
  }

  public void setPrivacy(String privacy) {
    this.privacy = privacy;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getNumberOfAttendees() {
    return numberOfAttendees;
  }

  public void setNumberOfAttendees(int numberOfAttendees) {
    this.numberOfAttendees = numberOfAttendees;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
