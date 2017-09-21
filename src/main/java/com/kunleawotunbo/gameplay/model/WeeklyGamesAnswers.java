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
@Table(name = "weekly_games_answers")
public class WeeklyGamesAnswers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")

    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "game_id")
    private int gameId;

    @Basic(optional = false)
    @NotNull
    @Size(max = 15)
    @Column(name = "user_phone_no")
    private String userPhoneNo;

    @Basic(optional = false)
    @NotNull
    @Size(max = 1000)
    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "date_answered")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateAnswered;

    @Column(name = "createdDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;

    @Column(name = "modifiedDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "weekNo")
    private int weekNo;

    @Column(name = "isRandomWinner", nullable = false)
    private boolean isRandomWinner;

    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "ip_address")
    private String ipAddress;

    public WeeklyGamesAnswers() {
        super();
        this.isRandomWinner = false;
        // this.creationDate(new Date());
    }

    public Long getId() {
        return id;
    }

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
    public boolean getIsRandomWinner() {
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

}
