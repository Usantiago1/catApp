package com.example.catapp.ui.Model;

public class Data {
     String fact;
     String length;

     public String getFact(){
         return fact;
     }
     public String getLength(){
         return length;
     }
     public void setFact(String fact){
         this.fact = fact;
     }
     public void setLength(String length){
         this.length = length;
     }
     public void DataConstructor(String fact, String length){
         this.fact = fact;
         this.length = length;
     }

    }

