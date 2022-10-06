/*
 * Name: Morgan Keeton
 * Instructor: Meisam Amjad
 * CSE 174, Section D
 * Date: 04.12.2020
 * Filename: Program10
 * Description: writes a program that accepts user input and changes
 * the open and closed lockers
 */

// Sequence: 1, 4, 9, 16, 25, 36, 49, 
// Sequence: a sub n = n^2

import java.util.Scanner;
public class Program10{
   
   /* shows the process of X's and O's if the user enters "y"
    * @param array, the boolean array
    * @return the method does not return anything
    */ 
   public static void showProcess(boolean[] array) {
      
      // uses a for-loop to change the true and false of array
      for(int i = 1; i <= array.length; i++){
         for(int k = 0; k < array.length; k++){
            if((k + 1) % i == 0){
               if(array[k])
                  array[k] = false;
               
               else 
                  array[k] = true;
               
            }
            
            // uses a for loop to print x's and o's based on true/false
            
            if(array[k])
               System.out.print("O");
            
            else
               System.out.print("X");
            
         }
         System.out.println(" ");
         
      } 
   }
   
   /* simply prints the Open lockers
    * @param array, the boolean array
    * @return the method does not return anything
    */
   public static void openLockers(boolean[] array) {
      
      // uses a for-loop to create the end sequence of lockers
      System.out.print("Open: ");
      for(int i = 1; i < array.length; i++) {
         int j = (int)Math.pow(i, 2);
         if (j > array.length)
            break;
         System.out.print(j + ", ");
      }
      
      
   }
   
   
   public static void main(String[] args){
      Scanner kb = new Scanner(System.in);
      
      // prompts the user to enter the number of lockers
      System.out.println("How many lockers?");
      int size = kb.nextInt();
      
      // creates a boolean array with the size of the user's locker #
      boolean[] array = new boolean[size];
      
      // asks the user if they want to see the stages
      System.out.println("Show stages (y/n)?");
      String yesOrNo = kb.next();
      
      // calls above method to show the stages
      if (yesOrNo.equals("y")) 
         showProcess(array);
      
      // calls above method to simply print the open lockers
      openLockers(array);
      
   } // end main method
   
} // end class











