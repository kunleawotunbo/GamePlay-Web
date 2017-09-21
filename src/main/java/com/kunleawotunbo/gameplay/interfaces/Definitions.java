/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.interfaces;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface Definitions {
    
    // CRUD
    public int CREATE = 1;
    public int READ = 2;
    public int UPDATE = 3;
    public int DELETE = 4;
    
    // 
    public String  SUCCESS = "SUCCESS";
    public String  FAILED  = "FAILED";
    
    public String  TIMEZONE  = "Africa/Lagos";
    
    // API url
    // https://freegeoip.net/json/197.255.170.0
    public String  IP_API  = "http://ip-api.com/json/";
}
