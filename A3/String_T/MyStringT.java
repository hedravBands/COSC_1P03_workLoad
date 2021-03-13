package String_T;


/**
 * This Class implements a given interface StringT with its public methods
 * Assignment 3
 * @author Heduin R. B. de Morais (Brock_ID 6967483, Campus_ID hr19ut, Lab 12)
 * @version 1.1 (Mar. 2021) new concepts: Interfaces, Error Handling, Multiple
 *          Packages, Test Harness
 */


public class MyStringT implements StringT {
  public char[] sequence;

  public MyStringT(){
    sequence = new char[]{};
  } // constructor
  
  public MyStringT(char[] input){
     sequence = input;
  } // constructor
    
  
  //Concatenates 2 strings, this.ConCat(S)
  public StringT ConCat(StringT S){
    StringT P = this;
    int p = P.Length();
    int s = S.Length();
    char[] result = new char[p + s];
    for (int i = 0; i < p; i++) {
      result[i] = P.CharAt(i);
    }
    for (int i = 0; i < s; i++) {
      result[p + i] = S.CharAt(i);
    }
    return new MyStringT(result);
  }
  
  //Returns a string from index 0 to but not including i.
  public StringT Before(int i){
    if (i < 0 || i >= this.Length() ) {
      throw new StringTException(); 
    }
    StringT P = this;
    char[] result = new char[i];
    for (int k = 0; k < i; k++) {
      result[k] = P.CharAt(k);
    }
  return new MyStringT(result);
  }
  
  //Returns a string from i to end.
  public StringT After(int i){
    if (i < 0 || i >= this.Length() ) {
      throw new StringTException(); 
    }
   StringT P = this;
   int p = P.Length();
   char[] result = new char[p-i];
   for (int k = 0; k < p-i; k++) {
      result[k] = P.CharAt(i+k);
    }
    return new MyStringT(result);
  }
  
  //Returns the length of this.
  public int Length(){
    return this.sequence.length;
  }
  
  //Creates a deep clone of this.
  public StringT Clone(){
    return this;
  }

  //Returns the character at index i of this;
  public char CharAt(int i){
    if (i < 0 || i >= this.Length() ) {
      throw new StringTException(); 
    }
    return this.sequence[i];
  }
  
  //Returns a character array representation of this.
  public char[] ToArray(){
    return this.sequence;
  }
  
  // SomeKoolFunction1, Returns s StringT in a CamelCaseFormat
  public StringT ToCamelCase(){ 
    StringT P = this;
    int space = 32;
    int convert = 32; //coincidence! lmao
    boolean lastWasBlank = true;
    char[] result = new char[P.Length()];
    for (int i = 0; i < P.Length(); i++) {
      int code = (int)P.CharAt(i);
      boolean isUpper = (65 <= code && code <= 90);
      boolean isLower = (97 <= code && code <= 122);
        result[i] = P.CharAt(i); //clone first, then change
        if(lastWasBlank && isLower){ //case for UPPER
          result[i] = (char)(code - convert); //SET TO UPPERCASE
        }
        if(!lastWasBlank && isUpper){ //case for lower
          result[i] = (char)(code + convert); //set to lowercase
        }
      lastWasBlank = (code == space); 
    }
    return new MyStringT(result);
  }
  
  // SomeKoolFunction2, Prints StringT to the screen, returns true when finished
  public boolean PrintReverse(){
    boolean result = false;
    StringT P = this;
    for (int i = P.Length() - 1; i>=0; i--) {
      System.out.print(P.CharAt(i));
    }
    System.out.println("");
    return !result; //true if operation is done
  }
  
}//MyStringT

