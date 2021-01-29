package A1;

import static java.lang.Math.*;


/** This class is a program to executes Error Detection and Correction by analizing 
  * the parity of Rows and Columns of a Matrix. One new Row and one new Column is added for this purpose.
  * 
  * @author Heduin R. B. de Morais (Brock_ID 6967483, Campus_ID hr19ut, Lab 12) 
  * @version 1.0 (Jan. 2021)
  * new concepts: Error Detection and Correction, Parity Check, Matrices
  * 
  * @param sizeM int assigns the size of the square matrix for DATA.
*/

public class Parity {
  //Settings
  private double probGetOne = 0.4;
  private double probError = 0.012;
  private int sizeM;

  public Parity(int sizeM)  throws InterruptedException {
    
    int[][] txM = new int[sizeM + 1][sizeM + 1];
  
    System.out.println("\n\nGenerating Packet, Data Size: "+sizeM+"x"+sizeM+"...");
    //Pause for 2 seconds
    Thread.sleep(2000);
    
    //Generates a transmitterMatrix as TxM with 'size + 1' bi-dimension
    txM = populateMatrix(sizeM, probGetOne);
    
    // Print TxM
    System.out.println("Original Packet:");
    printMatrix(txM, sizeM);
    
    // Transmit TxM to a ReceiverMatrix as RxM with a probError chance of flipping each value.
    int[][] rxM = transmitMatrix(txM, sizeM, probError);
    //Pause for 2 seconds
    Thread.sleep(2000);
    
    // Print RxM
    System.out.println("Received Packet:");
    printMatrix(rxM, sizeM);
    
    // Evaluate RxM
    int[] response = evaluateRx(rxM, sizeM);
   
    // Print result
    printResult(response);
    
   };  // constructor Parity

    
  /* This method populates a Matrix with 0's and 1's considering a probability of getting one being informed.
   * This matrix is fully populated with only one interation (nested for)
   * @param size int size of each side of a square matrix
   * @param probGetOne double probalility [0,1] of each value to be 1. 
   * @return txM matrix to be returned fully populated
   */
  private int[][] populateMatrix ( int _sizeM, double pG1 ) {
    int[][] _txM = new int[_sizeM+1][_sizeM+1]; // 9x9 as 0-8 x 0-8
    int[] parityRow = new int[_sizeM]; //init 0s by default 
    int[] parityCol = new int[_sizeM]; //init 0s 
    int parityRowCol = 0;
    int flip; //used as bool
    
    for (int i=0; i<=_sizeM; i++){
      for (int j=0; j<=_sizeM; j++){ 
        //NOT THE LAST ROW OR LAST COLUMN       
        if (i!= _sizeM && j!= _sizeM){ 
           flip = 0; 
           if (random() < pG1){ flip++; }
           _txM[i][j] = flip;
           parityRow[i] += flip;
           parityCol[j] += flip;
        } else {
          // LAST COLUMN MEANS ROW PARITY READY
          if (i != _sizeM && j==_sizeM) { 
            _txM[i][_sizeM] = parityRow[i] % 2;
            parityRowCol += _txM[i][_sizeM];
          }
          // LAST ROW MEANS COLUMN PARITY READY
          if (i == _sizeM && j!= _sizeM) {
            _txM[_sizeM][j] = parityCol[j] % 2;
            parityRowCol += _txM[i][_sizeM];
          }
          // LAST VALUE: PARITY OF PARITIES ROW  + COL
          if (i == _sizeM && j== _sizeM) {
            _txM[_sizeM][_sizeM] = parityRowCol % 2;
          } 
      } //end else     
    }//end for j
   }//end for i
    return _txM;   
  }// end populateMatrix
    
      
  /* This method prints a Matrix
   @param matrix array[][] to be printed
   @return void
   */
  private void printMatrix ( int[][] m, int _sizeM ) {
    for (int i=0; i<=_sizeM; i++){
      for (int j=0; j<=_sizeM; j++){
        if (j == _sizeM -1){
        System.out.print(m[i][j] + "  |  ");
        } else {
        System.out.print(m[i][j] + "   ");
        }//end if/else decoration
    }//end for j    
      System.out.println("");
      if (i == _sizeM -1) {
        for (int k=0; k<=_sizeM*4+2; k++){
          if (k == _sizeM*4-1){
          System.out.print("+");
          } else {
          System.out.print("-");
          }
        }
        System.out.print("\n");
      }//end for k decoration
    }//end for i
    System.out.println("\n");
  } // end printMatrix  

  
  /* This method simulates the transmittion of a Matrix TxM to a ReceiverMatrix RxM
   * @param txM matrix original matrix to be transmitted
   * @param probError double probalility [0,1] of each value to be flipped
   * @return rxM matrix to be returned with possible errors injected
   */
  private int[][] transmitMatrix ( int[][] _txM, int _sizeM, double _pE ) {
    int[][] _rxM = new int[_sizeM+1][_sizeM+1];   // 9 x 9
    int _flip = 0; //used as bool
    for (int i=0; i<=_sizeM; i++){   // 0-8
      for (int j=0; j<=_sizeM; j++){  // 0-8
        if (random() < _pE){ _flip++; } //invert value 0->1
        _rxM[i][j] = ((_txM[i][j]+_flip) % 2);
        if(_flip == 1){_flip--;} // revert flip if flipped
      }//end for i
    }//end for j
    return _rxM;
  }//end transmitMatrix
    
