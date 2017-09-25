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
@Table(name = "teams")
public class Team implements Serializable{

  
     private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "team_name")
    private String teamName;
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "short_name")
    private String shortName;
    
    @Column(name = "crest_url")
    private String crestUrl;
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "league_Code")
    private String leagueCode;    
    
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "country_Code")
    private String countryCode;
    
    @Column(name = "created_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedDate;

    //@Basic(optional = false)
    //@NotNull
    @Size(max = 100)
    @Column(name = "created_by")
    private String createdBy;
    
    //@Basic(optional = false)
    //@NotNull
    @Size(max = 100)
    @Column(name = "modified_by")
    private String modifiedBy;


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
     * @return the teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return the crestUrl
     */
    public String getCrestUrl() {
        return crestUrl;
    }

    /**
     * @param crestUrl the crestUrl to set
     */
    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
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
}
