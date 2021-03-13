package String_T;

/**
 * This Class of Exception that extends RuntimeException, creating a customized 
 * error message for StringTException
 * Assignment 3
 * @author Heduin R. B. de Morais (Brock_ID 6967483, Campus_ID hr19ut, Lab 12)
 * @version 1.1 (Mar. 2021) new concepts: Interfaces, Error Handling, Multiple
 *          Packages, Test Harness
 */

public class StringTException extends RuntimeException{
  public StringT message;
  
  public StringTException(){
    char[] msg = new char[]{'O','h',' ','N','o','!',' ','O','h','h',' ','N','o','o','!','!','.','.','.'};
    message = new MyStringT(msg);
  }
}