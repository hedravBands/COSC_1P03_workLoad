package A5;

import BasicIO.*;                // for IO classes
import static java.lang.Math.*;  // for math constants and functions

/** This class is a program to open input data files, sort data and write to output data file
  * @author Heduin R. B. de Morais (Brock_ID 6967483, Campus_ID hr19ut, Lab 12) 
  * @version 1.0 (Apr. 2021)
  * new concepts: Grid-field, Comparator, Sorting, Subsorting.
*/

public class Sorting { 
  
 
  //private ASCIIOutputFile output;//to write to a new .txt file

  public  Sorting ()  { //throws InterruptedException {
    ASCIIDataFile input;//to read from a .txt file 
    ASCIIOutputFile output; //to write to a .txt file
    input = new ASCIIDataFile("input.txt");
    output = new ASCIIOutputFile("output.txt");
    
    int nCases = input.readInt(); //number of cases
    System.out.println(nCases);
    input.nextLine(); // burn line
 
   int aw[] = new int[nCases];
   int ah[] = new int[nCases];
   int ac[] = new int[nCases];
    for (int i = 0; i < nCases; ++i){
      aw[i] = input.readInt();
      ah[i] = input.readInt();
      ac[i] = (int)input.readC();
      for (int j = 0; j < ah[i]; ++j){
        input.nextLine();
      }
      input.readC(); //burn line    
      System.out.println(aw[i] + " " + ah[i] + " " + (char)ac[i]);
    }//for input
    input.close();
    
    output.writeInt(nCases);
    output.newLine();
    output.newLine();
    for (int i = 0; i < nCases; ++i){
      output.writeInt(aw[i]);
      output.writeInt(ah[i]);
      output.newLine();
      for (int m = 0; m < ah[i]; ++m){
        for(int n = 0; n < aw[i]; ++n){
          output.writeC((char)ac[i]);
        }// for n(width output)
        output.newLine();
      } //for m(heigt output)    
      output.newLine();
    } // for nCases output
    
    output.close();
    
    
//    for (;;){
//      // READ INT FROM FILE
//      myLittleInt = input.readInt(); 
//      if ( input.isEOF() ) { break;}
//    
//    // SEARCH THIS INT IN CODEBOOK
//     //iterate all codeBook with 128 Linked Lists
//      for (int k = 0; k < 128; k++) {
//        foundIt = false;
//        foundIt = SearchList(CodeBook[k], myLittleInt); 
//        //white the index to file
//        if (foundIt) {
//          output.writeC( (char) k );
//          break; 
//        }
//      }
//    }    
//    input.close();
//    output.close();
//  
  };  // constructor
  
  
//  // This method returns a random number from 0 to maxNumber
//  //@param maxNumber int number of possibilities
//  //@return int from 0 to (max - 1), in a  total of (maxNumber) possibities
//  private int FetchRandomNumber (int maxNumber) {
//    return (int)(Math.random()*100000) % maxNumber;
//  }
//  
//  
//  
//  // This method adds a Linked List to an output file, node by node until node.next is null)
//  //@param node head of a linked list
//  //@param serial int index of that list
//  private void FileList (Node node, int serial){ 
//     //output.writeString("List #" + serial + " with " + node.item + " items: [");
//     while (node.next != null){
//       node = node.next;
//       output.writeInt(node.item);
//     }
//    //output.writeString("]");
//    output.newLine();
//  }
//  
  
  
// main function

public static void main ( String[] args ) { Sorting s = new Sorting(); };

} // Sorting
    

 