package A2;

import BasicIO.*;                // for IO classes
import static java.lang.Math.*;  // for math constants and functions

/** This class is a program to encode and decode a message read from file by using a Code Book
  * @author Heduin R. B. de Morais (Brock_ID 6967483, Campus_ID hr19ut, Lab 12) 
  * @version 1.0 (Feb. 2021)
  * new concepts: Linked Lists, Encoding, Decoding, Code Book
*/

public class Crypto { 
  
  private ASCIIDataFile input;//to read from a .txt file
  private ASCIIOutputFile output;//to write to a new .txt file

  public  Crypto ()  { //throws InterruptedException {
    Node[] CodeBook = new Node[128];// 0-127, we have 128 elements, all 0 by default
    System.out.println("CodeBook Lenght: " + CodeBook.length);
    int randomHead; // picks random Heads in the codeBook by using method FetchRandom(128)
    int myLittleChar; // each (int)char from input Message.txt
    int randomNodeItem; // each int for output Encrypted.txt
    int myLittleInt; // each int from input Encrypted.txt
    Boolean foundIt; // used to get the index in Decode process
    
    
    //initialize codeBook with Heads with default constructor (0, null)
    for (int i = 0; i < 128; i++) {
      CodeBook[i] = new Node();
    } // for
    
    //populate codeBook with 2000 random  2000 random characters in the range 0 to 127
    for (int j = 1; j <= 2000; j++ ) {
      randomHead = FetchRandomNumber(128);
      AddNode(CodeBook[randomHead], j);
      CodeBook[randomHead].item++; //Head counts amount of nodes
    } // for
    
    // Print all codeBook with 128 Linked Lists
    output = new ASCIIOutputFile("CodeBook.txt"); 
   System.out.println("CodeBook will saved to file...");
    for (int k = 0; k < 128; k++) {
     //PrintList(CodeBook[k], k+1); // list k shows [1 to 128] but runs [0 to 127] for better UX
     FileList(CodeBook[k], k+1); // list k shows [1 to 128] but runs [0 to 127] for better UX
    }
     output.close();
     System.out.println("... CodeBook written to file!"); 
     
    // CodeBook ready, LET'S ENCODE
    input = new ASCIIDataFile("Message.txt");
    output = new ASCIIOutputFile("Encrypted.txt"); 
    
   
    for (;;){
      // READ CHAR FROM FILE
      myLittleChar = (int) input.readC();   // do NOT use readChar
      if ( input.isEOF() ) { break;}
      // ENCRYPT THAT CHAR
      randomNodeItem = FetchRandomNodeItem(CodeBook[myLittleChar]); // sends a Head
      //WRITE TO FILE    
      output.writeInt(randomNodeItem);
    }//for
    input.close();
    output.close();
    System.out.println(".\n.\n.\nEncrypted.txt created!");
    
    //Encoding READY, LET'S Decode
    input = new ASCIIDataFile("Encrypted.txt");
    output = new ASCIIOutputFile("Decrypted.txt"); 
    
    for (;;){
      // READ INT FROM FILE
      myLittleInt = input.readInt(); 
      if ( input.isEOF() ) { break;}
    
    // SEARCH THIS INT IN CODEBOOK
     //iterate all codeBook with 128 Linked Lists
      for (int k = 0; k < 128; k++) {
        foundIt = false;
        foundIt = SearchList(CodeBook[k], myLittleInt); 
        //white the index to file
        if (foundIt) {
          output.writeC( (char) k );
          break; 
        }
      }
    }    
    input.close();
    output.close();
    System.out.println(".\n.\n.\nDecrypted.txt created!\n\nAll Done!\nYay!");

  };  // constructor
  
  
  // This method returns a random number from 0 to maxNumber
  //@param maxNumber int number of possibilities
  //@return int from 0 to (max - 1), in a  total of (maxNumber) possibities
  private int FetchRandomNumber (int maxNumber) {
    return (int)(Math.random()*100000) % maxNumber;
  }
  
  // This method adds a Node at the end of a Linked List
  //@param node head of a linked list
  //@param value int to be added as a new tail node
  private void AddNode(Node node, int value){
    while (node.next != null) {
      node = node.next;
    }
    node.next = new Node(value, null); //new tail added
  }
  
  // This method prints a Linked List, node by node until node.next is null)
  //@param node head of a linked list
  //@param serial int index of that list
  private void PrintList (Node node, int serial){ 
     System.out.print("List #" + serial + " with " + node.item + "\titems: [ ");
     while (node.next != null){
       node = node.next;
       System.out.print(node.item);
       System.out.print("  "); // decoration
     }
    System.out.println("]"); // end of list
  }
  
  // This method adds a Linked List to an output file, node by node until node.next is null)
  //@param node head of a linked list
  //@param serial int index of that list
  private void FileList (Node node, int serial){ 
     //output.writeString("List #" + serial + " with " + node.item + " items: [");
     while (node.next != null){
       node = node.next;
       output.writeInt(node.item);
     }
    //output.writeString("]");
    output.newLine();
  }
  
  
  // This method prints a Linked List, node by node until node.next is null)
  private Boolean SearchList (Node node, int value){ 
    Boolean itIsAMatch = false;
     while (node.next != null){
       node = node.next;
       itIsAMatch = node.item == value;
       if (itIsAMatch) { break; }
     }
    return itIsAMatch;
  }
  
  //This method returns a random node.item given a linked list
  //@param node head of a linked list
  //@return node.item randomly chosen
  private int FetchRandomNodeItem (Node node) {
    int randomNodeIndex = FetchRandomNumber(node.item);  //returns [0, head.item -1] with head.item possiblilities
    for (int i = 0; i <= randomNodeIndex; i++){  // <= because we need to skip the HeadNode
      node = node.next;
    }
    return node.item;
  }
     
  
// main function

public static void main ( String[] args ) { Crypto c = new Crypto(); };

} // Crypto
    

 