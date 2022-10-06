/*
 * Name: Morgan Keeton
 * Instructor: Meisam Amjad
 * CSE 174, Section D
 * Date: 03.08.2020
 * Filename: TestProgram6
 * Description: creates a series of methods
 */


public class Program6 {
   
   /*
    * checks to see if an integer value user input is even. Returns 
    * true if it is even, and false if it is not.
    * @param a the given integer value
    * @return boolean true or false based on given integer value
    */
   public static boolean isEven(int a) {
      
      // declares the result as a boolean value
      boolean result;
      
      // checks to see if the given int value is even or not
      if (a % 2 == 0) result = true;
      else result = false;
      
      return result;
      
   } // end method
   
   /* accepts a 4-digit postive integer value and substitutes even
    * digits with zero and returns a new integer. If integer value is
    * not positive or has less than four digits, returns 0
    * @param a the given integer value
    * @return new the new integer value
    */
   public static int changeEvens(int a) {
      
      // declares variables for later use
      int firstDigit, secondDigit, thirdDigit, lastDigit, newInt;
      
      // if the integer is negative or not four digits, returns 0
      if (a < 0 || a < 1000 || a > 9999)
         return 0;
      
      // separates the digits and saves them as integer values
      lastDigit = a % 10;
      a = a / 10;
      thirdDigit = a % 10;
      a = a / 10;
      secondDigit = a % 10;
      firstDigit = a / 10;
      
      // if the individual digit is even, saves it as 0
      if (Program6.isEven(firstDigit) == true) firstDigit = 0;
      if (Program6.isEven(secondDigit) == true) secondDigit = 0;
      if (Program6.isEven(thirdDigit) == true) thirdDigit = 0;
      if (Program6.isEven(lastDigit) == true) lastDigit = 0;
      
      
      // puts the new digits into a new integer value
      newInt = firstDigit * 10 + secondDigit;
      newInt = newInt * 10 + thirdDigit;
      newInt = newInt * 10 + lastDigit;

      return newInt;  
      
   } // end class
   
   public static int addDigits(int num, int index){
      
      // declares variables for a later use
      int firstDigit, secondDigit, thirdDigit, lastDigit, sum;
      
      
      // if the index or numbers do not fit the parameters, returns 0
      if (index < 0 || index > 3 || num < 1000 || num > 9999) 
         return 0;
      
      // separates the digits and saves them as variables
      lastDigit = num % 10;
      num = num / 10;
      thirdDigit = num % 10;
      num = num / 10;
      secondDigit = num % 10;
      firstDigit = num / 10;
      
      
      // adds up the digits based on the indices given
      if (index == 0) sum = firstDigit + secondDigit + thirdDigit + 
         lastDigit;
      else if (index == 1) sum = secondDigit + thirdDigit + lastDigit;
      else if (index == 2) sum = thirdDigit + lastDigit;
      else sum = lastDigit;
      
      return sum;
      
   } // end method 
} // end class

