/*
 * Name: Morgan Keeton
 * Instructor: Meisam Amjad
 * CSE 174, Section D
 * Date: 04.26.2020
 * Filename: Program12
 * Description: writes a program that accepts user input, creates 
 * methods, and plays a game of sudoku
 */

import java.util.Scanner;
public class Program12 {
   public static Scanner in = new Scanner(System.in);
   
   /* accepts a string and prompts the user to enter an array
    * @param str the given string
    * @return array, the given integer array
    */ 
   public static int[] getInput(String str) {
      // prints out the string and uses input for index array values
      System.out.println(str);
      int[] array = {in.nextInt(), in.nextInt(), in.nextInt()};
      
      
      // returns the array created by the user
      return array;
      
      
   }
   /* accepts an integer min and max and returns a random number
    * between the given min and max
    * @param min, max the given integer values
    * @return a random number between the min and max
    */
   public static int rnd(int min, int max) {
      
      return min + (int) (Math.random()* (max - min + 1));
   }
   /* uses a loop to check to see if an array contains 0
    * returns true if there are no zeros, false otherwise
    * @param arr, the given 2D array
    * @return true / false, the boolean result
    */ 
   public static boolean isDone(int[][] arr) {
      
      // uses nested for-loops to go through rows and columns
      //checks to see if either contains a value 0
      // if it contains 0, returns false
      for (int row = 0; row < arr.length; row++){ 
         for (int col = 0; col < arr[row].length; col++){
            if (arr[row][col] == 0)
               return false;
            
         } 
      } 
      // returns true if the loop didn't find any zeros
      return true;
   }
   /* accepts a 2D integer array and checks to see if there are any 
    * duplicate values in the array
    * returns true if there arent duplictes, false otherwise
    * @param arr, the given 2D integer array
    * @return true / false the boolean value
    */ 
   
   public static boolean acceptVal(int[][] arr) {
      for(int row = 0; row < arr.length; row++){
         if(arr[row][0] != arr[row][1] && arr[row][0] != 0)
            return true;
         if(arr[row][1] != arr[row][2] && arr[row][1] != 0)
            return true;
         if(arr[row][2] == arr[row][0] && arr[row][2] != 0)
            return true;
      }
         
      
         for(int col = 0; col < 3; col++){
          if(arr[0][col] != arr[1][col] && arr[0][col] != 0)
             return true;
          if(arr[1][col] != arr[2][col] && arr[1][col] != 0)
             return true;
          if(arr[2][col] != arr[0][col] && arr[2][col] != 0)
             return true;
               
         
      }
   
      return false;
   }
   public static void display(int[][] arr) {
      int i = 0, j = 0;
      for (int[] row: arr) {
         for (int col: row) {
            System.out.printf("%-15s", "["+ i + " " + (j++) +"]: " + col);
         }
         i++;
         j = 0;
         System.out.println();
      }
      System.out.println("--------------------------");
   }
   
   public static void initial(int[][] arr) {
      int val = 0, col = 0;
      boolean check = true;
      
      for (int i = 0; i < 3; i++) {
         do {
            check = true;
            val = rnd(1, 3);
            col = rnd(0, 2);
            arr[i][col] = val;
            if (!acceptVal(arr)) {
               check = false;
               arr[i][col] = 0;
            }
         } while (!check);
      }
   }
   
   public static void main(String[] args) {
      int[][] arr = new int[3][3];
      int val = 0, col = 0, row = 0;
      boolean check = true;
      
      initial(arr);
      
      while (!isDone(arr)) {
         display(arr);
         
         do {
            check = true;
            
            int[] inputs = getInput("Enter your move [row col num]: ");
            row = inputs[0];
            col = inputs[1];
            val = inputs[2];
            
            if (val < 1 || val > 3 || row < 0 || row > 2
                   || col < 0 || col > 2) {
               check = false;
               continue;
            }
            arr[row][col] = val;
            if (!acceptVal(arr)) {
               check = false;
               arr[row][col] = 0;
               
            }
         } while(!check);
      }
      
      display(arr);
      System.out.println("End!");
   }
}