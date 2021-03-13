package Test;

import String_T.*;

/**
 * This Package tests the implementation of an interface with its functions in
 * the Package String_T, by using a .jar file.
 * Assignment 3
 * @author Heduin R. B. de Morais (Brock_ID 6967483, Campus_ID hr19ut, Lab 12)
 * @version 1.1 (Mar. 2021) new concepts: Interfaces, Error Handling, Multiple
 *          Packages, Test Harness
 */

public class TestHarness {

  public TestHarness(){
    //Test 01: create empty StringT, Lenght()
 MyStringT s1 = new MyStringT();
    System.out.println("Test #01 -> Result: " + s1.Length() + " Expected: 0");
    //Test 02: create an StringT from Array, Length()
    MyStringT s2 = new MyStringT(new char[]{'a','b','C',' ','D','E','F',',',' ','u','2','!'});
    System.out.println("Test #02 -> Result: " + s2.Length()+ " Expected: 12");
    
    //Test 03: ToCamelCase(), ToArray()
    System.out.print("Test #03 -> Result: " );
    System.out.print(s2.ToCamelCase().ToArray() );
    System.out.println(" Expected: Abc Def, U2!" );
     
    //Test 04: ToCamelCase(), PrintReverse()
    System.out.print("Test #04 -> Result: ");
    s2.ToCamelCase().PrintReverse();
    System.out.println("Expected: !2U ,feD cbA" );
    
    //Test 05: Clone(), ToCamelCase(), ToArray()
    System.out.print("Test #05 -> Result: ");
    System.out.print(s2.Clone().ToCamelCase().ToArray());
    System.out.println("Expected: Abc Def, U2!" );
    
    //Test 06: CharAt(i) ArrayIndexOutOfBoundsException  (intercepted)
    char[] error = new char[]{'T','r','i','c','k','y',' ','E','x','c','e','p','t','i','o','n','!'};
    System.out.print("Test #06 -> Result: "); 
     try {
       s2.CharAt(-1);
     } catch (StringTException se){
       System.out.print(se.message.ToArray());
     } catch (Exception e){
       StringT errorS = new MyStringT(error);
       errorS.PrintReverse();
     }
     System.out.println("Expected: Oh No ..." );
     
     //Test 07: CharAt(i) ok
      System.out.println("Test #07 -> Result: " + s2.CharAt(0) + " " + s2.CharAt(4) + " " + s2.CharAt(11) + " Expected: a D !" );

      //Test 08 Before(i) ArrayIndexOutOfBoundsException  (intercepted)
      System.out.print("Test #08 -> Result: "); 
     try {
       s2.Before(-1);
     } catch (StringTException se){
       System.out.println(se.message.ToArray());
     } catch (Exception e){
       StringT errorS = new MyStringT(error);
       errorS.PrintReverse();
     }
     System.out.println("Expected: Oh No! Ohh Noo!!..." );
      
      //Test 09: Before(i) ok, ToArray()
      System.out.print("Test #09 -> Result: ");
       System.out.print(s2.Before(3).ToArray() );
        System.out.println(" Expected: abC" );
      
      
      //Test 10: After(i) ArrayIndexOutOfBoundsException  (intercepted)
    System.out.print("Test #10 -> Result: "); 
     try {
       s2.After(-1);
     } catch (StringTException se){
       System.out.print(se.message.ToArray());
     } catch (Exception e){
       StringT errorS = new MyStringT(error);
       errorS.PrintReverse();
     }
     System.out.println(" Expected: Oh No! Ohh Noo!!..." );
      
      //Test 11: After(i) ok, ToArray()
      System.out.print("Test #11 -> Result: ");
      System.out.print(s2.After(4).ToArray());
      System.out.println(" Expected: DEF, U2!" );
      
     //Test 12: ConCat() ToCamelCase() Before() After() ToArray()
      StringT concat = s2.Before(4).ConCat(s2.After(4)); 
      System.out.print("Test #12 -> Result: ");
      System.out.print(concat.ToCamelCase().ToArray());
      System.out.println(" Expected: Abc Def, U2!");
      
      //Test 13: General RunTimeException with CharAt(-1), PrintReverse()
    System.out.println("Test #13 -> Results below: "); 
     try {
       s2.CharAt(-1);
     } catch (RuntimeException e){
       StringT errorS = new MyStringT(error);
       System.out.println(errorS.PrintReverse());
       System.out.println(errorS.ToArray());
     }
     System.out.println(" Expected: !noitpecxE ykcirT  |  true  |  Tricky Exception!" );
      
  }//constructor
  
  
// main function

  public static void main(String[] args) {
    TestHarness t = new TestHarness();
  }

} // TestHarness
