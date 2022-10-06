import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* 
 * PhoneBook class
 * CSE 174 Spring 2020
 * Morgan Keeton
 * CSE 174 D
 * 
 * The PhoneBook class simulates a simple phone book application.
 * Copyright(C) Nov 2020
 */

public class PhoneBook {
   public static ArrayList<ContactInfo> list = new ArrayList<ContactInfo>();
   /**
    * 
    * @List, the given Array List
    * @Name, the given String name
    * @label, the given String label
    * @pnum, the given String
    * @return void 
    */ 
   public static void add(ArrayList<ContactInfo> list, String name, 
                          String label, String pNum) { 
      // Adds a ContactInfo object to the list 
      // If the name is already in the list
      // the method should print:
      // "The name already exists!"
      // If the contact is added successfully, the method should
      // print "*The contact has been added successfully*"
      
      for (ContactInfo contact : list){
         if (contact.getName().equals(name)){
            System.out.println("The name already exists!");
            return;
         }
      }
      
     Phone p = new Phone(label, pNum);
     ArrayList<Phone> phoneList = new ArrayList<Phone>();
     phoneList.add(p);
      
     ContactInfo contact1 = new ContactInfo(name, phoneList);
     
     list.add(contact1);
     
     System.out.println("The contact has been added successfully!");
   }
   
   /**
    * 
    * @list, the given ArrayList
    * @name, the given String name
    * @label, the given String label
    * @pNum, the giben String pnum
    */ 
   public static void append(ArrayList<ContactInfo> list, String name,     
                             String label, String pNum) { 
      // Adds a new Phone object to a specific ContactInfo  
      // If this person does not exist the method should print: 
      //   "Couldn't find the name!" 
      // If a similar label or phone number already exists for  
      // that person, the result should be: 
      //     "The label/number already exists for this person!" 
      // If the number is added, the method should print 
      // "*The number has been added successfully*"
      
      Phone p1 = new Phone(label, pNum);
      ArrayList<Phone> phoneList3 = new ArrayList<Phone>();
      phoneList3.add(p1);
      
     ContactInfo contact4 = new ContactInfo(name, phoneList3);
     
     list.add(contact4);
     
     System.out.println("The number has been added successfully!");
   } 
   
   /**
    * 
    * 
    * @list, the given ArrayList
    * @name, the given String name
    */ 
   public static void display(ArrayList<ContactInfo> list, 
                              String name) {
      
       for (ContactInfo contact : list){
         if (contact.getName().equals(name)){
           for (ContactInfo contact4 : list) {
         System.out.println(contact4.getName());
         for (Phone phone : contact4.getPhones()){
            System.out.println(phone.getLabel() + " " 
                                  + phone.getPhonNum());
         }
      }
         }
         return;
      }
     System.out.println("Couldn't find the name!"); 
   }
   //Displays all the phone numbers that belongs to the given   
   // name. 
   // If the name doesn't exist in the list, the method should print
   // "Couldn't find the name"  
   
   
   /**
    * 
    * @list, the given Array List
    */ 
   public static void displayAll(ArrayList<ContactInfo> list){ 
      // Displays all the names and the phone numbers. 
      // If the list is empty, the method should print
      //"The list is Empty!"
      for (ContactInfo contact : list) {
         System.out.println(contact.getName());
         for (Phone phone : contact.getPhones()){
            System.out.println(phone.getLabel() + " " 
                                  + phone.getPhonNum());
         }
      }
   } 
   
   /**
    * 
    * @list, the given ArrayList
    * @name, the given String name
    */ 
   public static void remove(ArrayList<ContactInfo> list,  
                             String name) { 
      // Remove all data related to the given name from the  
      // list. 
      // If the list is empty, the method should print
      // "The list is Empty!",
      // If the name does not exist the method should print 
      // "Couldn't find the name!"
      // If the contat is removed the method should print
      // *Contact is removed successfully*
      
      for (int i = 0; i < list.size(); i++){
         if(list.get(i).getName().equals(name)){
            list.remove(i);
            System.out.println("Contact is removed successfully");
            return;
         }
      }
      System.out.println("Couldn't find the name!");
   } 
   
   /* displays the menu
    */ 
   private static void displayMenu(){
      
      System.out.println("1. Add a Contact");
      System.out.println("2. Add a new number to an old contact");
      System.out.println("3. Display a contact");
      System.out.println("4. Display All");
      System.out.println("5. Remove a contact");
      System.out.println("6. Exit");
   }
   /* loops the menu based on the given choice
    */ 
   private static void loop(){
      Scanner keyboard = new Scanner(System.in);
      for (int i = 0; i < 100; i++){
         displayMenu();
         System.out.println("Enter your choice:");
         int choice = keyboard.nextInt();
         
         // validates user input
         if (choice < 1 || choice > 6)
            System.out.println("Invalid Input!!");
         
         // calls the switch case
         switchCase(choice);
         
         // if user enters 6, ends the program
         if (choice == 6)
            break;
      }
   }
   
   /* case 1 of the switch
    */
   private static void case1(){
      Scanner keyboard = new Scanner(System.in);
      System.out.println("--- Add a contact");
      System.out.println("Name:");
      String name = keyboard.next();
      System.out.println("Label:");
      String label = keyboard.next();
      System.out.println("Phone number (i.e. (513)111-1111)");
      String pNum = keyboard.next();
      
      // appends contactinfo
      append(list, name, label, pNum);
      
   }
   /* case 2 of the switch
    */ 
   private static void case2(){
      Scanner keyboard = new Scanner(System.in);
      System.out.println("--- Add a new number");
      System.out.println("Name:");
      String name = keyboard.next();
      
      // displays method
      display(list, name);
      
      // adds name
      case1();
   } 
   
   /* case 3 of switch
    */ 
   private static void case3(){
      Scanner keyboard = new Scanner(System.in);
      System.out.println("---Display a contact");
      System.out.println("Name:");
      String name = keyboard.next();
      
      // calls the display method
      display(list, name);
   }
   /* case 4 of switch
    */    
   private static void case4(){
      System.out.println("--- Display all");
      
      // calls the display method
      displayAll(list);
      
   }
   /* case 5 of switch
    */    
   private static void case5(){
      Scanner keyboard = new Scanner(System.in);
      System.out.println("--- Remove a contact");
      System.out.println("Name:");
      String name = keyboard.next();
      
      // calls the remove method
      remove(list, name);
   }
   /* method for the switch choice
    * @param choice, the given int choice
    */ 
   private static void switchCase(int choice){
      
      // calls the switch case methods within the switch case
      switch(choice){
         case 1: 
            case1(); break;
         case 2:
            case2(); break;
         case 3:
            case3(); break;
         case 4:
            case4(); break;
         case 5:
            case5(); break;
      }
      
   }
   public static void main(String[] args) {
      // This is how you need to define your list.
      // Use this list.
      ArrayList<ContactInfo> list = new ArrayList<ContactInfo>();
      Scanner keyboard = new Scanner(System.in);
      
     // uses the loop that has the switch case and all methods
      // called within the switch case to run the program
      loop();
      
      
   }
}
