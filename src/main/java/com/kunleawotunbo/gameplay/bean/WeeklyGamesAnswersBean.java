/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.bean;

import java.util.Date;

/**
 *
 * @author Olakunle Awotunbo
 */
public class WeeklyGamesAnswersBean {
    
    private Long id;
    private int gameId;
    private String userPhoneNo;
    private String userAnswer;
    private Date dateAnswered;
    private Date createdDate;
    private Date modifiedDate;
    private int weekNo;
    private boolean isRandomWinner;
    private String ipAddress;
    private Date startDate;
    private Date endDate;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the userPhoneNo
     */
    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    /**
     * @param userPhoneNo the userPhoneNo to set
     */
    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    /**
     * @return the userAnswer
     */
    public String getUserAnswer() {
        return userAnswer;
    }

    /**
     * @param userAnswer the userAnswer to set
     */
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    /**
     * @return the dateAnswered
     */
    public Date getDateAnswered() {
        return dateAnswered;
    }

    /**
     * @param dateAnswered the dateAnswered to set
     */
    public void setDateAnswered(Date dateAnswered) {
        this.dateAnswered = dateAnswered;
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
     * @return the isRandomWinner
     */
    public boolean isIsRandomWinner() {
        return isRandomWinner;
    }

    /**
     * @param isRandomWinner the isRandomWinner to set
     */
    public void setIsRandomWinner(boolean isRandomWinner) {
        this.isRandomWinner = isRandomWinner;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
