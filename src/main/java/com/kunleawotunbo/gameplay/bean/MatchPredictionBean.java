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
public class MatchPredictionBean {
    
  
    private Integer id;
    private int homeTeamId;
    private String homeTeamName;
    private int weekNo;
    private String prizeOfWinners;
    private int noOfWinners;
    private int awayTeamId;
    private String awayTeamName;
    private String countryCode;
    private String countryName;
    private String leagueCode;
    private String leagueName;
    private Date startTime;
    private Date endTime;
    private String winner;
    private String matchResult;
    private Date modifiedDate;
    private String createdBy;
    private boolean enabled;
    private int status;
    private int code;
    private boolean matchExpired;

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
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the leagueCode
     */
    public String getLeagueCode() {
        return leagueCode;
    }

    /**
     * @param leagueCode the leagueCode to set
     */
    public void setLeagueCode(String leagueCode) {
        this.leagueCode = leagueCode;
    }

    /**
     * @return the leagueName
     */
    public String getLeagueName() {
        return leagueName;
    }

    /**
     * @param leagueName the leagueName to set
     */
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
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
     * @return the matchExpired
     */
    public boolean isMatchExpired() {
        return matchExpired;
    }

    /**
     * @param matchExpired the matchExpired to set
     */
    public void setMatchExpired(boolean matchExpired) {
        this.matchExpired = matchExpired;
    }

}
