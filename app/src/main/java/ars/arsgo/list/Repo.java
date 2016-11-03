package ars.arsgo.list;

/**
 * @author Hannes Dorfmann
 */
public class Repo {

  long id;
  String name;
  String description;

  User owner;

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public User getOwner() {
    return owner;
  }
}
