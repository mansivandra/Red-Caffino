package com.example.redcaffino.model;

import java.util.jar.Attributes;

public class food {
   public int getImage;
   private String Description,Discount,Image,MenuID,Name,Price;

   public food() {
   }

   public food(String description, String discount, String image, String menuID, String name, String price) {
      Description = description;
      Discount = discount;
      Image = image;
      MenuID = menuID;
      Name = name;
      Price = price;
   }

   public String getDescription() {
      return Description;
   }

   public void setDescription(String description) {
      Description = description;
   }

   public String getDiscount() {
      return Discount;
   }

   public void setDiscount(String discount) {
      Discount = discount;
   }

   public String getImage() {
      return Image;
   }

   public void setImage(String image) {
      Image = image;
   }

   public String getMenuID() {
      return MenuID;
   }

   public void setMenuID(String menuID) {
      MenuID = menuID;
   }

   public String getName() {
      return Name;
   }

   public void setName(String name) {
      Name = name;
   }

   public String getPrice() {
      return Price;
   }

   public void setPrice(String price) {
      Price = price;
   }
}
