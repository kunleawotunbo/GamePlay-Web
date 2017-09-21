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
@Table(name = "match_prediction_winners")
public class MatchPredictionWinner implements Serializable{
    
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "game_id")
    private int gameId;
    
    @Basic(optional = false)
    @NotNull
    @Size( max = 15)
    @Column(name = "user_phone_no")
    private String userPhoneNo;
    
    @Basic(optional = false)
    @NotNull
    @Size( max = 250)
    @Column(name = "user_answer")
    private String userAnswer;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "answer_id")
    private int answerId;

    @Column(name = "date_answered")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateAnswered;
    
    @Column(name = "proccessed_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date proccessedDate;
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "ip_address")
    private String ipAddress;
    
    @Size(max = 100)
    @Column(name = "country")
    private String country;
    
    @Size(max = 10)
    @Column(name = "country_code")
    private String countryCode;
    
     @Size(max = 10)
    @Column(name = "city")
    private String city;
     
       @Size(max = 100)
    @Column(name = "league")
    private String league;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

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
     * @return the answerId
     */
    public int getAnswerId() {
        return answerId;
    }

    /**
     * @param answerId the answerId to set
     */
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
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
     * @return the proccessedDate
     */
    public Date getProccessedDate() {
        return proccessedDate;
    }

    /**
     * @param proccessedDate the proccessedDate to set
     */
    public void setProccessedDate(Date proccessedDate) {
        this.proccessedDate = proccessedDate;
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
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
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
     * @return the league
     */
    public String getLeague() {
        return league;
    }

    /**
     * @param league the league to set
     */
    public void setLeague(String league) {
        this.league = league;
    }
}
