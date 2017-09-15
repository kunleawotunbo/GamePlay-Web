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
@Table(name = "match_predictions")
public class MatchPrediction implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "homeTeamId")
    private int homeTeamId;
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "homeTeamName")
    private String homeTeamName;
    
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
    @Column(name = "awayTeamId")
    private int awayTeamId;
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "awayTeamName")
    private String awayTeamName;
    
    @Column(name = "startTime")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startTime;
    
    @Column(name = "endTime")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endTime;
    
    //@Basic(optional = false)
    //@NotNull
    @Size(max = 100)
    @Column(name = "winner")
    private String winner;
    
   // @Basic(optional = false)
   // @NotNull
    @Size(max = 100)
    @Column(name = "matchResult")
    private String matchResult;
    
    @Column(name = "modifiedDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedDate;

    @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "createdBy")
    private String createdBy;
    
    @Column(name = "enabled")
    private boolean enabled;

    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;

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
     * @return the homeTeamId
     */
    public int getHomeTeamId() {
        return homeTeamId;
    }

    /**
     * @param homeTeamId the homeTeamId to set
     */
    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    /**
     * @return the homeTeamName
     */
    public String getHomeTeamName() {
        return homeTeamName;
    }

    /**
     * @param homeTeamName the homeTeamName to set
     */
    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
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
     * @return the awayTeamId
     */
    public int getAwayTeamId() {
        return awayTeamId;
    }

    /**
     * @param awayTeamId the awayTeamId to set
     */
    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    /**
     * @return the awayTeamName
     */
    public String getAwayTeamName() {
        return awayTeamName;
    }

    /**
     * @param awayTeamName the awayTeamName to set
     */
    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the winner
     */
    public String getWinner() {
        return winner;
    }

    /**
     * @param winner the winner to set
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }

    /**
     * @return the matchResult
     */
    public String getMatchResult() {
        return matchResult;
    }

    /**
     * @param matchResult the matchResult to set
     */
    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
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

    
    
}
