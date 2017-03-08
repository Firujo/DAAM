package pt.iul.iscte.daam.fitmeet.data;

import java.util.Date;

/**
 * Created by jdandrade on 09/02/2017.
 */

public class Event {
  private final long id;
  private final String description;
  private final String title;
  private final Date date;
  private final String location;
  private final String imageUrl;
  private final Enum<Difficulty> difficulty;
  private final User owner;
  private int numberOfLikes;

  public Event(long id, String description, String title, Date date, String location,
      int numberOfLikes, String imageUrl, Enum<Difficulty> difficulty, User owner) {
    this.id = id;
    this.description = description;
    this.title = title;
    this.date = date;
    this.location = location;
    this.numberOfLikes = numberOfLikes;
    this.imageUrl = imageUrl;
    this.difficulty = difficulty;
    this.owner = owner;
  }

  public long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public Date getDate() {
    return date;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public Enum<Difficulty> getDifficulty() {
    return difficulty;
  }

  public String getTitle() {
    return title;
  }

  public User getOwner() {
    return owner;
  }

  public String getLocation() {
    return location;
  }

  public int getNumberOfLikes() {
    return numberOfLikes;
  }
}
