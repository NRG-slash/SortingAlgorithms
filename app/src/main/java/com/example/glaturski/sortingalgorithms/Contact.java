package com.example.glaturski.sortingalgorithms;

/**
 * Created by g.laturski on 4/6/2018.
 */



public class Contact
{
   private String name;
   private String phone;
   private String email;

   public Contact()
   {
      name = "";
      phone = "";
      email = "";
   }

   public Contact(String nameInput, String phoneInput, String emailInput)
   {
      name = nameInput;
      phone = phoneInput;
      email = emailInput;
   }

   public String getName()
   {
      return name;
   }

   public String getPhone()
   {
      return phone;
   }

   public String getEmail(){
      return email;
   }
}

