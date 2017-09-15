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
@Table(name = "match_prediction_results")
public class MatchPredictionResult implements Serializable{
    
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
     @Column(name = "matchPredictionId")
    private int matchPredictionId; 
    
    @Size( max = 10)
    @Column(name = "homeScore")
    private String homeScore;
    
    @Size( max = 10)
    @Column(name = "awayScore")
    private String awayScore;
      
    @Size( max = 50)
    @Column(name = "matchResult")
    private String matchResult;
    
    @Size( max = 10)
    @Column(name = "winner")
    private String winner;

    @Column(name = "createdDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;
    
      @Basic(optional = false)
    @NotNull
    @Size( max = 100)
    @Column(name = "createdBy")
    private String createdBy;
    
    @Column(name = "modifiedDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedDate;
    
    @Column(name = "modifiedBy")
    private String modifiedBy;

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
     * @return the homeScore
     */
    public String getHomeScore() {
        return homeScore;
    }

    /**
     * @param homeScore the homeScore to set
     */
    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    /**
     * @return the awayScore
     */
    public String getAwayScore() {
        return awayScore;
    }

    /**
     * @param awayScore the awayScore to set
     */
    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
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
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the matchPredictionId
     */
    public int getMatchPredictionId() {
        return matchPredictionId;
    }

    /**
     * @param matchPredictionId the matchPredictionId to set
     */
    public void setMatchPredictionId(int matchPredictionId) {
        this.matchPredictionId = matchPredictionId;
    }
}
