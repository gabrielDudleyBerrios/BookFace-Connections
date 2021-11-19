//HeapMin build off of HeapMin from Assignment 3, modified for Assignment 4
import java.util.*;

public class HeapMin{

  private ArrayList<Vertex> minHeap;
  public int insertIndex;

//  Constructor of HeapMin. Initializes Arraylist and insertIndex
  public HeapMin(){
    minHeap = new ArrayList<Vertex>();
    insertIndex = 0;
  }

/*erase: deletes all elements in the minHeap and changes insertIndex to 0
*/
  public void erase(){
    minHeap.clear();
    insertIndex = 0;
  }

/*   isEmpty: This method checks to see if the HeapMin is empty
*/

  public boolean isEmpty(){
    if(insertIndex != 0){
      return false;
    }
    else{
      return true;
    }
  }

  /*   insert: This method inserts a new element to the min heap
      and organizes min heap for proper order
  */
  public void insert(Vertex element){
    minHeap.add(element);
    int i = insertIndex;
    Vertex child = minHeap.get(i);
    Vertex parent = minHeap.get((i-1)/2);
    while(child.dist < parent.dist && parent.dist > 0){
      swap(minHeap, i, ((i-1)/2) );
      i = (i-1)/2;
      child = minHeap.get(i);
      parent = minHeap.get((i-1)/2);
    }
    insertIndex ++;
  }
/*  extractMin: This method returns the root element of the min heap
    or -1 id the heap is empty
*/
  public int extractMin(){
    Vertex min = minHeap.get(0);
    if(insertIndex == 0){
      System.out.println("Heap is empty, cannot return a min-value");
      return -1;
    }
    else{ return minHeap.get(0).dist; }

  }
/*  removemin: This method removes and returns the root element of the
   min heap, and reorganize the heap accordingly to restore its min heap
   characteristics. If the min heap is empty, removeMin prints a statement
   to explai and returns a dummy element.
*/
  public Vertex removeMin(){
    if(insertIndex == 0){
      System.out.println("Heap is empty, cannot remove a min-value");
      Vertex dummy = new Vertex(0,0);
      return dummy;
    }
    else{
      Vertex temp = minHeap.remove(insertIndex-1);
      insertIndex --;
      if(insertIndex != 0){
        Vertex min = minHeap.remove(0);
        minHeap.add(0, temp);
        heapify(0);
        return min;
      }
      else{ return temp;}
    }
  }
/*   display: This method prints all specified elements in the min heap
    in the order of levels for testing (e.g., root element first, root’s
    two children second, root’s grandchildren third, etc.).
*/
  public void display(){
    System.out.print("Current heap is: ");
    int index = 0;
    int numLvlVals = 1;
    while( index < insertIndex ){
      int lvlVal = 1;
      while( lvlVal <= numLvlVals && index < insertIndex){
        Vertex vert = minHeap.get(index);
        System.out.print("id " + vert.id + ", dist " + vert.dist + "  ");
        index ++;
        lvlVal ++;
      }
      System.out.print(", ");
      numLvlVals = numLvlVals * 2;
    }
    System.out.println();
  }

  public void heapify(int i){
    while((2*i + 1) < insertIndex){
      int minChildIndex = minChild(i);
      if(minHeap.get(i).dist > minHeap.get(minChildIndex).dist){
        swap(minHeap, i, minChildIndex);
      }
      i = minChildIndex;
    }
  }

  public int minChild(int i){
    int cL = 2*i + 1;
    int cR = 2*i + 2;
    Vertex leftChild = minHeap.get(cL);
    if(cR == insertIndex){
      return cL;
    }
    else{
      Vertex rightChild = minHeap.get(cR);
      if( leftChild.dist < rightChild.dist ){
        return cL;
      }
      else{ return cR; }
    }
  }

  public void swap(ArrayList<Vertex> arr, int i1, int i2){
    Vertex temp1 = arr.get(i1);
    Vertex temp2 = arr.remove(i2);
    arr.add(i2, temp1);
    arr.add(i1, temp2);
    arr.remove(i1+1);
  }

}
