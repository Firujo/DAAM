package pt.iul.iscte.daam.fitmeet.data;

/**
 * Created by jdandrade on 09/02/2017.
 */
public class User {
  private final long id;
  private final String name;
  private final String email;

  public User(long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
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
}
