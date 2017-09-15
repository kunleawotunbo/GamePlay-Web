/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.bean;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author BELLO
 */
public class WinnerBean {
    
    private String winnerNumber;
     private MultipartFile[] files;
    private MultipartFile file;
    
    
    
    /**
     * @return the winnerNumber
     */
    public String getNumber() {
        
        return winnerNumber;
    }

    /**
     * @param winnerNumber the winnerNumber to set
     */
    public void setNumber(String winnerNumber) {
        
        this.winnerNumber = winnerNumber;
    }
    
    
     /**
     * @return the files
     */
    public MultipartFile[] getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
