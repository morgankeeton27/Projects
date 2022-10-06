/* Morgan Keeton
 * CSE 174 D
 * 10 May 2020
 * Program 13 - works with array lists and methods
 * Filename: ContactInfo
 */ 
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactInfo{
  private String name;
  private ArrayList<Phone> phoneNums;
  
  /* default constructor for name and PhoneNums
   */ 
  public ContactInfo(){
    this.name = "unknown";
    this.phoneNums = new ArrayList<Phone>();
  }
  /* holds the values of name and phoneNums
   * @param name, the String value, phoneNumber, the array list
   * @return void
   */ 
  public ContactInfo(String name, ArrayList<Phone> phoneNumber){
    this.name = name;
    this.phoneNums = phoneNumber;
  }
  /* returns the variable of name
   */ 
  public String getName(){
    return this.name;
  }
  
  /* returns the araylist of phoneNums
   */ 
  public ArrayList<Phone> getPhones(){
    return this.phoneNums;  
    
  }
  /* checks to see if a phone number is already there
   * @param Phone P
   * @return true / false based on the boolean object
   */ 
  public boolean addPhone(Phone p){
    
    // for (int i = 0; i < phoneNums.size(); i++){
    for (Phone ph: getPhones()){
      if (ph.getLabel().equals(p.getLabel()) ||
          ph.getPhonNum().equals(p.getPhonNum()))
        return false;
    }
    
    getPhones().add(p);
    return true;
  }
  /* checks to see if a phone number is already there
   * @param string PhoneNums
   * @return true / false based on the boolean object
   */ 
  public boolean removePhone(String phonNum){
    for (int i = 0; i < phoneNums.size(); i++){
      //for (Phone P: phoneNums){ContactInfo pp1 = new ContactInfo ()
      if (getPhones().get(i).getPhonNum().equals(phonNum)){
        getPhones().remove(i);
        return true;
      }
    }
    
    return false;
  }
}



