/*Graph constructor
SOURCES:
Dr.Shameem Ahmed, CS 241 graph lecture slides
*/
import java.util.*;

public class Graph{
  public Vertex vertList[];
  public int[][] adjMatrix;
  public int totalVerts;
  //public int nVerts = 0;
  public Vertex sPath[];
  public int currentVert;
  public int startToCurrent;

//constructor for graph
  public Graph(int totalVerts){
    vertList = new Vertex[totalVerts];
    adjMatrix = new int[totalVerts][totalVerts];
    for(int i = 0; i < adjMatrix.length; i++){
      vertList[i] = new Vertex(i , Integer.MAX_VALUE);
      for(int j = 0; j < adjMatrix[i].length; j ++){
        adjMatrix[i][j] = Integer.MAX_VALUE;
      }
    }
    sPath = new Vertex[totalVerts];
  }

//adds edge weights to adjMatrix
  public void addEdge(int source, int dest, int weight){
    adjMatrix[source][dest] = weight;
  }
}
