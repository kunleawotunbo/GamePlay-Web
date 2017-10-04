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
@Table(name = "weeklygames")
public class WeeklyGames implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "weekNo")
    private int weekNo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "prizeOfWinners")
    private String prizeOfWinners;

    @Basic(optional = false)
    @NotNull
    @Column(name = "noOfWinners")
    private int noOfWinners;

    @Basic(optional = false)
    @NotNull
    @Column(name = "gameExpiryDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date gameExpiryDate;

    @Basic(optional = false)
    @NotNull
    @Size(max = 1000)
    @Column(name = "gameRules")
    private String gameRules;

    @Basic(optional = false)
    @NotNull
    @Column(name = "gamePlayType")
    private int gamePlayType;

    @Basic(optional = false)
    @NotNull
    @Column(name = "gameCategory")
    private int gameCategory;

    @Size(max = 1000)
    @Column(name = "gameText")
    private String gameText;

    //@Size( max = 500)
    @Column(name = "gameImage")
    private String gameImage;

    //@Size( max = 500)
    @Column(name = "gameImage2")
    private String gameImage2;

    //@Size(min = 1, max = 500)
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
    @Size(max = 100)
    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "isPicture")
    private byte isPicture;

    @Size(max = 1000)
    @Column(name = "gameAnswer")
    private String gameAnswer;

    @Column(name = "gameStartDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date gameStartDate;

    @Column(name = "enabled")
    private boolean enabled;

    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "code")
    private int code;

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

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

}
