/*
Name:           Siobhan Boylan
Date:           October 8, 2023
description:    a Java program to simulate Instagram profile
purpose:        to practice / implement ArrayList<> Objects and Comparable<> interface
*/

import java.util.*;
public class SocialMedia
{
   //no code here
}
class User implements Comparable {
    private String first;
    private String last;
    private int age;
    private String userName;
    private boolean followBack;
    private int followers;
      
    // Constructor     
    public User(String first, String last, int age, String userName, boolean followBack){
        this.first = first;
        this.last = last;
        this.age = age;
        this.userName = userName;
        this.followBack = followBack;
    }
    
    
    // -----------------------
    //   Accessors / Getters
    // -----------------------
    
    public boolean getFollow()
    {
        return this.followBack;
    }
    
    public String getFirst() 
    {
        return this.first;
    }
    
    public String getLast() 
    {
        return this.last;
    }
    
    public int getAge() 
    {
        return this.age;
    }
    
    public String getUsername()
    {
        return ""; //your code
    }
    
    // ----------------------
    //   Mutators / Setters
    // ----------------------
          
    public void setFirst(String first) 
    {
        this.first = first;
    }
    
    public void setLast(String last) 
    {
        this.last = last;
    }
    
    public void setAge(int age) 
    {
        this.age = age;
    }
    
    // *******************
    //   Utility Methods
    // *******************
    
    public void unfollow()
    {
        this.followBack = false;
    }
    
    public void follow()
    {
        this.followBack = true;
    }
    
    // compare Users based on userName
    public int compareTo(Object o) 
    {
        User u = (User)o;
        return (this.userName).compareTo(u.userName);
    }
    
    public boolean equals(User other) 
    {
        return this.last.equals(other.last) && this.first.equals(other.first) && this.age == other.age;
    }
    
    public String toString() 
    {
        String print = "";
        print += String.format(
                                "User name: %s\n" +
                                "First name: %s\n" +
                                "Last name: %s\n"+
                                "Age: %d\n",
                                userName,
                                first,
                                last,
                                age
                              );
        if (this.followBack)
        {
            print += "You follow back this person\n";
        } else {
            print += "You are not following this person\n";
        }
        
        print += "**************************************************\n";
        
        return print;
    }
}


// ###############################
// ###############################
//         Instagram Class
// ###############################
// ###############################

class Instagram{
    private ArrayList<User> app;
     
    public Instagram()
    {
        app = new ArrayList<User>();
    }
    
    // follows a User from app ArrayList
    public void followBack(String first, String last)
    {
        String s = first + " " + last; 
        for(int i = 0; i < app.size(); i++) 
        { 
            String s1 = app.get(i).getFirst() + " " + app.get(i).getLast(); 
            if(s1.equalsIgnoreCase(s)) 
            { 
                app.get(i).follow(); 
            }
        }
    }
    
    
    // gets information for a User
    // creates User
    // adds it IN ORDER to ArrayList app 
    // don't add if user already in ArrayList app
    public boolean follow(String first, String last, int age, String userName, boolean followBack) {
        
        // make new User from inputs
        User u = new User(first, last, age, userName, followBack);
        
        // check if already in list (don't add)
        if (find(first, last, age))
        {
            return false;
        }
        
        // if no Users in app, 
        // add and return true - no need to add in order
        if (app.size() == 0)
        {
            app.add(u);
            return true;
        } 
        
        // only other option is the user must be added, 
        // but must add in order
        
        // set up variable for return
        boolean following = false;
        
        for (int i = 0; i < app.size(); i++)
        {
            if (u.compareTo(app.get(i)) < 0)
            {
                app.add(i, u);
                following = true;
                return true;
            }
            
        }
        
        // if you get through the whole list without adding the user,
        // add to end of ArrayList & return true
        if (!following)
        {
            app.add(u);
            following = true;
            return true;
        }
        
        // return whether User was added
        return following;                  	
    }
     
    /*This method removes the person from the list meaning that they are not following you
    and you are not following them*/
    public boolean delete(String first, String last) {
        
        for (int i = 0; i < app.size(); i ++)
        {
            if (first.equals(app.get(i).getFirst()) && last.equals(app.get(i).getLast()))
            {
                app.remove(i);
                return true;
            }
        }
        
        return false; 
    }
           
    
    public boolean find(String first, String last, int age) 
    {
        for (int i = 0; i < app.size(); i ++)
        {
            if (first.equals(app.get(i).getFirst()) && last.equals(app.get(i).getLast()) && age == app.get(i).getAge())
            {
                return true;
            }
        }
        
        return false;
    }
    
    // returns ArrayList app
    public ArrayList<User >getList()
    {
        return app;
    }
    
    // returns number of Users in ArrayList app
    public int followersNum()
    {
        return app.size();
    }
    