  /*This Method checks parity Row/Column detect errors (amount and position)
   * if only one error is found, the value is fixed at (i,j) on _rxM matrix
   * @param _rxM int[][] matrix received
   * @param _sizeM int size of matrix
   * @return int[] array with status of correction [errorCountX, errorCountY, posX, posY, status]
   */
  private int[] evaluateRx (int[][] _rxM, int _sizeM){
    int _response[] = new int[5]; // [errorCountX, errorCountY, posX, posY, status]
    int parityRowCol = 0;
    int[] parityRow = new int[_sizeM];
    int[] parityCol = new int[_sizeM];
    
    // Test last bit first
    for (int k = 0; k < _sizeM; k++){  //count i-j [0-7] 
      parityRowCol += _rxM[k][_sizeM] + _rxM[_sizeM][k];  //grab [i][8] + [8][j] 
    }
    if (_rxM[_sizeM][_sizeM] != parityRowCol % 2) { // test [8][8]
      _response[4] = 9; // void packet
      return _response;
    }
 
    // Check parity for data
    parityRowCol = 0;
     for (int i=0; i<=_sizeM; i++){
      for (int j=0; j<=_sizeM; j++){ 
        
        //NOT THE LAST ROW OR LAST COLUMN   
        if (i!=_sizeM && j!=_sizeM){ 
           parityRow[i] += _rxM[i][j];
           parityCol[j] += _rxM[i][j];
        } else {
          
          // LAST COLUMN MEANS ROW PARITY READY
          if (i != _sizeM && j==_sizeM) {
            if (_rxM[i][_sizeM] != parityRow[i] % 2) {
              _response[0]++;
              _response[2] = i;
            }          
          }
          
          // LAST ROW MEANS COLUMN PARITY READY
          if (i == _sizeM && j!= _sizeM) {
            if(_rxM[_sizeM][j] != parityCol[j] % 2){
              _response[1]++;
              _response[3] = j;
            }
          }           
      } //end else     
    }//end for j
      if (_response[0] > 2) {
         _response[4] = 9; // void packet
         return _response;} 
   }//end for i 
        
     // tests
     if (_response[0] != _response[1]) {_response[4]= 9; } // void packet (errors same col or same row)
     if (_response[0] == 1 && _response[1] == 1) {
     _rxM[_response[2]][_response[3]] = (_rxM[_response[2]][_response[3]] +1) % 2;
     _response[4] = 1; // 1 error found and _rxM already fixed
    }
     return _response;
  }    

  
  private void printResult (int[] _response){
  switch (_response[4]){
    case 0:
      System.out.println("Packet Accepted, no errors found");     
      break;
    case 1:  
      System.out.println("Packet Accepted, Error corrected at >"+_response[2]+","+_response[3]+"<");           
      break;
    default:
      System.out.println("Packet Rejected, too many errors found");
  }
}

  
// main function
public static void main ( String[] args ) 
 throws InterruptedException {
  Parity p1 = new Parity(4);
  //Pause for 1 second
  Thread.sleep(1000);
  Parity p2 = new Parity(6);
  //Pause for 1 second 
  Thread.sleep(1000);
  Parity p3 = new Parity(8);
}
   

}  // Parity
    