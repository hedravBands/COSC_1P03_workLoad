package A5;

import BasicIO.*;            // for IO classes
import java.util.Comparator; // for the 3 implementations 

import A5.BrickWidthComparator;

/** This class is a program that opens a input data file, 
  * sort data by Content, Height, and Width, in this order. Then, write the sorted data
  * to output an ouput data file in the same folder with "Result" appended in the oririnal filename.
  * @author Heduin R. B. de Morais (Brock_ID 6967483, Campus_ID hr19ut, Lab 12) 
  * @version 1.0 (Apr. 2021)
  * New concepts: Gridfields, Comparator, Sorting, Subsorting.
  * @param wall Brick[] containing a list of triplets(w, h, c) as Brick to be stored.
  * @param filename String added to sequentially process all 10 input gridfields w/o user intervention
  * Please read comments above main function.
*/

public class Sorting { 
  //general array per input file
  public Brick[] wall;
  public String filename;
   
  //constructor
  public  Sorting (int counter)  { 
    //format filename
    filename = "gridfields" + String.format("%02d", counter);
    //open input file
    ASCIIDataFile input;//to read from a .txt file 
    input = new ASCIIDataFile("./gridfields/"+filename+".txt");
    
    //read number of cases
    int nCases = input.readInt(); 
    //burn blank line
    input.nextLine(); 
    //build a Wall with nCases Bricks
    wall = new Brick[nCases];
    //receive the input data file
    for (int i = 0; i < nCases; i++){
      wall[i] = new Brick(input.readInt(), input.readInt(), input.readC());
      for (int j = 0; j < wall[i].getH(); j++){ 
        // skip repeated lines
        input.nextLine();
      }
      //burn blank line
      input.readC();
      //debug only
      //wall[i].printMe();
    }//for 
    input.close();

    //start sorting accordinly to Assign5 requests    
    wall = InsertionSort(wall, new BrickContentComparator());
    wall = InsertionSort(wall, new BrickHeightComparator());
    wall = InsertionSort(wall, new BrickWidthComparator());

    //write wall fully sorted to file
    writeOutputFile(wall, nCases);
   
  };  // constructor
  
/** 
 * This method sorts List of Bricks accordingly to the chosen comparator
 * @param block List of Bricks to be sorted
 * @param comparator Comparator implementation by a given key (w, h or c)
 * Returns List of Bricks after sorting by Insert Sorting algorithm
 */
   private Brick[] InsertionSort(Brick[] block, Comparator<Brick>comparator){
     Brick key;
       for(int i = 1; i < block.length; i++){
         key = block[i];
         int j = i - 1;
         //move elements
         while(j >= 0 && (comparator.compare(block[j], key) > 0 )) {
            block[j+1] = block[j];         
            j--;
         }
         block[j+1] = key;
       }
       return block;
     }
 
   /**
    * This method writes a List of Bricks to an output file after being fully sorted.
    * It chooses the output filename bases on the input filename by appending "Result" w/o user intervention.
    * @param block List of Bricks fully sorted
    * @param nCases int with a number of Bricks to be written to file.
    */  
  public void writeOutputFile(Brick[] block, int nCases){
    ASCIIOutputFile output =  new ASCIIOutputFile("./gridfields/"+filename+"Result.txt");
    //initial format
    output.writeInt(nCases);
    output.newLine();
    output.newLine();
    //writes Width, Heith, then Content
    for (int i = 0; i < nCases; ++i){
      output.writeInt(block[i].getW());
      output.writeInt(block[i].getH());
      output.newLine();
      //Height of Content
      for (int m = 0; m < block[i].getH(); m++){
        //Width of Content
        for(int n = 0; n < block[i].getW(); n++){
          output.writeC(block[i].getC());
        }// for n(width output)
        output.newLine();
      } //for m(heigth output)    
      output.newLine();
    } // for nCases output
     output.close();
  }

// main function
/**
 * This is the Main function, processing exactly 10 input files for all cases w/o user intervention.
 * If T.A./Marker needs to test a particular file, please do as follows: 
 * Add the file as "./gridfields/gridfields11.txt ,  the 11th case.
 * Please change below:  i <= 10  to  i<= 11  .
 * That's it. It should appear as "./gridfields/gridfields11Result.txt"
 */
  public static void main ( String[] args ) { 
    for (int i = 1; i<=10; i++) { Sorting s = new Sorting(i); };
    System.out.println("Yay! All files processed!");
    System.out.println("Check out the folder \"gridfields\".");
    System.out.println("Best regards, HR19UT.");
  }

} // Sorting