    // returns number of people this person is following
    public int followingsNum()
    {
        int num = 0;
        for (int i = 0; i < app.size(); i ++)
        {
            if (app.get(i).getFollow() == true)
            {
                num += 1;
            }
        }
        
        return num;      
    } 
    
    // returns print friendly version of app
    public String toString() 
    { 
        String print = "";
        for (int i = 0; i < app.size(); i ++)
        {
            print += app.get(i).toString();
        }
        
        return print; 
    }
}
/* create your own driver here
Must create a list with 5 users.
Make sure to call all the methods you wrote */
class MyDriver
{
    public static void main(String[] args)
    {
        // make new Instagram
        Instagram myGram = new Instagram();
        
        // add 5 Users
        myGram.follow("Beyonce", "Knowles", 42, "QueenBee", false);
        myGram.follow("Samara", "Joy", 24, "SJoy", false);
        myGram.follow("Nikole", "Hannah-Jones", 47, "1619", false);
        myGram.follow("Ayesha", "Rascoe", 37, "WkndEd", false);
        myGram.follow("Leslie", "Jones", 56, "Annette", false);
        
        // print my Instagram
        System.out.println("\nYour followers:\n");
        System.out.println(myGram);
        System.out.println("Number of followers: " + myGram.followersNum());
        System.out.println("You are following " + myGram.followingsNum() + " users");
        System.out.println("\n--------------------\n");
        
        
        
        // search for a follower that isn't in the list
        System.out.println("\nSearching for that bozo Jeff Bezos...");
        
        if(myGram.find("Bezos", "Jeff", 59) == false) 
        {
            System.out.println("\nThat thief is not in your list of followers!");
            System.out.println("\n***************************\n");
        }
        
        // delete a follower
        System.out.println("\nUnfollowing Samara Joy...\n");
        myGram.delete("Samara", "Joy");
        
        // print after deletion:
        System.out.println("\nYour followers:\n");
        System.out.println(myGram);
        
        // add new follower
        myGram.follow("Kamala", "Harris", 58, "Veep", false);
        
        // follow back someone from your list
        System.out.println("\n\nFollow Back Ayesha Rascoe...");
        myGram.followBack("Ayesha", "Rascoe");
        System.out.println("\n\nFollow Back Nikole Hannah-Jones...");
        myGram.followBack("Nikole", "Hannah-Jones");
        
        // show the updated list
        System.out.println("\nUpdated list of followers:\n");
        System.out.println(myGram);
    }
}


/*below is a sample driver. Do not remove this driver from your code when sub,itting it*/
class Driver{
   public static void main(String[]args) {
      Instagram myInsta = new Instagram();
      
      // Adding followers to your list
      // the boolean field indicates whether you want to follow them back
      
      myInsta.follow("Matthew", "Philips", 44, "MatPhil", true); // must modify this line to include the added attribute
      myInsta.follow("Gary", "Kane", 20, "GKane", false); //// must modify this line to include the added attribute
      myInsta.follow("Robert", "Kenny",  13, "RKenny", true); //// must modify this line to include the added attribute
      myInsta.follow("Bill", "Fitch", 75, "BillF", true);//// must modify this line to include the added attribute
      myInsta.follow("Trevor", "Schlulz", 63, "TrevorS", false);//// must modify this line to include the added attribute
      
      // Displaying your followers
      System.out.println("Your followers informations\n");
      System.out.println(myInsta);
    	
      // Unfollowing a user
      System.out.println("Removing Robert Kenny from your followers list");
      myInsta.delete("Robert", "Kenny");
   	
      // Displaying the list
      System.out.println("List of followers after removing Robert Kenny");
      System.out.println(myInsta);
   	
      // adding a new follower
      System.out.println("Adding Elon Musk to your list of followers");
      myInsta.follow("Elon", "Musk", 502, "ElonM", true);
      
      // Dipslying the followers
      System.out.println("List of your followers:");
      System.out.println(myInsta);
   	
      // Searching for a follower
      System.out.println("Searching for Stonewall Jackson(StonW) in your followers list");
      if(myInsta.find("Jackson", "Stonewall", 26) == false) {
         System.out.println("Stonewall Jackson is not in your list of followers");
         System.out.println("\n***************************");   
         System.out.println("You are following " + myInsta.followersNum() + " people");
      
         System.out.println("You have " + myInsta.followingsNum() + " followers");  
         System.out.println(myInsta);
         Scanner kb = new Scanner(System.in);
         System.out.println("Enter the first name and the last name of  of the person that you want to follow back: ");
      
         String first =kb.next();
         String last = kb.next();
          
         myInsta.followBack(first, last);
      
         System.out.println(myInsta);
      
      }
   	
   }
}
