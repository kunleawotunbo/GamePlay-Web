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
    
    @Column(name = "isWinner", nullable = false)
    private boolean isWinner;

    @Basic(optional = false)
    @Size(max = 50)
    @Column(name = "ip_address", nullable = false)
    private String ipAddress;
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "players_country")
    private String playersCountry;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "code")
    private int code;
        
    @Size(max = 10)
    @Column(name = "country_code")
    private String countryCode;
    
     @Size(max = 10)
    @Column(name = "city")
    private String city;
     
     @Column(name = "correct") 
    private boolean correct;
    

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
     * @return the isWinner
     */
    public boolean getIsWinner() {
        return isWinner;
    }

    /**
     * @param isWinner the isRandomWinner to set
     */
    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
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
    
    public String getPlayersCountry() {
        return playersCountry;
    }

    /**
     * @param playersCountry the ipAddress to set
     */
    public void setPlayersCountry(String playersCountry) {
        this.playersCountry = playersCountry;
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

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the correct
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * @param correct the correct to set
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
