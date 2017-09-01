/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.utility;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Olakunle Awotunbo
 */
public class Test {
    
    @Autowired
    private TunborUtility tunborUtility;
    
    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Nigeria"));
        Date currentDate = calendar.getTime();
        System.out.println("currentdate :: " + currentDate);
        
       // test();
       Test test = new Test();
       test.forSMS();
    }
    
    public String  forSMS (){
         tunborUtility.sendSMS();
         
         return "";
    }
    
   
    
}
