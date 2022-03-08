/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Pratiksss
 */
public class SafeInput 
{
    //Part A
    /**
    * 
    * @param pipe a Scanner opened to read from System.in
    * @param prompt prompt for the user
    * @return a String response that is not zero length
    */ 

   public static String getNonZeroLenString(Scanner pipe, String prompt)
   {
       String retString = "";  // Set this to zero length. Loop runs until it isn’t
       do
       {
           System.out.print("\n" + prompt + ": "); // show prompt add space
           retString = pipe.nextLine();
       }while(retString.length() == 0);
       
       return retString;
       
   }
   
   //Part B
    /**
    * 
    * @param pipe a Scanner opened to read from System.in
    * @param prompt prompt for the user
    * @return a String response that is not zero length
    */ 

   public static int getInt(Scanner pipe, String prompt)
   {
       String trash = "";
       int retInt = 0;
       boolean done = false;
       
       do
       {
           done = false;
           trash = "";
           System.out.print("\n" + prompt + ": "); // show prompt add space
 
            if(pipe.hasNextInt())
            {
                // OK safe to read in a int
                retInt = pipe.nextInt(); 
                pipe.nextLine(); // clears the newline from the buffer
                done = true;
            }
            else
            { 
               // Not a int can’t use nextDouble() read as String with nextLine() instead
               trash = pipe.nextLine();
               System.out.println("\nYou must enter an Int:");
               
            }
       }while(!done);
       
       return retInt;
       
   }

   //Part C
    /**
    * 
    * @param pipe a Scanner opened to read from System.in
    * @param prompt prompt for the user
    * @return a String response that is not zero length
    */ 

   public static double getDouble(Scanner pipe, String prompt)
   {
       String trash = "";
       double retDouble = 0;
       boolean done = false;
       
       do
       {
           done = false;
           trash = "";
           System.out.print("\n" + prompt + ": "); // show prompt add space
 
            if(pipe.hasNextDouble())
            {
                // OK safe to read in a int
                retDouble = pipe.nextDouble(); 
                pipe.nextLine(); // clears the newline from the buffer
                done = true;
            }
            else
            { 
               // Not a int can’t use nextDouble() read as String with nextLine() instead
               trash = pipe.nextLine();
               System.out.println("\nYou must enter an Double:");
               
            }
       }while(!done);
       
       return retDouble;
       
   }
   //Part D
   
   public static int getRangedInt(Scanner pipe, String prompt, int lo, int hi)
   {
        int result = 0;
        boolean done = false;
        String trash = "";
        do
        {            
            // Code and control logic to loop until validated
            System.out.print("\n"+ prompt + "[" + lo + " - " + hi + "]: ");
            if(pipe.hasNextInt())
            {
                result = pipe.nextInt();
                pipe.nextLine(); // clear input buffer
                if(result >= lo && result <= hi)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You must enter a value in range [" + lo + " - " + hi + "]: " + result);
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter an int [" + lo + " - " + hi + "]: " + trash);
            }
       
        }while(!done);
        
        return result;
   }
    
   
   //Part E
   
   public static double getRangedDouble(Scanner pipe, String prompt, double lo, double hi)
   {
        double result = 0;
        boolean done = false;
        String trash = "";
        do
        {            
            // Code and control logic to loop until validated
            System.out.print(prompt + "[" + lo + " - " + hi + "]: ");
            if(pipe.hasNextDouble())
            {
                result = pipe.nextDouble();
                pipe.nextLine(); // clear input buffer
                if(result >= lo && result <= hi)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You must enter a value in range [" + lo + " - " + hi + "]: " + result);
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a double [" + lo + " - " + hi + "]: " + trash);
            }
       
        }while(!done);
        
        return result;
   }
   
   //Part F
    /**
    * 
    * @param pipe a Scanner opened to read from System.in
    * @param prompt prompt for the user
    * @return a String response that is not zero length
    */ 

   public static boolean getYNConfirm(Scanner pipe, String prompt)
   {
       boolean result = false; 
       String opString = "";
       boolean validOp = false;
       
        do 
        {           
          
           System.out.print("\n" + prompt + ": "); // show prompt add space
           opString = pipe.nextLine();
           
            if ((opString.equalsIgnoreCase("Y")) || (opString.equalsIgnoreCase("N")))
            {
                if (opString.equalsIgnoreCase("Y"))
                {
                    result = true;
                }
                if(opString.equalsIgnoreCase("N"))
                {
                    result = false;
                }
                validOp = true;
            }
            else
            {
                validOp = false;
            }
           
        } while (!validOp);
       
       return result;
       
   }

   //Part G
   
    public static String getRegExString(Scanner pipe, String prompt, String regEx) 
    {
        String retString = "";
        String trash = "";
        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
         
        System.out.print("\n" + prompt + ": "); // show prompt add space
        
        retString = pipe.next(); // clears the newline from the buffer
        Matcher matcher = pattern.matcher(retString);

        boolean matchFound = matcher.find();

        if(!matchFound)   
        {
           // trash = pipe.nextLine();
            retString = "Invalid Input";
            System.out.println("\nInvalid Input: " + trash);
        }        
    
        return retString;               
    }
   
   
   
   
   
   
   
   // Pretty Header
   public static void prettyHeader(String msg)
   {
     
       for (int i = 0; i < 60; i++)
       {
           System.out.print("*");
       }
       
       int lMsg = msg.length();
       int leftMsg = lMsg/2;
       int startMsg = 28 - leftMsg;
       int endMsg = 28 + leftMsg;
       
       System.out.println("");
       
       for (int i = 0; i < 60; i++)
       {
           if(i < 3 || i > 56) System.out.print("*");
           
           else if(i < startMsg || i > endMsg) System.out.print(" ");
           
           if(i == startMsg) System.out.print(msg);
       }
       System.out.println("");
       for (int i = 0; i < 60; i++)
       {
           System.out.print("*");
       }
   }
   
   
}
