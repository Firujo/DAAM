package pt.iul.iscte.daam.fitmeet.data;

import java.util.Date;

/**
 * Created by jdandrade on 09/02/2017.
 */

public class Event {
  private final long id;
  private final String location;
  private final User owner;
  private String privacy;
  private String description;
  private String title;
  private Date date;
  private String imageUrl;
  private Enum<Difficulty> difficulty;
  private int messagesNumber;
  private int distanceKm;
  private int numberOfAttendees;

  public Event(long id, String description, String title, Date date, String location,
      int numberOfAttendees, String imageUrl, Enum<Difficulty> difficulty, User owner,
      int messagesNumber, int distanceKm, String privacy) {
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

  public long getId() {
    return id;
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

  public Enum<Difficulty> getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Enum<Difficulty> difficulty) {
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

  public String getLocation() {
    return location;
  }

  public int getNumberOfAttendees() {
    return numberOfAttendees;
  }

  public void setNumberOfAttendees(int numberOfAttendees) {
    this.numberOfAttendees = numberOfAttendees;
  }
}
