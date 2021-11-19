/* functions for Assignment 4
*/

/*SOURCES
HashMap- https://www.geeksforgeeks.org/java-util-hashmap-in-java/
Infinity- https://stackoverflow.com/questions/12952024/how-to-implement-infinity-in-java
*/

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;


public class a4_Functions{

  public HashMap<String, Integer> nameToIndex = new HashMap<>();
  public HashMap<Integer, String> indexToName = new HashMap<>();
  public HashMap<Integer, Integer> vertToDist = new HashMap<>();
  public int maxIndex = 0;

  public HeapMin vertices = new HeapMin();
  public int pathInsert;

//dijkstra shortest path algorithm implementation for Assignment 4
  public void dijkstraSP(Graph graph, int source){
    ArrayList<Vertex> vertHold = new ArrayList<Vertex>();
    initializeSingleSource(graph, source);
    pathInsert = 0;
    while(!vertices.isEmpty()){
      //vertices.display();

      Vertex u = vertices.removeMin();
      vertToDist.put(u.id, u.dist);

    //  System.out.println("id " + u.id + " dist " + u.dist);
      graph.sPath[pathInsert] = u;
      pathInsert++;

      while(!vertices.isEmpty()){
        Vertex v = vertices.removeMin();
        vertHold.add(v);
        if(graph.adjMatrix[u.id][v.id] != Integer.MAX_VALUE){
          relax(u, v, graph.adjMatrix);
        }
      }
      vertToDist.put(u.id, u.dist);
      for(Vertex q : vertHold){
        vertices.insert(q);
      }
      vertHold.clear();
    }
  }
//inititalize single-source for dijkstraSP
  public void initializeSingleSource(Graph graph, int source){
    for(Vertex v : graph.vertList){
       if(v.id == source){
         v.dist = 0;
       }
      vertices.insert(v);
    }
    graph.adjMatrix[source][source] = 0;
  }

//uses adjMatrix to update shortest path distance values for vertices
  public void relax(Vertex source, Vertex dest, int[][] edges){
    Vertex s = source;
    Vertex d = dest;
    if(d.dist > (s.dist + edges[s.id][d.id])){
      d.dist = (s.dist + edges[s.id][d.id]);
      d.parent = s;
    }
  }

//updates hashMaps to map names
  public void toHash(String name){
    if(!nameToIndex.containsKey(name)){
      nameToIndex.put(name, maxIndex);
      indexToName.put(maxIndex, name);
      maxIndex++;
    }
  }

//returns a graph given the specification of the users.txt file
  public Graph a4Graph(ArrayList<String[]> fileLines){
    Graph graph = new Graph(maxIndex);
    for(String[] line : fileLines){
      int source = nameToIndex.get(line[0]);
      int dest = nameToIndex.get(line[1]);
      int weight = Integer.parseInt(line[2]);
      graph.addEdge(source, dest, weight);
    }
    return graph;
  }

  //fileToArray converts lines from a .txt file into an ArrayList of type String[]
  public ArrayList fileToArray(String file, ArrayList<String[]> table){
  //  ArrayList<String[]> table = new ArrayList<String[]>();

    try{
      //function to find file for conversion
      File numFile = new File(file);
      //function to read file
      FileReader readFile = new FileReader(numFile);
      //function to convert lines from file into strings
      BufferedReader bufferedReader = new BufferedReader(readFile);
      String line;

      int i = 0;
      while((line = bufferedReader.readLine()) != null){
        String[] row = line.split(" ");
        table.add(row);
        i++;
        }
    }
    catch(IOException e){
      e.printStackTrace();
    }
    return table;
  }

//prints arrayList of string[] for testing and debugging
  public void displayArrayList(ArrayList<String[]> arr){
    for(int i = 0; i < arr.size(); i++){
      String[] line = arr.get(i);
      String print = Arrays.toString(line);
      System.out.println(print);
    }
  }

//prints int[][] matrix for testing and debugging
  public void printWeightMatrix(int[][] mtx){
    for(int[] i : mtx){
      for(int j : i){
        if(j == Integer.MAX_VALUE){
          System.out.print("©© ");
        }
        else
          System.out.print(j + " ");
        }
      System.out.println();
    }
  }
}
