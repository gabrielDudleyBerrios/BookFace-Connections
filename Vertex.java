// Vertex class from lecture slides

public class Vertex{
  public int id;
  public int dist;
  public Vertex parent;

//constructor for Vertex class
  public Vertex(int id, int dist){
    this.id = id;
    this.dist = dist;
  }
//constructor for no int values
  public Vertex(){}
}
