/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Olakunle Awotunbo
 */
@Entity
@Table(name = "WeeklyGames")
public class WeeklyGames implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "weekNo")
    private int weekNo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "prizeOfWinners")
    private String prizeOfWinners;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "noOfWinners")
    private int noOfWinners;

    @Basic(optional = false)
    @NotNull
    @Column(name = "gameExpiryDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date gameExpiryDate;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "gameRules")
    private String gameRules;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "gamePlayType")
    private int gamePlayType;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "gameCategory")
    private int gameCategory;
    
    
    @Size(min = 1, max = 50)
    @Column(name = "gameText")
    private int gameText;
    
    @Size(min = 1, max = 50)
    @Column(name = "gameImage")
    private int gameImage;
   
    @Size(min = 1, max = 500)
    @Column(name = "gameImgLocation")
    private String gameImgLocation;
    

    @Column(name = "createdDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;
    
    @Column(name = "modifiedDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedDate;
    
     @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "createdBy")
    private String createdBy;
     
     @Column(name = "isPicture")
    private byte isPicture;

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
    public int getGameText() {
        return gameText;
    }

    /**
     * @param gameText the gameText to set
     */
    public void setGameText(int gameText) {
        this.gameText = gameText;
    }

    /**
     * @return the gameImage
     */
    public int getGameImage() {
        return gameImage;
    }

    /**
     * @param gameImage the gameImage to set
     */
    public void setGameImage(int gameImage) {
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
    
}
