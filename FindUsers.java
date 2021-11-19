/*Gabriel Dudley-Berrios
CSCI 241: Assignment 4
*/
import java.util.*;
import java.lang.*;


public class FindUsers{
  public static ArrayList<String[]> fileLines = new ArrayList<String[]>();
  public static String sourceName;
  public static String arg2;
  public static ArrayList<String> names = new ArrayList<String>();

  public static void main(String[] args){
    sourceName = args[1];
    arg2 = args[2];
  //create instance of a4_Functions
    a4_Functions functions = new a4_Functions();
  //convert file to 2D string array
    fileLines = functions.fileToArray(args[0], fileLines);
  //  functions.displayArrayList(fileLines);

  //create HashMaps to map names to index value
    for(String[] line : fileLines){
      functions.toHash(line[0]);
    }

  //create a graph using
    Graph connections = functions.a4Graph(fileLines);
  //  functions.printWeightMatrix(connections.adjMatrix);

  /*use dijkstra shortest-path algorithm to find shortest path from source vertex to
  all other vertices
  */
    int source = functions.nameToIndex.get(sourceName);
    functions.dijkstraSP(connections, source);

  /*checking whether to find distance from source vertex to another vertex, or find all
  vertices within a range of the source vertex
  */
    if(functions.nameToIndex.containsKey(arg2)){
      int vertID = functions.nameToIndex.get(arg2);
      int distance = functions.vertToDist.get(vertID);
      System.out.println(sourceName + " -> " + arg2 + " : " + distance);
    }
    else{
      String option = arg2.substring(0,2);
      int distVal = Integer.parseInt(arg2.substring(2));
        if(option.equals("lt")){
          for(Vertex vert : connections.sPath){
            if(distVal > vert.dist){
              String name = functions.indexToName.get(vert.id);
              if(!name.equals(sourceName)){
                names.add(name);
              }
            }
          }

          System.out.print(sourceName + " less than " + distVal + " : ");
          if(names.isEmpty()){
            System.out.println("none");
          }
          else{
            System.out.println(names);
          }
        }
        else if(option.equals("gt")){
          for(Vertex vert : connections.sPath){
            if(distVal < vert.dist){
              String name = functions.indexToName.get(vert.id);
              names.add(name);
            }
          }
          System.out.print(sourceName + " greater than " + distVal + " : ");
          if(names.isEmpty()){
            System.out.println("none");
          }
          else{
            System.out.println(names);
          }
        }
        else if(option.equals("eq")){
          for(Vertex vert : connections.sPath){
            if(distVal == vert.dist){
              String name = functions.indexToName.get(vert.id);
              names.add(name);
            }
          }
          System.out.print(sourceName + " equal to " + distVal + " : ");
          if(names.isEmpty()){
            System.out.println("none");
          }
          else{
            System.out.println(names);
          }
        }
      }

  }
}
