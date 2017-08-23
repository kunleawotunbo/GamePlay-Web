/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Olakunle Awotunbo
 */
public class FileBucket {
    
    private Integer id;
    private int weekNo;
    private String prizeOfWinners;
    private int noOfWinners;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date gameExpiryDate;
    private String gameRules;
    private int gamePlayType;
    private int gameCategory;
    private String gameText;
    private String gameImage;
    private String gameImage2;
    private String gameImgLocation;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedDate;
    private String createdBy;
    private byte isPicture;  
    private String gameAnswer;
    private Date gameStartDate;
    private boolean enabled;
    private MultipartFile[] files;
    private MultipartFile file;


    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the weekNo
     */
    public int getWeekNo() {
        return weekNo;
    }

    /**
     * @param weekNo the weekNo to set
     */
    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    /**
     * @return the prizeOfWinners
     */
    public String getPrizeOfWinners() {
        return prizeOfWinners;
    }

    /**
     * @param prizeOfWinners the prizeOfWinners to set
     */
    public void setPrizeOfWinners(String prizeOfWinners) {
        this.prizeOfWinners = prizeOfWinners;
    }

    /**
     * @return the noOfWinners
     */
    public int getNoOfWinners() {
        return noOfWinners;
    }

    /**
     * @param noOfWinners the noOfWinners to set
     */
    public void setNoOfWinners(int noOfWinners) {
        this.noOfWinners = noOfWinners;
    }

    /**
     * @return the gameExpiryDate
     */
    public Date getGameExpiryDate() {
        return gameExpiryDate;
    }

    /**
     * @param gameExpiryDate the gameExpiryDate to set
     */
    public void setGameExpiryDate(Date gameExpiryDate) {
        this.gameExpiryDate = gameExpiryDate;
    }

    /**
     * @return the gameRules
     */
    public String getGameRules() {
        return gameRules;
    }

    /**
     * @param gameRules the gameRules to set
     */
    public void setGameRules(String gameRules) {
        this.gameRules = gameRules;
    }

    /**
     * @return the gamePlayType
     */
    public int getGamePlayType() {
        return gamePlayType;
    }

    /**
     * @param gamePlayType the gamePlayType to set
     */
    public void setGamePlayType(int gamePlayType) {
        this.gamePlayType = gamePlayType;
    }

    /**
     * @return the gameCategory
     */
    public int getGameCategory() {
        return gameCategory;
    }

    /**
     * @param gameCategory the gameCategory to set
     */
    public void setGameCategory(int gameCategory) {
        this.gameCategory = gameCategory;
    }

    /**
     * @return the gameText
     */
    public String getGameText() {
        return gameText;
    }

    /**
     * @param gameText the gameText to set
     */
    public void setGameText(String gameText) {
        this.gameText = gameText;
    }

    /**
     * @return the gameImage
     */
    public String getGameImage() {
        return gameImage;
    }

    /**
     * @param gameImage the gameImage to set
     */
    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }

    /**
     * @return the gameImgLocation
     */
    public String getGameImgLocation() {
        return gameImgLocation;
    }

    /**
     * @param gameImgLocation the gameImgLocation to set
     */
    public void setGameImgLocation(String gameImgLocation) {
        this.gameImgLocation = gameImgLocation;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the isPicture
     */
    public byte getIsPicture() {
        return isPicture;
    }

    /**
     * @param isPicture the isPicture to set
     */
    public void setIsPicture(byte isPicture) {
        this.isPicture = isPicture;
    }

    /**
     * @return the gameAnswer
     */
    public String getGameAnswer() {
        return gameAnswer;
    }

    /**
     * @param gameAnswer the gameAnswer to set
     */
    public void setGameAnswer(String gameAnswer) {
        this.gameAnswer = gameAnswer;
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
     * @return the gameStartDate
     */
    public Date getGameStartDate() {
        return gameStartDate;
    }

    /**
     * @param gameStartDate the gameStartDate to set
     */
    public void setGameStartDate(Date gameStartDate) {
        this.gameStartDate = gameStartDate;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the gameImage2
     */
    public String getGameImage2() {
        return gameImage2;
    }

    /**
     * @param gameImage2 the gameImage2 to set
     */
    public void setGameImage2(String gameImage2) {
        this.gameImage2 = gameImage2;
    }

    
}
