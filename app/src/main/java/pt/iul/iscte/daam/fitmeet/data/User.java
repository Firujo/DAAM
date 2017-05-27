package pt.iul.iscte.daam.fitmeet.data;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by jdandrade on 09/02/2017.
 */

@IgnoreExtraProperties public class User {
  private long id;
  private String name;
  private String email;
  private String avatarUrl;

  public User() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public User(long id, String name, String email, String avatarUrl) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.avatarUrl = avatarUrl;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }
}

