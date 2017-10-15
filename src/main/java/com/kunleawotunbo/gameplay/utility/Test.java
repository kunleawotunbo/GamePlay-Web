/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.utility;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Olakunle Awotunbo
 */
public class Test {
    
   // @Autowired
    //private TunborUtility tunborUtility;
    
    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Nigeria"));
        Date currentDate = calendar.getTime();
        System.out.println("currentdate :: " + currentDate);
        
       // test();
       Test test = new Test();
       test.forSMS();
       
       int total = 100;
       int x = 0;
       int randomNo;
       
       while(x < total){
          randomNo = test.getRandomNumber();
          System.out.println("random Number :: " + randomNo);
           x++;
           
       }
    }
    
    public String  forSMS (){
         boolean result =  false;
         String[] v1 = "FN,CT,ST;".replace(";", "").split(",");
            String[] v2 = "FN,ST,CT;".replace(";", "").split(",");
            Arrays.sort(v1);
            Arrays.sort(v2);
            System.out.println(Arrays.equals(v1, v2));
            
         return "";
    }
    
     /**
     * Generate random number
     * @return random number
     */
    public int getRandomNumber() {
        int randomNumber;
        Random r = new Random();
        //Random r = new Random(System.currentTimeMillis());
        randomNumber = ((1 + r.nextInt(9)) * 1000 + r.nextInt(1000));
        // randomNumber = ((1 + r.nextInt(2)) * 1000 + r.nextInt(1000));
        return randomNumber;
    }
   
    
}
