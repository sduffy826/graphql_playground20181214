package graphql_playground_20181214;

public class Cat {
  private String id;
  private String name;
  private String color;
  
  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getColor() {
    return color;
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setColor(String color) {
    this.color = color;
  }
  public Cat(String id, String name, String color) {
    super();
    this.id = id;
    this.name = name;
    this.color = color;
  }
  @Override
  public String toString() {
    return "Cat [id=" + id + ", name=" + name + ", color=" + color + "]";
  }

}
