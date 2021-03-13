package String_T;

/*
 * This interface is given to be implemented as part of Assignment 3
 * Two added functions: ToCamelCase() and PrintReverse()
 * @author Heduin R. B. de Morais (Brock_ID 6967483, Campus_ID hr19ut, Lab 12)
 * @version 1.1 (Mar. 2021) new concepts: Interfaces, Error Handling, Multiple
 *          Packages, Test Harness
 */

public interface StringT {
  
//Concatenates 2 strings, this.ConCat(S)
public StringT ConCat(StringT S);

//Returns a string from index 0 to but not including i.
public StringT Before(int i);

//Returns a string from i to end.
public StringT After(int i);

//Returns the length of this.
public int Length();

//Creates a deep clone of this.
public StringT Clone();

//Returns the character at index i of this;
public char CharAt(int i);

//Returns a character array representation of this.
public char[] ToArray();

// SomeKoolFunction1, Returns s StringT in a CamelCaseFormat
public StringT ToCamelCase();

// SomeKoolFunction2, Prints StringT to the screen, returns true when finished
public boolean PrintReverse();

}